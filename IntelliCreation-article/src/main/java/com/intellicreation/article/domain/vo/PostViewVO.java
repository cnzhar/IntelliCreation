package com.intellicreation.article.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author za
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostViewVO {

    private String title;

    private String content;

    @ApiModelProperty(value = "缩略图")
    private String thumbnail;

    private Long likeCount;

    private Long viewCount;

    private Long createBy;

    private String avatar;

    private String nickname;
}
