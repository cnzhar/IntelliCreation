package com.intellicreation.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author za
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SystemLog {

    /**
     * 功能名称
     */
    String businessName();

    /**
     * 是否保存请求的参数
     */
    boolean isSaveRequestArg() default true;

    /**
     * 是否保存响应的参数
     */
    boolean isSaveResponseData() default true;

    /**
     * 操作类型
     */
    String operationType();
}
