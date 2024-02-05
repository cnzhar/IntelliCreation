package com.intellicreation.member.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author za
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleVO {

    private Long id;

    private String roleName;

    private String roleKey;
}
