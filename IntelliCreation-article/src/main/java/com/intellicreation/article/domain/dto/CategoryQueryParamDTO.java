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
public class CategoryQueryParamDTO {

    private Long id;

    private String name;

    private String description;
}
