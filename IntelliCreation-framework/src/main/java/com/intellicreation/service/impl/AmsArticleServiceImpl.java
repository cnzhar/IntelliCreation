package com.intellicreation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.intellicreation.domain.model.AmsArticleDO;
import com.intellicreation.domain.ResponseResult;
import com.intellicreation.mapper.AmsArticleMapper;
import com.intellicreation.service.AmsArticleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author za
 * @since 2024-01-05
 */
@Service
public class AmsArticleServiceImpl extends ServiceImpl<AmsArticleMapper, AmsArticleDO> implements AmsArticleService {

    @Override
    public ResponseResult hotArticleList() {
        // 查询热门文章，并封装成ResponseResult
        LambdaQueryWrapper<AmsArticleDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 必须是正式文章（不是草稿）
        lambdaQueryWrapper.eq(AmsArticleDO::getStatus, 0);
        // 按照浏览量进行排序AmsArticle
        lambdaQueryWrapper.orderByDesc(AmsArticleDO::getViewCount);
        // 最多只查询十条
        Page<AmsArticleDO> page = new Page(1, 10);
        page(page, lambdaQueryWrapper);

        List<AmsArticleDO> articleList = page.getRecords();
        return ResponseResult.okResult(articleList);
    }
}
