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
public class MineEditInfoVO {

    @ApiModelProperty(value = "头像")
    private String avatar;
}
