package com.intellicreation.constant;

/**
 * @author za
 */
public class SystemConstants {

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


}
