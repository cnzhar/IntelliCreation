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
public class RoleQueryParamDTO {

    private Long id;

    private String roleName;

    private String roleKey;
}
