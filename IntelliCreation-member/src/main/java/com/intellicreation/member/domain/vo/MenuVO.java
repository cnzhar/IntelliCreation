package com.intellicreation.member.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 菜单权限表(Menu)表实体类
 *
 * @author makejava
 * @since 2022-08-09 23:47:50
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class MenuVO {

    /**
     * 菜单ID
     */
    private Long id;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 父菜单ID
     */
    private Long parent;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 是否为外链（0是 1否）
     */
    private Integer frame;

    /**
     * 菜单类型（I目录，M菜单，B按钮）
     */
    private String menuType;

    /**
     * 菜单状态（0显示 1隐藏）
     */
    private String visible;

    /**
     * 菜单状态（0正常，1停用）
     */
    private String status;

    /**
     * 描述信息
     */
    private String description;

    private List<MenuVO> children;
}
