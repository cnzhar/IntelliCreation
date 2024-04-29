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
public class AddTagDTO {

    @NotEmpty(message = "标签名不能为空")
    private String name;

    private String description;
}
