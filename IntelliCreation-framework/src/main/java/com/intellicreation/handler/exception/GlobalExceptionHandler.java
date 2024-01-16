package com.intellicreation.handler.exception;

import com.intellicreation.domain.dto.ResponseResult;
import com.intellicreation.constant.AppHttpCodeEnums;
import com.intellicreation.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 全局异常处理
 * @author za
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理自定义系统异常
     */
    @ExceptionHandler(SystemException.class)
    public ResponseResult systemExceptionHandler(SystemException e) {
        // 打印异常信息
        log.error("程序发生异常，参考异常信息 ----- code:{}, msg:{}", e.getCode(), e.getMsg());
        // 从异常对象中获取提示信息封装返回
        return ResponseResult.errorResult(e.getCode(), e.getMsg());
    }

    /**
     * 处理其他异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseResult exceptionHandler(Exception e) {
        // 打印异常信息
        log.error("程序发生异常，参考异常信息 ----- code:{}, msg:{}", AppHttpCodeEnums.SYSTEM_ERROR.getCode(), e.getMessage());
        // 从异常对象中获取提示信息封装返回
        return ResponseResult.errorResult(AppHttpCodeEnums.SYSTEM_ERROR.getCode(), e.getMessage());
    }
}
