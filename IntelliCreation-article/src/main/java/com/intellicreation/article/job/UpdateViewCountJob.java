package com.intellicreation.article.job;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.intellicreation.common.constant.SystemConstants;
import com.intellicreation.article.domain.entity.AmsArticleDO;
import com.intellicreation.article.service.AmsArticleService;
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
public class UpdateViewCountJob {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private AmsArticleService amsArticleService;

    // todo 把这里的5秒改成合适的时间
    @Scheduled(cron = "0/59 * * * * ?")
    public void updateViewCount(){
        // 获取redis中的浏览量
        Map<String, Integer> viewCountMap = redisCache.getCacheMap(SystemConstants.ARTICLE_VIEW_COUNT_KEY);

        List<AmsArticleDO> articles = viewCountMap.entrySet()
                .stream()
                .map(entry -> new AmsArticleDO(Long.valueOf(entry.getKey()), entry.getValue().longValue()))
                .collect(Collectors.toList());
        // 更新到数据库中
        // todo 仅更新viewcount也会更新修改时间，存在问题
        for (AmsArticleDO article : articles) {
            LambdaUpdateWrapper<AmsArticleDO> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(AmsArticleDO :: getId, article.getId());
            updateWrapper.set(AmsArticleDO :: getViewCount, article.getViewCount());
            amsArticleService.update(updateWrapper);
        }
    }
}
