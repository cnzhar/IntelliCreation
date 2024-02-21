package com.intellicreation.article.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
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
public class CategoryDetailVO {

    private Long id;

    private Long parent;

    private String name;

    private Integer sort;

    private String description;

    private String status;

    private Long createBy;

    private Long modifiedBy;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;
}
