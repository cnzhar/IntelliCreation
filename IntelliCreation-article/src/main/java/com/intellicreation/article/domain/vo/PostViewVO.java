package com.intellicreation.article.domain.vo;

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

    private Long likeCount;

    private Long viewCount;

    private Long createBy;

    private String avatar;

    private String nickname;
}
