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
public class MenuVO {

    private Long id;

    private String menuName;

    private Long parent;

    private Integer sort;


}
