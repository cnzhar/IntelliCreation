package com.intellicreation.member.domain.dto;

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
public class UpdateMineInfoDTO {

    private Long id;

    @ApiModelProperty(value = "头像")
    private String avatar;
}
