package com.intellicreation.member.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author za
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePermissionDTO {

    private Long id;

    private String permissionName;

    private String permissionKey;
}
