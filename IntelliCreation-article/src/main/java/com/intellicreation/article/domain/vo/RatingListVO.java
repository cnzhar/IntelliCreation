package com.intellicreation.article.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author za
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class RatingListVO {

    private Long id;

    private Integer score;

    private String text;

    @ApiModelProperty(value = "创建人")
    private Long createBy;

    private String avatar;

    private String nickname;
}
