package com.intellicreation.article.domain.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author za
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDetailVO {

    private Long id;

    private String title;

    private Long authorId;

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

    @ApiModelProperty(value = "创建人")
    private Long createBy;

    @ApiModelProperty(value = "更新人")
    private Long modifiedBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "最后修改时间")
    private LocalDateTime gmtModified;
}
