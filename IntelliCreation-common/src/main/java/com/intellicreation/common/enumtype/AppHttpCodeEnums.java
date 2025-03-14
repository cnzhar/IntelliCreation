package com.intellicreation.common.enumtype;

/**
 * @author za
 */
public enum AppHttpCodeEnums {

    /**
     * 操作成功
     */
    SUCCESS(200, "操作成功"),

    /**
     * 请求格式错误
     */
    BAD_REQUEST(400, "请求格式错误"),

    /**
     * 需要登录后操作
     */
    NEED_LOGIN(401, "未登录或登录已过期，请重新登录"),

    /**
     * 无权限操作
     */
    NO_OPERATOR_AUTH(403, "无权限操作"),

    /**
     * 重复点赞
     */
    DUPLICATE_LIKE(409, "重复点赞"),

    /**
     * 尚未点赞
     */
    NOT_YET_LIKED(410, "尚未点赞"),

    /**
     * 出现错误
     */
    SYSTEM_ERROR(500, "服务出现错误"),

    /**
     * 用户名已存在
     */
    USERNAME_EXIST(501, "用户名已存在"),

    /**
     * 手机号已存在
     */
    PHONE_NUMBER_EXIST(502, "手机号已存在"),

    /**
     * 邮箱已存在
     */
    EMAIL_EXIST(503, "邮箱已存在"),

    /**
     * 必须填写用户名
     */
    REQUIRE_USERNAME(504, "必须填写用户名"),

    /**
     * 用户名或密码错误
     */
    LOGIN_ERROR(505, "用户名或密码错误"),

    /**
     * 评论内容不能为空
     */
    CONTENT_NOT_NULL(506, "评论内容不能为空"),

    /**
     * 文件类型错误，请上传png文件
     */
    FILE_TYPE_ERROR(507, "文件类型错误，请上传png文件"),

    /**
     * 图片不能为空
     */
    IMAGE_NOT_NULL(508, "图片不能为空"),

    /**
     * 用户名不能为空
     */
    USERNAME_NOT_NULL(509, "用户名不能为空"),

    /**
     * 昵称不能为空
     */
    NICKNAME_NOT_NULL(509, "昵称不能为空"),

    /**
     * 密码不能为空
     */
    PASSWORD_NOT_NULL(510, "密码不能为空"),

    /**
     * 邮箱不能为空
     */
    EMAIL_NOT_NULL(511, "邮箱不能为空"),

    /**
     * 昵称已存在
     */
    NICKNAME_EXIST(512, "昵称已存在"),

    /**
     * 内容违规
     */
    CONTAIN_ILLEGALWORD(513, "内容违规");

    int code;
    String msg;

    AppHttpCodeEnums(int code, String errorMessage) {
        this.code = code;
        this.msg = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
