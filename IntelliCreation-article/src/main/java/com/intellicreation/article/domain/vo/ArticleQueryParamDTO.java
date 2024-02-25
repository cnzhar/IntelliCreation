package com.intellicreation.article.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author za
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleQueryParamDTO {

    private Long id;

    private String title;

    private Long authorId;
}
