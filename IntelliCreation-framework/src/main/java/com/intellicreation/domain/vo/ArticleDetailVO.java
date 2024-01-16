package com.intellicreation.domain.vo;

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
public class ArticleDetailVO {

    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 文章摘要
     */
    private String summary;
    /**
     * 所属分类id
     */
    private Long category1Id;
    /**
     * 所属分类名
     */
    private String category1Name;
    /**
     * 缩略图
     */
    private String thumbnail;
    /**
     * 文章内容
     */
    private String content;
    /**
     * 访问量
     */
    private Long viewCount;

    private Date createTime;

}
