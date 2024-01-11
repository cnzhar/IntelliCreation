//package com.intellicreation.service.impl;
//
//import com.intellicreation.model.domain.LoginMember;
//import com.intellicreation.model.domain.ResponseResult;
//import com.intellicreation.domain.UmsMember;
//import com.intellicreation.service.LoginServcie;
//import com.intellicreation.utils.JwtUtil;
//import com.intellicreation.utils.RedisCache;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Objects;
//
///**
// * @author za
// */
//@Service
//public class LoginServiceImpl implements LoginServcie {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private RedisCache redisCache;
//
//    @Override
//    public ResponseResult login(UmsMember umsMember) {
//        //AuthenticationManager authenticate进行用户认证
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(umsMember.getUid(), umsMember.getPassword());
//        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
//        //如果认证没通过，给出对应的提示
//        if(Objects.isNull(authenticate)){
//            throw new RuntimeException("登录失败");
//        }
//        //如果认证通过了，使用userid生成一个jwt jwt存入ResponseResult返回
//        LoginMember loginMember = (LoginMember) authenticate.getPrincipal();
//        String userid = loginMember.getUmsMember().getId().toString();
//        String jwt = JwtUtil.createJWT(userid);
//        Map<String,String> map = new HashMap<>();
//        map.put("token", jwt);
//        //把完整的用户信息存入redis  userid作为key
//        redisCache.setCacheObject("login:" + userid, loginMember);
//        return new ResponseResult(200,"登录成功", map);
//    }
//
//    @Override
//    public ResponseResult logout() {
//        //获取SecurityContextHolder中的用户id
//        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
//        LoginMember loginMember = (LoginMember) authentication.getPrincipal();
//        Long memberId = loginMember.getUmsMember().getId();
//        //删除redis中的值
//        redisCache.deleteObject("login:" + memberId);
//        return new ResponseResult(200,"注销成功");
//    }
//}
