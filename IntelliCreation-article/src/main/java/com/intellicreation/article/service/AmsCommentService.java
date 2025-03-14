package com.intellicreation.article.service;

import com.intellicreation.article.domain.dto.AddCommentDTO;
import com.intellicreation.article.domain.entity.AmsCommentDO;
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
     * 添加一条评论
     *
     * @param addCommentDTO
     */
    void addComment(AddCommentDTO addCommentDTO) throws Exception;
}
