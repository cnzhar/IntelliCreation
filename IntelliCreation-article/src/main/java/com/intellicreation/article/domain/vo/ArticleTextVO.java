package com.intellicreation.article.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author za
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleTextVO {

    private Long id;

    private String title;

    private Long authorId;

    @ApiModelProperty(value = "文章摘要")
    private String summary;

    @ApiModelProperty(value = "缩略图")
    private String thumbnail;

    private String content;

    @ApiModelProperty(value = "点赞数")
    private Long likeCount;

    @ApiModelProperty(value = "阅读量")
    private Long viewCount;

    /**
     * 所属分类id
     */
    private Long category1Id;

    /**
     * 所属分类名
     */
    private String category1Name;

    private Date createTime;

}
