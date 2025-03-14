package com.intellicreation.member.handler;

import com.alibaba.fastjson.JSON;
import com.intellicreation.common.ResponseResult;
import com.intellicreation.common.enumtype.AppHttpCodeEnums;
import com.intellicreation.common.util.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 授权失败处理器
 *
 * @author za
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // print异常信息
        accessDeniedException.printStackTrace();
        ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnums.NO_OPERATOR_AUTH);
        // 响应给前端
        WebUtils.renderString(response, JSON.toJSONString(result));
    }
}
