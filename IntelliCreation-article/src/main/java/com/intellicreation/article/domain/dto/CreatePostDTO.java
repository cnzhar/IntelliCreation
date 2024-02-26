package com.intellicreation.article.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author za
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostDTO {

    private String title;

    private String content;
}
