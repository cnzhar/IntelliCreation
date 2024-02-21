package com.intellicreation.member.domain.vo;

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
public class PermissionVO {

    private Long id;

    private String permissionName;

    private String permissionKey;

    private String permissionType;

    private String status;

    private Long createBy;

    private Long modifiedBy;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;
}
