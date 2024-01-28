package com.intellicreation.article.domain.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author za
 * @since 2024-01-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ams_comment")
@ApiModel(value="AmsComment对象", description="")
public class AmsCommentDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long articleId;

    @ApiModelProperty(value = "根评论id, 默认为0")
    private Long rootId;

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "对哪条评论进行回复，默认为0")
    private Long toCommentId;

    @ApiModelProperty(value = "对哪个用户进行回复，默认为0")
    private Long toCommentMemberId;

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


}
