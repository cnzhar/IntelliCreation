package com.intellicreation.service.impl;

import com.intellicreation.domain.dto.LoginMemberDTO;
import com.intellicreation.domain.dto.ResponseResult;
import com.intellicreation.domain.model.UmsMemberDO;
import com.intellicreation.domain.vo.MemberInfoVO;
import com.intellicreation.domain.vo.MemberLoginVO;
import com.intellicreation.service.LoginService;
import com.intellicreation.util.BeanCopyUtils;
import com.intellicreation.util.JwtUtil;
import com.intellicreation.util.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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
    public ResponseResult login(UmsMemberDO umsMemberDO) {
        // AuthenticationManager authenticate进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(umsMemberDO.getUid(), umsMemberDO.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        // 如果认证没通过，给出对应的提示
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或密码错误");
        }
        // 如果认证通过了，使用userid生成一个jwt jwt存入ResponseResult返回
        LoginMemberDTO loginMemberDTO = (LoginMemberDTO) authenticate.getPrincipal();
        String memberId = loginMemberDTO.getUmsMemberDO().getId().toString();
        String jwt = JwtUtil.createJWT(memberId);
        // 把完整的用户信息存入redis，memberId作为key
        redisCache.setCacheObject("memberLogin:" + memberId, loginMemberDTO);
        // 把UmsMemberDO转为memberInfoVO
        MemberInfoVO memberInfoVO = BeanCopyUtils.copyBean(loginMemberDTO.getUmsMemberDO(), MemberInfoVO.class);
        // 把token和memberInfo封装，返回
        MemberLoginVO memberLoginVO = new MemberLoginVO(jwt, memberInfoVO);
        return ResponseResult.okResult(memberLoginVO);
    }

    @Override
    public ResponseResult logout() {
        // 获取SecurityContextHolder中的用户id
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginMemberDTO loginMemberDTO = (LoginMemberDTO) authentication.getPrincipal();
        // 获取id
        Long memberId = loginMemberDTO.getUmsMemberDO().getId();
        // 删除redis中的值
        redisCache.deleteObject("memberLogin:" + memberId);
        return new ResponseResult(200, "注销成功");
    }
}
