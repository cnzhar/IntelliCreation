package com.intellicreation.member.filter;

import com.alibaba.fastjson.JSON;
import com.intellicreation.common.enumtype.AppHttpCodeEnums;
import com.intellicreation.common.constant.SystemConstants;
import com.intellicreation.common.ResponseResult;
import com.intellicreation.common.util.JwtUtil;
import com.intellicreation.common.util.RedisCache;
import com.intellicreation.common.util.WebUtils;
import com.intellicreation.member.domain.dto.MemberDetailsDTO;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author za
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取请求头中的token
        String token = request.getHeader("Authorization");
        if (!StringUtils.hasText(token)) {
            // 直接放行
            filterChain.doFilter(request, response);
            return;
        }
        // 解析token
        String memberId;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            memberId = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            // token超时或token非法
            // 响应告诉前端需要重新登录
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnums.NEED_LOGIN);
            WebUtils.renderString(response, JSON.toJSONString(result));
            return;
        }
        // 从redis中获取用户信息
        String redisKey = SystemConstants.ADMIN_LOGIN_KEY + memberId;
        MemberDetailsDTO memberDetailsDTO = redisCache.getCacheObject(redisKey);
        if(Objects.isNull(memberDetailsDTO)){
            // 说明登录过期，提示重新登录
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnums.NEED_LOGIN);
            WebUtils.renderString(response, JSON.toJSONString(result));
            return;
        }
        // 存入SecurityContextHolder
        // 获取权限信息封装到Authentication中
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(memberDetailsDTO, null, memberDetailsDTO.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        // 放行
        filterChain.doFilter(request, response);
    }
}
