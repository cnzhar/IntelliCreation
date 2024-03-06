package com.intellicreation.member.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author za
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MineBasicInfoVO {

    @ApiModelProperty(value = "用户唯一编号")
    private String uid;

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

}
