package com.intellicreation.common.exception;

import com.intellicreation.common.ResponseResult;
import com.intellicreation.common.enumtype.AppHttpCodeEnums;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


/**
 * 全局异常处理
 *
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
     * 处理数据校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult validationExceptionHandler(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        Map<String, String> errors = new HashMap<>();
        bindingResult.getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        // 只取第一个错误信息返回
        String firstErrorMessage = errors.values().stream().findFirst().orElse("输入格式错误");
        // 打印异常信息
        log.error("程序发生异常，参考异常信息 ----- code:{}, msg:{}", AppHttpCodeEnums.SYSTEM_ERROR.getCode(), firstErrorMessage);
        // 从异常对象中获取提示信息封装返回
        return ResponseResult.errorResult(AppHttpCodeEnums.BAD_REQUEST.getCode(), firstErrorMessage);
    }

    /**
     * 处理其他异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseResult exceptionHandler(Exception e) {
        // 打印异常信息
        log.error("程序发生异常，参考异常信息 ----- code:{}, msg:{}", AppHttpCodeEnums.SYSTEM_ERROR.getCode(), e.getMessage());
        // 从异常对象中获取提示信息封装返回
        return ResponseResult.errorResult(AppHttpCodeEnums.SYSTEM_ERROR.getCode(), AppHttpCodeEnums.SYSTEM_ERROR.getMsg());
    }
}
