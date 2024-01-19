package com.intellicreation.service;

import com.intellicreation.domain.dto.ResponseResult;
import com.intellicreation.domain.model.AmsCommentDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author za
 * @since 2024-01-19
 */
public interface AmsCommentService extends IService<AmsCommentDO> {

    /**
     * 获取评论列表
     *
     * @param articleId
     * @param pageNum
     * @param pageSize
     * @return ResponseResult
     */
    ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize);

}
