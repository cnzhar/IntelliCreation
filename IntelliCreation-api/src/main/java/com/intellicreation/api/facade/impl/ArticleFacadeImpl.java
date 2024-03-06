package com.intellicreation.api.facade.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.intellicreation.api.facade.ArticleFacade;
import com.intellicreation.article.domain.dto.AddArticleDTO;
import com.intellicreation.article.domain.dto.PostRatingDTO;
import com.intellicreation.article.domain.dto.UpdateArticleInfoDTO;
import com.intellicreation.article.domain.entity.*;
import com.intellicreation.article.domain.vo.*;
import com.intellicreation.article.service.*;
import com.intellicreation.common.constant.SystemConstants;
import com.intellicreation.common.util.BeanCopyUtils;
import com.intellicreation.common.util.RedisCache;
import com.intellicreation.common.vo.PageVO;
import com.intellicreation.member.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author za
 */
@Component
public class ArticleFacadeImpl implements ArticleFacade {

    @Autowired
    private AmsArticleService amsArticleService;

    @Autowired
    private AmsCategoryService amsCategoryService;

    @Autowired
    private UmsMemberService umsMemberService;

    @Autowired
    private AmsTagService amsTagService;

    @Autowired
    private AmsArticleTagRelationService amsArticleTagRelationService;

    @Autowired
    private AmsRatingService amsRatingService;

    @Autowired
    private RedisCache redisCache;

    @Override
    public List<HotArticleVO> hotArticleList() {
        return amsArticleService.hotArticleList();
    }

