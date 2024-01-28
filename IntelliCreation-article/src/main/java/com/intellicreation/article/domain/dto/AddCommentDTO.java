package com.intellicreation.article.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author za
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AddCommentDTO对象", description = "添加评论DTO")
public class AddCommentDTO {

    private Long id;

    @ApiModelProperty(value = "文章id")
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
     * 所回复的目标评论的MemberId
     */
    private Long toCommentMemberId;

    private Long createBy;

    private Long modifiedBy;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;


}
