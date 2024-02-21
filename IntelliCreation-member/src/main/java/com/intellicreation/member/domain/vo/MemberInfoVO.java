package com.intellicreation.member.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author za
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberInfoVO {

    private Long id;

    @ApiModelProperty(value = "用户唯一编号")
    private String uid;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "电子邮件")
    private String email;

    @ApiModelProperty(value = "电子邮件激活状态（0未激活 1已激活）")
    private String emailStatus;

    @ApiModelProperty(value = "手机号码")
    private String phoneNumber;

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

    @ApiModelProperty(value = "所在城市")
    private Long locationId;

    @ApiModelProperty(value = "职业")
    private String job;

    @ApiModelProperty(value = "公司")
    private String company;

    @ApiModelProperty(value = "qq号")
    private Integer qqNumber;

    @ApiModelProperty(value = "创建人")
    private Long createBy;

    @ApiModelProperty(value = "更新人")
    private Long modifiedBy;

    @ApiModelProperty(value = "创建时间，即注册时间")
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "最后修改时间")
    private LocalDateTime gmtModified;


}
