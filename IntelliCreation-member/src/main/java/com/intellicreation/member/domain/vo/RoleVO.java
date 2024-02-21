package com.intellicreation.member.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    private String status;

    private String description;

    private Long createBy;

    private Long modifiedBy;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;
}
