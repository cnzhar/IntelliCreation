package com.intellicreation.member.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author za
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddMenuDTO {

    @NotEmpty(message = "菜单名不能为空")
    private String menuName;

    @NotNull(message = "父菜单不能为空")
    private Long parent;

    private Integer sort;
}
