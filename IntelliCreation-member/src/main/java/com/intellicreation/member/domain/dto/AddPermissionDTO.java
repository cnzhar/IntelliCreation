package com.intellicreation.member.domain.dto;


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
public class AddPermissionDTO {

    @NotEmpty(message = "权限名不能为空")
    private String permissionName;

    @NotEmpty(message = "权限Key不能为空")
    private String permissionKey;
}
