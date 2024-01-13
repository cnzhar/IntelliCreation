package com.intellicreation.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author za
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AmsCategoryVO {

    private Long id;
    private String name;
    /**
     * 描述
     */
    private String description;
}
