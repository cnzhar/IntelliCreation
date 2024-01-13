package com.intellicreation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.intellicreation.domain.model.AmsArticleDO;
import com.intellicreation.domain.ResponseResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author za
 * @since 2024-01-05
 */
public interface AmsArticleService extends IService<AmsArticleDO> {

    /**
     * 查询热门文章，并封装成ResponseResult
     * @return ResponseResult
     */
    ResponseResult hotArticleList();

    /**
     * 文章列表
     * @param pageNum
     * @param pageSize
     * @param categoryId
     * @return ResponseResult
     */
    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);
}
