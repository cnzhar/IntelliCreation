package com.intellicreation.member.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author za
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadMineCardInfoVO {

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "文章数量")
    private Long articleCount;

    @ApiModelProperty(value = "文章评价数量")
    private Long ratingCount;

    @ApiModelProperty(value = "经验")
    private Integer exp;
}
