package com.intellicreation.constant;

/**
 * @author za
 */
public class SystemConstants {

    /**
     * 用户登录信息在redis中的key
     */
    public static final String MEMBER_LOGIN_KEY = "memberLogin:";

    /**
     * 文章浏览量在Redis中的key
     */
    public static final String ARTICLE_VIEW_COUNT_KEY = "article:viewCount";

    /**
     * 通用状态
     * 状态为正常
     */
    public static final String  STATUS_NORMAL = "0";

    /**
     * 通用状态
     * 状态为禁用
     */
    public static final String  STATUS_DISABLED = "1";

    /**
     *  文章是草稿状态
     */
    public static final int ARTICLE_STATUS_DRAFT = 0;

    /**
     *  文章是审核中状态
     */
    public static final int ARTICLE_STATUS_REVIEWING = 1;

    /**
     *  文章是审核通过状态
     */
    public static final int ARTICLE_STATUS_APPROVED = 2;

    /**
     *  文章是审核不通过状态
     */
    public static final int ARTICLE_STATUS_REJECTED = 3;

    /**
     *  文章是已发布状态
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
}