    @Override
    public PageVO articleList(Integer pageNum, Integer pageSize, Long category1Id, Long category2Id) {
        // todo 要不要下放到service
        // todo viewCount改为从redis中获取
        // todo select过多字段
        // 查询条件
        LambdaQueryWrapper<AmsArticleDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .select(AmsArticleDO::getId, AmsArticleDO::getTitle, AmsArticleDO::getSummary,
                        AmsArticleDO::getCategory1Id, AmsArticleDO::getThumbnail, AmsArticleDO::getLikeCount,
                        AmsArticleDO::getViewCount, AmsArticleDO::getCreateBy, AmsArticleDO::getGmtCreate)
                // 状态是已发布的
                .eq(AmsArticleDO::getStatus, SystemConstants.ARTICLE_STATUS_PUBLISHED)
                // 如果传入分类不为空，则只返回符合的
                .eq(!ObjectUtils.isEmpty(category1Id), AmsArticleDO::getCategory1Id, category1Id)
                .eq(!ObjectUtils.isEmpty(category2Id), AmsArticleDO::getCategory2Id, category2Id);
        // 分页查询
        Page<AmsArticleDO> page = new Page<>(pageNum, pageSize);
        amsArticleService.page(page, lambdaQueryWrapper);
        // 封装查询结果
        List<ArticleListVO> articleListVOList = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVO.class);
        articleListVOList
                // 查询关联的标签
                .forEach(articleListVO -> {
                    List<Long> tagIds = amsArticleTagRelationService.getTagByArticleId(articleListVO.getId());
                    List<String> tagNameList = new ArrayList<>();
                    for (Long tagId : tagIds) {
                        String tagName = amsTagService.getById(tagId).getName();
                        tagNameList.add(tagName);
                    }
                    articleListVO.setTagName(tagNameList);
                });
        articleListVOList.stream()
                // 根据categoryId，查询的对应categoryName
                .map(articleListVO -> articleListVO.setCategory1Name(amsCategoryService.getById(articleListVO.getCategory1Id()).getName()))
                // 根据createBy，查询的对应nickname
                .map(articleListVO -> articleListVO.setNickname(umsMemberService.getNicknameById(articleListVO.getCreateBy())))
                // 根据createBy，查询的对应Avatar
                .map(articleListVO -> articleListVO.setAvatar(umsMemberService.getAvatarById(articleListVO.getCreateBy())))
                .collect(Collectors.toList());
        return new PageVO(articleListVOList, page.getTotal());
    }

    // Todo 只有已发布的能被所有人看到，草稿等只能自己看
    @Override
    public ArticleViewVO getArticleDetail(Long id) {
        // 根据id查询文章
        AmsArticleDO amsArticleDO = amsArticleService.getById(id);
        // 从redis中获取viewCount
        Integer viewCount = redisCache.getCacheMapValue(SystemConstants.ARTICLE_VIEW_COUNT_KEY, id.toString());
        amsArticleDO.setViewCount(viewCount.longValue());
        // 转换成VO
        ArticleViewVO articleViewVO = BeanCopyUtils.copyBean(amsArticleDO, ArticleViewVO.class);
        // 设定作者头像
        String avatar = umsMemberService.getAvatarById(articleViewVO.getCreateBy());
        articleViewVO.setAvatar(avatar);
        // 设定作者nickname
        String nickname = umsMemberService.getNicknameById(articleViewVO.getCreateBy());
        articleViewVO.setNickname(nickname);
        // 根据分类id查询分类名
        Long category1Id = articleViewVO.getCategory1Id();
        AmsCategoryDO amsCategoryDO = amsCategoryService.getById(category1Id);
        if (amsCategoryDO != null) {
            articleViewVO.setCategory1Name(amsCategoryDO.getName());
        }
        List<Long> tagIds = amsArticleTagRelationService.getTagByArticleId(id);
        List<String> tagNameList = new ArrayList<>();
        for (Long tagId : tagIds) {
            String tagName = amsTagService.getById(tagId).getName();
            tagNameList.add(tagName);
        }
        articleViewVO.setTagName(tagNameList);
        // 更新浏览量
        amsArticleService.updateViewCount(id);
        return articleViewVO;
    }

    @Override
    public PageVO availableCategory1(Integer pageNum, Integer pageSize, String name) {
        LambdaQueryWrapper<AmsCategoryDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .select(AmsCategoryDO::getId, AmsCategoryDO::getName, AmsCategoryDO::getParent)
                .eq(AmsCategoryDO::getParent, SystemConstants.NULL_PARENT)
                .like(StringUtils.hasText(name), AmsCategoryDO::getName, name);
        Page<AmsCategoryDO> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        amsCategoryService.page(page, lambdaQueryWrapper);
        return new PageVO(page.getRecords(), page.getTotal());
    }

    @Override
    public PageVO availableCategory2(Integer pageNum, Integer pageSize, String name, Long parent) {
        LambdaQueryWrapper<AmsCategoryDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .select(AmsCategoryDO::getId, AmsCategoryDO::getName, AmsCategoryDO::getParent)
                .eq(AmsCategoryDO::getParent, parent)
                .like(StringUtils.hasText(name), AmsCategoryDO::getName, name);
        Page<AmsCategoryDO> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        amsCategoryService.page(page, lambdaQueryWrapper);
        return new PageVO(page.getRecords(), page.getTotal());
    }

    @Override
    public PageVO availableTag(Integer pageNum, Integer pageSize, String name) {
        LambdaQueryWrapper<AmsTagDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .select(AmsTagDO::getId, AmsTagDO::getName)
                .like(StringUtils.hasText(name), AmsTagDO::getName, name);
        Page<AmsTagDO> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        amsTagService.page(page, lambdaQueryWrapper);
        return new PageVO(page.getRecords(), page.getTotal());
    }

    @Override
    public void addArticle(AddArticleDTO addArticleDTO) {
        // 新增文章
        Long articleId = amsArticleService.addArticle(addArticleDTO);
        // 保存文章标签关联关系
        amsArticleTagRelationService.saveArticleTag(articleId, addArticleDTO.getTag());
        // 在redis中初始化viewCount
        redisCache.setCacheMapValue(SystemConstants.ARTICLE_VIEW_COUNT_KEY, articleId.toString(), 0);
    }

    @Override
    public UpdateArticleInfoVO updateArticleInfo(Long id) {
        AmsArticleDO amsArticleDO = amsArticleService.getById(id);
        UpdateArticleInfoVO updateArticleInfoVO = BeanCopyUtils.copyBean(amsArticleDO, UpdateArticleInfoVO.class);
        // 根据分类id查询分类名
        Long category1Id = updateArticleInfoVO.getCategory1Id();
        AmsCategoryDO amsCategoryDO = amsCategoryService.getById(category1Id);
        if (amsCategoryDO != null) {
            updateArticleInfoVO.setCategory1Name(amsCategoryDO.getName());
        }
        Long category2Id = updateArticleInfoVO.getCategory2Id();
        amsCategoryDO = amsCategoryService.getById(category2Id);
        if (amsCategoryDO != null) {
            updateArticleInfoVO.setCategory2Name(amsCategoryDO.getName());
        }
        // 设定标签
        List<Long> tagIdList = amsArticleTagRelationService.getTagByArticleId(id);
        updateArticleInfoVO.setTag(tagIdList);
        List<String> tagNameList = new ArrayList<>();
        // 设定标签名
        for (Long tagId : tagIdList) {
            AmsTagDO amsTagDO = amsTagService.getById(tagId);
            String tagName = amsTagDO.getName();
            tagNameList.add(tagName);
        }
        updateArticleInfoVO.setTagName(tagNameList);
        return updateArticleInfoVO;
    }

    @Override
    public void updateArticle(UpdateArticleInfoDTO updateArticleInfoDTO) {
        amsArticleService.updateArticle(updateArticleInfoDTO);
        // 删除原本所有文章标签关联
        LambdaQueryWrapper<AmsArticleTagRelationDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(AmsArticleTagRelationDO::getArticleId, updateArticleInfoDTO.getId());
        amsArticleTagRelationService.remove(lambdaQueryWrapper);
        // 建立新的文章标签关联
        amsArticleTagRelationService.saveArticleTag(updateArticleInfoDTO.getId(), updateArticleInfoDTO.getTag());
    }

    @Override
    public void postRating(PostRatingDTO postRatingDTO) {
        amsRatingService.postRating(postRatingDTO);
    }

    @Override
    public PageVO ratingList(Integer pageNum, Integer pageSize, Long articleId) {
        LambdaQueryWrapper<AmsRatingDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .select(AmsRatingDO::getScore, AmsRatingDO::getText, AmsRatingDO::getCreateBy)
                .eq(AmsRatingDO::getArticleId, articleId);
        Page<AmsRatingDO> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        amsRatingService.page(page, lambdaQueryWrapper);
        // 封装查询结果
        List<RatingListVO> ratingListVOList = BeanCopyUtils.copyBeanList(page.getRecords(), RatingListVO.class);
        ratingListVOList.stream()
                .map(ratingListVO -> ratingListVO.setNickname(umsMemberService.getNicknameById(ratingListVO.getCreateBy())))
                .map(ratingListVO -> ratingListVO.setAvatar(umsMemberService.getAvatarById(ratingListVO.getCreateBy())))
                .collect(Collectors.toList());
        return new PageVO(ratingListVOList, page.getTotal());
    }
}
