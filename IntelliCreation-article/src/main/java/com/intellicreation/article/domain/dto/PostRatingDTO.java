package com.intellicreation.article.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author za
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRatingDTO {

    @NotNull(message = "文章ID不能为空")
    private Long articleId;

    @NotNull(message = "评分不能为空")
    private Integer score;

    private String text;
}
