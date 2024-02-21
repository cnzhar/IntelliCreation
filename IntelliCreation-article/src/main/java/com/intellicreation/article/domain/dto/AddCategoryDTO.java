package com.intellicreation.article.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * @author za
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCategoryDTO {

    private Long id;

    private Long parent;

    @NotEmpty(message = "分类名不能为空")
    private String name;

    private Integer sort;

    private String description;
}
