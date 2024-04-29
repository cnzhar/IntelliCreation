package com.intellicreation.article.domain.dto;


import io.swagger.annotations.ApiModelProperty;
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
public class CreatePostDTO {

    @NotEmpty(message = "标题不能为空")
    private String title;

    @NotEmpty(message = "内容不能为空")
    private String content;

    @ApiModelProperty(value = "缩略图")
    private String thumbnail;
}
