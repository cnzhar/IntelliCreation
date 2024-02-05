package com.intellicreation.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.intellicreation.article.domain.dto.AddArticleDTO;
import com.intellicreation.article.domain.entity.AmsArticleDO;
import com.intellicreation.article.domain.vo.HotArticleVO;
import com.intellicreation.common.util.BeanCopyUtils;
import com.intellicreation.common.util.RedisCache;
import com.intellicreation.common.constant.SystemConstants;
import com.intellicreation.article.mapper.AmsArticleMapper;
import com.intellicreation.article.service.AmsArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        // 必须是正式文章（不是草稿）
        lambdaQueryWrapper.eq(AmsArticleDO::getStatus, SystemConstants.ARTICLE_STATUS_PUBLISHED);
        // 按照浏览量进行排序AmsArticle
        lambdaQueryWrapper.orderByDesc(AmsArticleDO::getViewCount);
        // 最多只查询十条
        Page<AmsArticleDO> page = new Page(1, 10);
        page(page, lambdaQueryWrapper);
        List<AmsArticleDO> articleList = page.getRecords();
        // bean拷贝
        List<HotArticleVO> hotArticleVOList = BeanCopyUtils.copyBeanList(articleList, HotArticleVO.class);
        return hotArticleVOList;
    }

    @Override
    public void updateViewCount(Long id) {
        // 更新redis中对应 id的浏览量
        redisCache.incrementCacheMapValue(SystemConstants.ARTICLE_VIEW_COUNT_KEY, id.toString(), 1);
    }

    @Override
    public void addArticle(AddArticleDTO addArticleDTO, Long memberId) {
        AmsArticleDO amsArticleDO = BeanCopyUtils.copyBean(addArticleDTO, AmsArticleDO.class);
        amsArticleDO.setAuthorId(memberId);
        save(amsArticleDO);
    }
}
