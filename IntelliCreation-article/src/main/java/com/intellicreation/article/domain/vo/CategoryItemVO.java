package com.intellicreation.article.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author za
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CategoryItemVO {

    private Long id;

    private Long parent;

    private String name;

    /**
     * 描述
     */
    private String description;

    private List<CategoryItemVO> children;
}
