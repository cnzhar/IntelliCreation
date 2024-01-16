package com.intellicreation.exception;

import com.intellicreation.constant.AppHttpCodeEnums;

/**
 * 自定义系统异常
 * @author za
 */
public class SystemException extends RuntimeException {

    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public SystemException(AppHttpCodeEnums httpCodeEnums) {
        super(httpCodeEnums.getMsg());
        this.code = httpCodeEnums.getCode();
        this.msg = httpCodeEnums.getMsg();
    }

}
