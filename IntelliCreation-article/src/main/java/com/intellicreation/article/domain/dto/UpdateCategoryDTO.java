package com.intellicreation.article.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author za
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCategoryDTO {

    private Long id;

    private Long parent;

    private String name;

    private Integer sort;

    private String description;
}
