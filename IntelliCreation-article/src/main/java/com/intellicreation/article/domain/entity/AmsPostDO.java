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
 * @since 2024-02-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ams_post")
@ApiModel(value="AmsPost对象", description="")
public class AmsPostDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String title;

    private String content;

    @ApiModelProperty(value = "缩略图")
    private String thumbnail;

    private Long likeCount;

    private Long viewCount;

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
