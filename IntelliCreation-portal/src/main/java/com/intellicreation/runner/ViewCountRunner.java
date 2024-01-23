package com.intellicreation.runner;

import com.intellicreation.constant.SystemConstants;
import com.intellicreation.domain.model.AmsArticleDO;
import com.intellicreation.mapper.AmsArticleMapper;
import com.intellicreation.util.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
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
        List<AmsArticleDO> articles = amsArticleMapper.selectList(null);
        Map<String, Integer> viewCountMap = articles.stream()
                .collect(Collectors.toMap(article -> article.getId().toString(), article -> article.getViewCount().intValue()));
        // 存储到redis中
        redisCache.setCacheMap(SystemConstants.ARTICLE_VIEW_COUNT_KEY, viewCountMap);
    }
}
