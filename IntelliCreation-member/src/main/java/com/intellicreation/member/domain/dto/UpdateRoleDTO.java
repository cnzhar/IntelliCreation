package com.intellicreation.member.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author za
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRoleDTO {

    private Long id;

    private String roleName;

    private String roleKey;

    private String status;

    private String description;
}
