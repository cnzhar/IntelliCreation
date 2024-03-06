package com.intellicreation.api.aspect;

import com.alibaba.fastjson.JSON;
import com.intellicreation.api.annotation.SystemLog;
import com.intellicreation.common.constant.SystemConstants;
import com.intellicreation.common.util.IpRegionUtils;
import com.intellicreation.common.util.RequestUtil;
import com.intellicreation.member.domain.entity.UmsOperationLogDO;
import com.intellicreation.member.service.UmsOperationLogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author za
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

    @Autowired
    private UmsOperationLogService umsOperationLogService;

    /**
     * 保存日志实体类
     */
    private final UmsOperationLogDO umsOperationLogDO = new UmsOperationLogDO();

    /**
     * 获取被增强方法上的注解对象
     */
    SystemLog systemLog;

    @Pointcut("@annotation(com.intellicreation.api.annotation.SystemLog)")
    public void pt() {

    }

    @Around("pt()")
    public Object generateLog(ProceedingJoinPoint joinPoint) throws Throwable {

        Object ret;
        systemLog = getSystemLog(joinPoint);
        try {
            handleBefore(joinPoint);
            ret = joinPoint.proceed();
            handleAfter(ret);
        } catch (Throwable throwable) {
            handleError(throwable);
            // 重新抛出异常
            throw throwable;
        } finally {
            // 结束后换行
            log.info("======= END =======" + System.lineSeparator());
            umsOperationLogDO.setType(systemLog.operationType());
            umsOperationLogService.save(umsOperationLogDO);
        }

        return ret;
    }

    private void handleBefore(ProceedingJoinPoint joinPoint) {

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request = requestAttributes.getRequest();
        log.info("======= START =======");
        // 获取参数, 打印日志, 设置日志实体类参数
        // 请求 URL
        String url = request.getRequestURL().toString();
        log.info("URL            : {}", url);
        umsOperationLogDO.setUrl(url);
        // 功能名称
        String businessName = systemLog.businessName();
        log.info("BusinessName   : {}", businessName);
        umsOperationLogDO.setBusinessName(businessName);
        // Http method
        String httpMethod = request.getMethod();
        log.info("HTTP Method    : {}", httpMethod);
        umsOperationLogDO.setHttpMethod(httpMethod);
        // 调用 controller 的全路径以及执行方法
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + joinPoint.getSignature().getName();
        log.info("Class Method   : {}", classMethod);
        umsOperationLogDO.setClassMethod(classMethod);
        // 请求的 IP
        String ip = RequestUtil.getRequestIp(request);
        log.info("IP             : {}", ip);
        umsOperationLogDO.setIp(ip);
        // IP属地
        String ipRegion = IpRegionUtils.getIpRegion(ip);
        log.info("Region         : {}", ipRegion);
        umsOperationLogDO.setRegion(ipRegion);
        // user-agent
        String userAgent = request.getHeader("User-Agent");
        log.info("User-Agent     : {}", userAgent);
        umsOperationLogDO.setUserAgent(userAgent);
        // 请求入参
        if (systemLog.isSaveRequestArg()) {
            String requestArgs = JSON.toJSONString(joinPoint.getArgs());
            log.info("Request Args   : {}", requestArgs);
            umsOperationLogDO.setRequestArg(requestArgs);
        }
    }

    private void handleAfter(Object ret) {
        umsOperationLogDO.setStatus(SystemConstants.STATUS_SUCCESS);
        // 出参
        if (systemLog.isSaveResponseData()) {
            String responseDate = JSON.toJSONString(ret);
            log.info("Response       : {}", responseDate);
            umsOperationLogDO.setResponseData(responseDate);
        }

    }

    private void handleError(Throwable throwable) {
        // 异常信息
        String exception = throwable.toString();
        log.info("Error          : {}", exception);
        umsOperationLogDO.setStatus(SystemConstants.STATUS_ERROR);
        umsOperationLogDO.setExceptionMsg(exception);
    }

    /**
     * 获取被增强方法上的注解对象
     *
     * @param joinPoint
     * @return
     */
    private SystemLog getSystemLog(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        SystemLog systemLog = methodSignature.getMethod().getAnnotation(SystemLog.class);
        return systemLog;
    }
}
