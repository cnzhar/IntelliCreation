package com.intellicreation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.intellicreation.domain.dto.ResponseResult;
import com.intellicreation.domain.model.AmsArticleDO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author za
 * @since 2024-01-05
 */
public interface AmsArticleService extends IService<AmsArticleDO> {

    /**
     * 查询热门文章，并封装成ResponseResult
     *
     * @return ResponseResult
     */
    ResponseResult hotArticleList();

    /**
     * 直接查询文章列表，或根据分类查询文章列表
     * 注意：
     * 1.仅返回有“已发布”状态的文章
     * 2.必须是未被删除的文章
     *
     * @param pageNum
     * @param pageSize
     * @param categoryId
     * @return ResponseResult
     */
    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    /**
     * 更新浏览量
     *
     * @param id
     * @return
     */
    ResponseResult updateViewCount(Long id);

    /**
     * 获取文章详情
     * @param id
     * @return
     */
    ResponseResult getArticleDetail(Long id);
}
