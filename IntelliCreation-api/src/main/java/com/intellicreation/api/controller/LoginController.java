package com.intellicreation.api.controller;

import com.intellicreation.api.facade.LoginFacade;
import com.intellicreation.common.ResponseResult;
import com.intellicreation.member.domain.entity.UmsMemberDO;
import com.intellicreation.member.domain.vo.AdminInfoVO;
import com.intellicreation.member.domain.vo.MenuVO;
import com.intellicreation.member.domain.vo.RouterVO;
import com.intellicreation.common.enumtype.AppHttpCodeEnums;
import com.intellicreation.common.exception.SystemException;
import com.intellicreation.member.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author za
 */
@RestController
public class LoginController {

    @Autowired
    private LoginFacade loginFacade;

    // Todo 改用logindto接收
    @PostMapping("/login")
    public ResponseResult login(@RequestBody UmsMemberDO umsMemberDO) {
        // todo 改为用注解校验
        if (!StringUtils.hasText(umsMemberDO.getUid())) {
            // 抛出异常，提示必须要传用户名
            throw new SystemException(AppHttpCodeEnums.REQUIRE_USERNAME);
        }
        Map<String, String> map = loginFacade.login(umsMemberDO);
        return ResponseResult.okResult(map);
    }

    @PostMapping("/logout")
    public ResponseResult logout() {
        loginFacade.logout();
        return new ResponseResult(200, "注销成功");
    }

    // todo getinfo和getrouter接口放到合适文件

    @GetMapping("getInfo")
    public ResponseResult<AdminInfoVO> getInfo() {
        AdminInfoVO adminInfoVO = loginFacade.getInfo();
        return ResponseResult.okResult(adminInfoVO);
    }

    @PreAuthorize("hasAuthority('system:test:list1')")
    @GetMapping("getRouter")
    public ResponseResult<RouterVO> getRouters() {
        Long memberId = SecurityUtils.getMemberId();
        // 查询menu，结果是tree的形式
        List<MenuVO> menus = loginFacade.selectRouterMenuTreeByMemberId(memberId);
        // 封装数据返回
        return ResponseResult.okResult(new RouterVO(menus));
    }
}
