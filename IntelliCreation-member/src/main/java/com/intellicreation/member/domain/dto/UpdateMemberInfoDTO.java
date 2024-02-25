package com.intellicreation.member.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * @author za
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMemberInfoDTO {

    private Long id;

    // todo 改为校验不能为空字符串

    @ApiModelProperty(value = "用户唯一编号")
    @NotEmpty(message = "UID不能为空")
    private String uid;

    @ApiModelProperty(value = "昵称")
//    @NotEmpty(message = "昵称不能为空")
    private String nickname;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "电子邮件")
    private String email;
}
