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
public class CommunityMineCardInfoVO {

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "帖子数量")
    private Long postCount;

    @ApiModelProperty(value = "帖子评论数量")
    private Long postCommentCount;

    @ApiModelProperty(value = "经验")
    private Integer exp;
}
