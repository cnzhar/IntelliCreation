package com.intellicreation.article.runner;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.intellicreation.common.constant.SystemConstants;
import com.intellicreation.article.domain.entity.AmsArticleDO;
import com.intellicreation.article.mapper.AmsArticleMapper;
import com.intellicreation.common.util.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 启动时，将浏览量读入Redis
 * @author za
 */
@Component
public class ViewCountRunner implements CommandLineRunner {

    @Autowired
    private AmsArticleMapper amsArticleMapper;

    @Autowired
    private RedisCache redisCache;

    @Override
    public void run(String... args) throws Exception {
        // 查询博客信息  id  viewCount
        LambdaQueryWrapper<AmsArticleDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.select(AmsArticleDO::getId, AmsArticleDO::getViewCount);
        List<AmsArticleDO> articles = amsArticleMapper.selectList(lambdaQueryWrapper);
        Map<String, Integer> viewCountMap = articles.stream()
                .collect(Collectors.toMap(article -> article.getId().toString(), article -> article.getViewCount().intValue()));
        // 存储到redis中
        redisCache.setCacheMap(SystemConstants.ARTICLE_VIEW_COUNT_KEY, viewCountMap);
    }
}
