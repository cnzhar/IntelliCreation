package com.intellicreation.member.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * @author za
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddMemberDTO {

    @NotEmpty(message = "UID不能为空")
    private String uid;

    @NotEmpty(message = "昵称不能为空")
    private String nickname;

    @NotEmpty(message = "电子邮件不能为空")
    @Email(message = "电子邮件格式不正确")
    private String email;
}
