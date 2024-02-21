package com.intellicreation.common.constant;

/**
 * @author za
 */
public class SystemConstants {

    /**
     * Bearer Token前缀
     */
    public static final String BearerTokenPrefix = "Bearer ";

    /**
     * 管理员登录信息在redis中的key
     */
    public static final String LOGIN_KEY = "memberLogin:";

    /**
     * 文章浏览量在Redis中的key
     */
    public static final String ARTICLE_VIEW_COUNT_KEY = "article:viewCount";

    /**
     * 通用状态
     * 状态为正常
     */
    public static final String STATUS_NORMAL = "0";

    /**
     * 通用状态
     * 状态为禁用
     */
    public static final String STATUS_DISABLED = "1";

    /**
     * 无父元素
     */
    public static final Long NULL_PARENT = 0L;

    /**
     * 文章是草稿状态
     */
    public static final int ARTICLE_STATUS_DRAFT = 0;

    /**
     * 文章是审核中状态
     */
    public static final int ARTICLE_STATUS_REVIEWING = 1;

    /**
     * 文章是审核通过状态
     */
    public static final int ARTICLE_STATUS_APPROVED = 2;

    /**
     * 文章是审核不通过状态
     */
    public static final int ARTICLE_STATUS_REJECTED = 3;

    /**
     * 文章是已发布状态
     */
    public static final int ARTICLE_STATUS_PUBLISHED = 4;

    /**
     * 评论是根评论
     */
    public static final long COMMENT_ROOT = 0;

    /**
     * 不对任何用户进行回复
     */
    public static final long COMMENT_TO_NULL = 0;

    /**
     * 超级管理员ID
     */
    public static final long ID_OF_SUPER_ADMIN = 1L;

    /**
     * 超级管理员 role ley
     */
    public static final String KEY_OF_SUPER_ADMIN = "Super Admin";

    /**
     * 菜单权限
     */
    public static final String MENU_PERMISSION = "M";

    /**
     * 按钮权限
     */
    public static final String BUTTON_PERMISSION = "B";

    /**
     * 新建账号默认密码
     */
    public static String defaultPassword = "123456";
}
