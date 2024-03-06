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
     * @param pageNum
     * @param pageSize
     * @param articleId
     * @return ResponseResult
     */
    PageVO commentList(Integer pageNum, Integer pageSize, Long articleId);

    /**
     * 添加一条评论
     *
     * @param comment
     * @return
     */
    void addComment(AmsCommentDO comment);
}
