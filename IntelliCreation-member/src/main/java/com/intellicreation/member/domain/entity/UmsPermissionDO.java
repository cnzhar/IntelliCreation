package com.intellicreation.member.domain.entity;

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
 * @since 2024-01-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ums_permission")
@ApiModel(value="UmsPermission对象", description="")
public class UmsPermissionDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String permissionName;

    private String permissionKey;

    @ApiModelProperty(value = "权限类型（I目录，M菜单，B按钮）")
    private String permissionType;

    @ApiModelProperty(value = "权限状态（0正常 1停用）")
    private String status;

    @ApiModelProperty(value = "是否删除（0未删除 1已删除）")
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
