package com.intellicreation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.intellicreation.mapper.UmsMemberMapper;
import com.intellicreation.mapper.UmsPermissionMapper;
import com.intellicreation.domain.dto.LoginMemberDTO;
import com.intellicreation.domain.model.UmsMemberDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author za
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UmsMemberMapper umsMemberMapper;

    @Autowired
    private UmsPermissionMapper umsPermissionMapper;

    @Override
    public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {
        // 查询用户信息
        LambdaQueryWrapper<UmsMemberDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UmsMemberDO::getUid, uid);
        UmsMemberDO umsMemberDO = umsMemberMapper.selectOne(queryWrapper);
        // 如果没有查询到用户就抛出异常
        if(Objects.isNull(umsMemberDO)) {
            throw new RuntimeException("用户名或者密码错误");
        }
        // todo 查询权限信息封装
        List<String> permissionList = umsPermissionMapper.selectPermissionByMemberId(umsMemberDO.getId());
        // 把数据封装成UserDetails返回
        return new LoginMemberDTO(umsMemberDO, permissionList);
    }
}
