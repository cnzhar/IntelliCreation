package com.intellicreation.api.facade.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.intellicreation.api.facade.ArticleFacade;
import com.intellicreation.article.domain.dto.AddArticleDTO;
import com.intellicreation.article.domain.dto.PostRatingDTO;
import com.intellicreation.article.domain.entity.AmsArticleDO;
import com.intellicreation.article.domain.entity.AmsCategoryDO;
import com.intellicreation.article.domain.vo.ArticleTextVO;
import com.intellicreation.article.domain.vo.ArticleListVO;
import com.intellicreation.article.domain.vo.HotArticleVO;
import com.intellicreation.article.service.AmsArticleService;
import com.intellicreation.article.service.AmsCategoryService;
import com.intellicreation.article.service.AmsRatingService;
import com.intellicreation.common.constant.SystemConstants;
import com.intellicreation.common.util.BeanCopyUtils;
import com.intellicreation.common.util.RedisCache;
import com.intellicreation.common.vo.PageVO;
import com.intellicreation.member.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
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
    private AmsRatingService amsRatingService;

    @Autowired
    private RedisCache redisCache;

    @Override
    public List<HotArticleVO> hotArticleList() {
        return amsArticleService.hotArticleList();
    }

    @Override
    public PageVO articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        // todo 要不要下放到service
        // todo viewCount改为从redis中获取
        // todo select过多字段
        // todo 看是否需要去掉articlelistvo
        // 查询条件
        LambdaQueryWrapper<AmsArticleDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 如果有categoryId，只留下该分类下的
        lambdaQueryWrapper.eq(Objects.nonNull(categoryId) && categoryId > 0, AmsArticleDO::getCategory1Id, categoryId);
        // 状态是已发布的
        lambdaQueryWrapper.eq(AmsArticleDO::getStatus, SystemConstants.ARTICLE_STATUS_PUBLISHED);
        // 分页查询
        Page<AmsArticleDO> page = new Page<>(pageNum, pageSize);
        amsArticleService.page(page, lambdaQueryWrapper);
        // 封装查询结果
        List<ArticleListVO> articleListVOList = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVO.class);
        // 根据categoryId，查询的对应categoryName
        articleListVOList.stream()
                .map(articleListVO -> articleListVO.setCategory1Name(amsCategoryService.getById(articleListVO.getCategory1Id()).getName()))
                .collect(Collectors.toList());
        return new PageVO(articleListVOList, page.getTotal());
    }

    @Override
    public void updateViewCount(Long id) {
        amsArticleService.updateViewCount(id);
    }

    // Todo 只有已发布的能被所有人看到，草稿等只能自己看
    @Override
    public ArticleTextVO getArticleDetail(Long id) {
        // 根据id查询文章
        AmsArticleDO amsArticleDO = amsArticleService.getById(id);
        // 从redis中获取viewCount
        Integer viewCount = redisCache.getCacheMapValue(SystemConstants.ARTICLE_VIEW_COUNT_KEY, id.toString());
        amsArticleDO.setViewCount(viewCount.longValue());
        // 转换成VO
        ArticleTextVO articleTextVO = BeanCopyUtils.copyBean(amsArticleDO, ArticleTextVO.class);
        // 根据分类id查询分类名
        Long category1Id = articleTextVO.getCategory1Id();
        AmsCategoryDO amsCategoryDO = amsCategoryService.getById(category1Id);
        if (amsCategoryDO != null) {
            articleTextVO.setCategory1Name(amsCategoryDO.getName());
        }
        return articleTextVO;
    }

    @Override
    public void addArticle(AddArticleDTO addArticleDTO) {
        amsArticleService.addArticle(addArticleDTO);
    }

    @Override
    public void postRating(PostRatingDTO postRatingDTO) {
        amsRatingService.postRating(postRatingDTO);
    }
}
