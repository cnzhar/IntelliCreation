package com.intellicreation.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.intellicreation.article.domain.dto.AddArticleDTO;
import com.intellicreation.article.domain.dto.UpdateArticleInfoDTO;
import com.intellicreation.article.domain.entity.AmsArticleDO;
import com.intellicreation.article.domain.vo.ArticleDetailVO;
import com.intellicreation.article.domain.vo.ArticleQueryParamDTO;
import com.intellicreation.article.domain.vo.HotArticleVO;
import com.intellicreation.article.domain.vo.UpdateArticleInfoVO;
import com.intellicreation.common.util.BeanCopyUtils;
import com.intellicreation.common.util.RedisCache;
import com.intellicreation.common.constant.SystemConstants;
import com.intellicreation.article.mapper.AmsArticleMapper;
import com.intellicreation.article.service.AmsArticleService;
import com.intellicreation.common.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author za
 * @since 2024-01-05
 */
@Service
public class AmsArticleServiceImpl extends ServiceImpl<AmsArticleMapper, AmsArticleDO> implements AmsArticleService {

    @Autowired
    private RedisCache redisCache;

    @Override
    public List<HotArticleVO> hotArticleList() {
        // todo viewCount改为从redis中获取
        // 查询热门文章，并封装成ResponseResult
        LambdaQueryWrapper<AmsArticleDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                // 必须是正式文章（不是草稿）
                .select(AmsArticleDO::getId, AmsArticleDO::getTitle, AmsArticleDO::getViewCount)
                .eq(AmsArticleDO::getStatus, SystemConstants.ARTICLE_STATUS_PUBLISHED)
                // 按照浏览量进行排序AmsArticle
                .orderByDesc(AmsArticleDO::getViewCount);
        // 最多只查询十条
        Page<AmsArticleDO> page = new Page(1, 10);
        page(page, lambdaQueryWrapper);
        List<AmsArticleDO> articleList = page.getRecords();
        // bean拷贝
        return BeanCopyUtils.copyBeanList(articleList, HotArticleVO.class);
    }

    @Override
    public void updateViewCount(Long id) {
        // 更新redis中对应 id的浏览量
        redisCache.incrementCacheMapValue(SystemConstants.ARTICLE_VIEW_COUNT_KEY, id.toString(), 1);
    }

    @Override
    public Long addArticle(AddArticleDTO addArticleDTO) {
        AmsArticleDO amsArticleDO = BeanCopyUtils.copyBean(addArticleDTO, AmsArticleDO.class);
        save(amsArticleDO);
        return amsArticleDO.getId();
    }

    @Override
    public void updateArticle(UpdateArticleInfoDTO updateArticleInfoDTO) {
        AmsArticleDO amsArticleDO = BeanCopyUtils.copyBean(updateArticleInfoDTO, AmsArticleDO.class);
        updateById(amsArticleDO);
    }

    @Override
    public PageVO queryArticleList(Integer pageNum, Integer pageSize, ArticleQueryParamDTO articleQueryParamDTO) {
        // 分页查询
        LambdaQueryWrapper<AmsArticleDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .select(AmsArticleDO::getId, AmsArticleDO::getTitle, AmsArticleDO::getCreateBy)
                .like(!ObjectUtils.isEmpty(articleQueryParamDTO.getId()), AmsArticleDO::getId, articleQueryParamDTO.getId())
                .like(StringUtils.hasText(articleQueryParamDTO.getTitle()), AmsArticleDO::getTitle, articleQueryParamDTO.getTitle())
                .like(!ObjectUtils.isEmpty(articleQueryParamDTO.getAuthorId()), AmsArticleDO::getCreateBy, articleQueryParamDTO.getAuthorId());
        Page<AmsArticleDO> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, lambdaQueryWrapper);
        // 封装数据返回
        return new PageVO(page.getRecords(), page.getTotal());
    }

    @Override
    public ArticleDetailVO getArticleDetail(Long id) {
        AmsArticleDO amsArticleDO = getById(id);
        return BeanCopyUtils.copyBean(amsArticleDO, ArticleDetailVO.class);
    }

    @Override
    public boolean isAuthor(Long memberId, Long articleId) {
        AmsArticleDO amsArticleDO = getById(articleId);
        return amsArticleDO.getCreateBy().equals(memberId);
    }
}
