package com.intellicreation.article.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author za
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MineArticleListVO {

    private Long id;

    private String title;

    @ApiModelProperty(value = "文章摘要")
    private String summary;

    @ApiModelProperty(value = "缩略图")
    private String thumbnail;

    @ApiModelProperty(value = "所属分类id")
    private Long category1Id;

    @ApiModelProperty(value = "所属分类名称")
    private String category1Name;

    private List<String> tagName;

    @ApiModelProperty(value = "点赞数")
    private Long likeCount;

    @ApiModelProperty(value = "访问量")
    private Long viewCount;

    private LocalDateTime gmtCreate;
}
