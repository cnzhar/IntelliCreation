package com.intellicreation.api.facade;


import com.intellicreation.article.domain.entity.AmsCommentDO;
import com.intellicreation.common.vo.PageVO;

/**
 * @author za
 */
public interface CommentFacade {

    /**
     * 获取评论列表
     *
     * @param articleId
     * @param pageNum
     * @param pageSize
     * @return ResponseResult
     */
    PageVO commentList(Long articleId, Integer pageNum, Integer pageSize);

    /**
     * 添加一条评论
     *
     * @param comment
     * @return
     */
    void addComment(AmsCommentDO comment);
}
