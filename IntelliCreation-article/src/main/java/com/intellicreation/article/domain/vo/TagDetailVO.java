package com.intellicreation.article.domain.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author za
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagDetailVO {

    private Long id;

    private String name;

    private String description;

    private Long createBy;

    private Long modifiedBy;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;
}
