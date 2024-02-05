package com.intellicreation.member.service.impl;

import com.intellicreation.common.constant.SystemConstants;
import com.intellicreation.member.domain.dto.MemberDetailsDTO;
import com.intellicreation.member.domain.entity.UmsMemberDO;
import com.intellicreation.member.service.LoginService;
import com.intellicreation.common.util.JwtUtil;
import com.intellicreation.common.util.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author za
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public Map<String, String> login(UmsMemberDO umsMemberDO) {
        // AuthenticationManager authenticate进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(umsMemberDO.getUid(), umsMemberDO.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        // 如果认证没通过，给出对应的提示
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或密码错误");
        }
        // 如果认证通过了，使用memberId生成一个jwt jwt存入ResponseResult返回
        MemberDetailsDTO memberDetailsDTO = (MemberDetailsDTO) authenticate.getPrincipal();
        String memberId = memberDetailsDTO.getUmsMemberDO().getId().toString();
        String jwt = JwtUtil.createJWT(memberId);
        // 把完整的用户信息存入redis，memberId作为key
        redisCache.setCacheObject(SystemConstants.ADMIN_LOGIN_KEY + memberId, memberDetailsDTO);
        // 把token封装 返回
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);
        return map;
    }

    @Override
    public void logout() {
        // todo 看要不要改成用securityutil中的方法
        // 获取SecurityContextHolder中的用户id
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MemberDetailsDTO memberDetailsDTO = (MemberDetailsDTO) authentication.getPrincipal();
        // 获取id
        Long memberId = memberDetailsDTO.getUmsMemberDO().getId();
        // 删除redis中的值
        redisCache.deleteObject(SystemConstants.ADMIN_LOGIN_KEY + memberId);
    }
}
