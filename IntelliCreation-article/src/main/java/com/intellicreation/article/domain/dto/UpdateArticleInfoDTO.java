package com.intellicreation.article.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author za
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateArticleInfoDTO {

    private Long id;

    private String title;

    @ApiModelProperty(value = "文章摘要")
    private String summary;

    @ApiModelProperty(value = "缩略图")
    private String thumbnail;

    private String content;

    @ApiModelProperty(value = "文章状态（0为草稿，1为审核中，2为审核通过，3为审核不通过，4为已发布")
    private String status;

    @ApiModelProperty(value = "默认为非分类1")
    private Long category1Id;

    @ApiModelProperty(value = "默认为非分类1")
    private Long category2Id;

    private List<Long> tag;
}
