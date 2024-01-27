package com.intellicreation.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2024-01-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ums_menu")
@ApiModel(value="UmsMenu对象", description="")
public class UmsMenu implements Serializable {

    // todo 可能有很多不需要的字段，记得删除，同时也要删除数据表中的相应字段

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String icon;

    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @ApiModelProperty(value = "父菜单ID")
    private Long parent;

    @ApiModelProperty(value = "显示顺序")
    private Integer sort;

    @ApiModelProperty(value = "路由地址")
    private String path;

    @ApiModelProperty(value = "组件路径")
    private String component;

    @ApiModelProperty(value = "是否为外链（0否，1是）")
    @TableField("is_frame")
    private Boolean frame;

    @ApiModelProperty(value = "菜单类型（I目录，M菜单，B按钮）")
    private String menuType;

    @ApiModelProperty(value = "菜单状态（0显示，1隐藏）")
    @TableField("is_visible")
    private Boolean visible;

    @ApiModelProperty(value = "菜单状态（0正常，1停用）")
    private String status;

    @ApiModelProperty(value = "描述信息")
    private String description;

    @ApiModelProperty(value = "是否被删除（0为未删除，1为已删除）")
    @TableField("is_deleted")
    private Integer deleted;

    @ApiModelProperty(value = "创建人")
    private Long createBy;

    @ApiModelProperty(value = "更新人")
    private Long modifiedBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "最后修改时间")
    private LocalDateTime gmtModified;


}
