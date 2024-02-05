package com.intellicreation.api.facade.impl;

import com.intellicreation.api.facade.LoginFacade;
import com.intellicreation.common.util.BeanCopyUtils;
import com.intellicreation.member.domain.dto.MemberDetailsDTO;
import com.intellicreation.member.domain.entity.UmsMemberDO;
import com.intellicreation.member.domain.vo.AdminInfoVO;
import com.intellicreation.member.domain.vo.MemberInfoVO;
import com.intellicreation.member.domain.vo.MenuVO;
import com.intellicreation.member.service.LoginService;
import com.intellicreation.member.service.UmsMenuService;
import com.intellicreation.member.service.UmsPermissionService;
import com.intellicreation.member.service.UmsRoleService;
import com.intellicreation.member.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author za
 */
@Component
public class LoginFacadeImpl implements LoginFacade {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UmsPermissionService umsPermissionService;

    @Autowired
    private UmsRoleService umsRoleService;

    @Autowired
    private UmsMenuService umsMenuService;

    @Override
    public Map<String, String> login(UmsMemberDO umsMemberDO) {
        return loginService.login(umsMemberDO);
    }

    @Override
    public void logout() {
        loginService.logout();
    }

    @Override
    public AdminInfoVO getInfo() {
        // todo 把封装VO放到controller
        // 获取当前登录的用户
        MemberDetailsDTO memberDetailsDTO = SecurityUtils.getLoginMember();
        // 根据用户id查询权限信息
        List<String> perms = umsPermissionService.selectPermissionByMemberId(memberDetailsDTO.getUmsMemberDO().getId());
        // 根据用户id查询角色信息
        List<String> roleKeyList = umsRoleService.selectRoleKeyByMemberId(memberDetailsDTO.getUmsMemberDO().getId());
        // 获取用户信息
        UmsMemberDO umsMemberDO = memberDetailsDTO.getUmsMemberDO();
        MemberInfoVO memberInfoVO = BeanCopyUtils.copyBean(umsMemberDO, MemberInfoVO.class);
        // 封装数据返回
        AdminInfoVO adminInfoVO = new AdminInfoVO(perms, roleKeyList, memberInfoVO);
        return adminInfoVO;
    }

    @Override
    public List<MenuVO> selectRouterMenuTreeByMemberId(Long memberId) {
        return umsMenuService.selectRouterMenuTreeByMemberId(memberId);
    }
}
