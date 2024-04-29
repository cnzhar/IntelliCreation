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
public class RatingViewVO {

    private Integer score;

    private String text;
}
