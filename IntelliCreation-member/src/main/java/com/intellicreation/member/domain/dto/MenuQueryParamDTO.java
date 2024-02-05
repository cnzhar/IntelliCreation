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
public class MenuQueryParamDTO {

    private Long id;

    private String icon;

    private String menuName;

    private Long parent;

    private Integer sort;

    private String path;
}
