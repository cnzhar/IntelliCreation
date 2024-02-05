package com.intellicreation.member.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

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
@TableName("ums_member")
@ApiModel(value="UmsMemberDO对象", description="")
public class UmsMemberDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户唯一编号")
    private String uid;

    @ApiModelProperty(value = "昵称")
    @NotNull(message = "昵称不能为空")
    private String nickname;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "电子邮件")
    @NotNull(message = "电子邮件不能为空")
    @Email(message = "电子邮件格式不正确")
    private String email;

    @ApiModelProperty(value = "电子邮件激活状态（0未激活 1已激活）")
    private String emailStatus;

    @ApiModelProperty(value = "手机号码")
    private Integer phoneNumber;

    @ApiModelProperty(value = "手机号码激活状态")
    private Integer phoneNumberStatus;

    @ApiModelProperty(value = "真实姓名")
    private String fullName;

    @ApiModelProperty(value = "性别，0为男，1为女， 2为保密")
    private String gender;

    @ApiModelProperty(value = "生日")
    private LocalDate birthday;

    @ApiModelProperty(value = "个性签名")
    private String personalizedSignature;

    @ApiModelProperty(value = "账号状态（0正常 1停用）")
    private String status;

    @ApiModelProperty(value = "经验")
    private Integer exp;

    @ApiModelProperty(value = "会员等级")
    private Long vipLevelId;

    @ApiModelProperty(value = "所在城市")
    private Long locationId;

    @ApiModelProperty(value = "职业")
    private String job;

    @ApiModelProperty(value = "公司")
    private String company;

    @ApiModelProperty(value = "qq号")
    private Integer qqNumber;

    @ApiModelProperty(value = "是否被删除（0为未删除，1为已删除）")
    @TableField("is_deleted")
    private Integer deleted;

    @ApiModelProperty(value = "创建人")
    private Long createBy;

    @ApiModelProperty(value = "更新人")
    private Long modifiedBy;

    @ApiModelProperty(value = "创建时间，即注册时间")
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "最后修改时间")
    private LocalDateTime gmtModified;


}
