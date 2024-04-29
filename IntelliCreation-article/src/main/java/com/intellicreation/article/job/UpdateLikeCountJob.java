package com.intellicreation.article.job;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.intellicreation.article.domain.entity.AmsArticleDO;
import com.intellicreation.article.service.AmsArticleService;
import com.intellicreation.common.constant.SystemConstants;
import com.intellicreation.common.util.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author za
 */
@Component
public class UpdateLikeCountJob {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private AmsArticleService amsArticleService;

    @Scheduled(cron = "0 0/5 * * * ?")
    public void updateLikeCount() {
        // 获取redis中的点赞量
        Map<String, Integer> likeCountMap = redisCache.getCacheMap(SystemConstants.ARTICLE_LIKE_COUNT_KEY);

        List<AmsArticleDO> articles = likeCountMap.entrySet()
                .stream()
                .map(entry -> new AmsArticleDO(Long.valueOf(entry.getKey()), null, entry.getValue().longValue()))
                .collect(Collectors.toList());
        // 更新到数据库中
        for (AmsArticleDO article : articles) {
            LambdaUpdateWrapper<AmsArticleDO> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(AmsArticleDO :: getId, article.getId());
            updateWrapper.set(AmsArticleDO :: getLikeCount, article.getLikeCount());
            amsArticleService.update(updateWrapper);
        }
    }
}
