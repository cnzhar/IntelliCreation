package com.intellicreation.article.domain.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 *
 * </p>
 *
 * @author za
 * @since 2024-01-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@TableName("ams_article")
@ApiModel(value = "AmsArticle对象", description = "AmsArticleDO")
public class AmsArticleDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String title;

    @ApiModelProperty(value = "文章摘要")
    private String summary;

    @ApiModelProperty(value = "缩略图")
    private String thumbnail;

    private String content;

    @ApiModelProperty(value = "文章状态（0为草稿，1为审核中，2为审核通过，3为审核不通过，4为已发布")
    private String status;

    @ApiModelProperty(value = "点赞数")
    private Long likeCount;

    @ApiModelProperty(value = "阅读量")
    private Long viewCount;

    @ApiModelProperty(value = "是否允许评论（0为允许评论，1为不允许评论）")
    @TableField("is_comment")
    private Integer comment;

    @ApiModelProperty(value = "评论数")
    private Long commentCount;

    @ApiModelProperty(value = "是否被设为精品")
    @TableField("is_premium")
    private Integer premium;

    @ApiModelProperty(value = "默认为非分类1")
    private Long category1Id;

    @ApiModelProperty(value = "默认为非分类1")
    private Long category2Id;

    @ApiModelProperty(value = "是否被删除（0为未删除，1为已删除）")
    @TableField("is_deleted")
    private Integer deleted;

    @ApiModelProperty(value = "创建人")
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    @ApiModelProperty(value = "更新人")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long modifiedBy;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "最后修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;

    public AmsArticleDO(Long id, Long viewCount, Long likeCount) {
        this.id = id;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
    }
}
