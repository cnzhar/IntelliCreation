package com.intellicreation.article.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author za
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentVO {

    private Long id;

    /**
     * 文章id
     */
    private Long articleId;

    /**
     * 根评论id
     */
    private Long rootId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 回复目标评论id
     */
    private Long toCommentId;

    /**
     * 所回复的目标评论的userid
     */
    private Long toCommentMemberId;

    private String toCommentMemberName;

    private Long createBy;

    private String avatar;

    private LocalDateTime gmtCreate;

    private String nickname;

    private List<CommentVO> children;
}
