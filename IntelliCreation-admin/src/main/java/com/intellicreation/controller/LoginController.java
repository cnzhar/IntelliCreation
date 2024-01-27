package com.intellicreation.controller;

import com.intellicreation.domain.dto.LoginMemberDTO;
import com.intellicreation.domain.dto.ResponseResult;
import com.intellicreation.domain.model.UmsMemberDO;
import com.intellicreation.domain.model.UmsMenu;
import com.intellicreation.domain.vo.AdminInfoVO;
import com.intellicreation.domain.vo.MemberInfoVO;
import com.intellicreation.domain.vo.MenuVO;
import com.intellicreation.domain.vo.RouterVO;
import com.intellicreation.enumtype.AppHttpCodeEnums;
import com.intellicreation.exception.SystemException;
import com.intellicreation.service.AdminLoginService;
import com.intellicreation.service.UmsMenuService;
import com.intellicreation.service.UmsPermissionService;
import com.intellicreation.service.UmsRoleService;
import com.intellicreation.util.BeanCopyUtils;
import com.intellicreation.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author za
 */
@RestController
public class LoginController {

    @Autowired
    private AdminLoginService adminLoginService;

    @Autowired
    private UmsPermissionService umsPermissionService;

    @Autowired
    private UmsRoleService umsRoleService;

    @Autowired
    private UmsMenuService umsMenuService;

    // Todo 改用logindto接收
    @PostMapping("/login")
    public ResponseResult login(@RequestBody UmsMemberDO umsMemberDO) {
        if (!StringUtils.hasText(umsMemberDO.getUid())) {
            // 抛出异常，提示必须要传用户名
            throw new SystemException(AppHttpCodeEnums.REQUIRE_USERNAME);
        }
        return adminLoginService.login(umsMemberDO);
    }

    @PostMapping("/logout")
    public ResponseResult logout(){
        return adminLoginService.logout();
    }

    // todo getinfo和getrouter接口放到合适文件

    // todo 以下内容放在controller是不是不太合适
    @GetMapping("getInfo")
    public ResponseResult<AdminInfoVO> getInfo() {
        // 获取当前登录的用户
        LoginMemberDTO loginMemberDTO = SecurityUtils.getLoginMember();
        // 根据用户id查询权限信息
        List<String> perms = umsPermissionService.selectPermissionByMemberId(loginMemberDTO.getUmsMemberDO().getId());
        // 根据用户id查询角色信息
        List<String> roleKeyList = umsRoleService.selectRoleKeyByMemberId(loginMemberDTO.getUmsMemberDO().getId());
        // 获取用户信息
        UmsMemberDO umsMemberDO = loginMemberDTO.getUmsMemberDO();
        MemberInfoVO memberInfoVO = BeanCopyUtils.copyBean(umsMemberDO, MemberInfoVO.class);
        // 封装数据返回
        AdminInfoVO adminInfoVO = new AdminInfoVO(perms, roleKeyList, memberInfoVO);
        return ResponseResult.okResult(adminInfoVO);
    }

    @PreAuthorize("hasAuthority('system:test:list1')")
    @GetMapping("getRouters")
    public ResponseResult<RouterVO> getRouters(){
        Long memberId = SecurityUtils.getMemberId();
        // 查询menu 结果是tree的形式
        List<MenuVO> menus = umsMenuService.selectRouterMenuTreeByMemberId(memberId);
        // 封装数据返回
        System.out.println(menus);
        return ResponseResult.okResult(new RouterVO(menus));
    }


}
