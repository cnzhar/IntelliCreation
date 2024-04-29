package com.intellicreation.api.controller;

import com.intellicreation.api.facade.MemberFacade;
import com.intellicreation.common.ResponseResult;
import com.intellicreation.common.vo.PageVO;
import com.intellicreation.member.domain.dto.LoginMemberDTO;
import com.intellicreation.member.domain.vo.AdminInfoVO;
import com.intellicreation.member.domain.vo.MenuItemVO;
import com.intellicreation.member.domain.vo.RouterVO;
import com.intellicreation.member.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author za
 */
@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberFacade memberFacade;

    @PostMapping("/login")
    public ResponseResult login(@Valid @RequestBody LoginMemberDTO loginMemberDTO) {
        Map<String, String> map = memberFacade.login(loginMemberDTO);
        return ResponseResult.okResult(map);
    }

    @PostMapping("/logout")
    public ResponseResult logout() {
        memberFacade.logout();
        return new ResponseResult(200, "注销成功");
    }

    @GetMapping("getInfo")
    public ResponseResult<AdminInfoVO> getInfo() {
        AdminInfoVO adminInfoVO = memberFacade.getInfo();
        return ResponseResult.okResult(adminInfoVO);
    }

    @GetMapping("isAdmin")
    public ResponseResult isAdmin() {
        Long id = SecurityUtils.getMemberId();
        Map<String, Object> result = new HashMap<>();
        if (id != 1L && id != 2L) {
            result.put("isAdmin", false);
        } else {
            result.put("isAdmin", true);
        }
        return ResponseResult.okResult(result);
    }

    @PreAuthorize("hasAuthority('system:test:list1')")
    @GetMapping("getRouter")
    public ResponseResult<RouterVO> getRouters() {
        Long memberId = SecurityUtils.getMemberId();
        // 查询menu，结果是tree的形式
        List<MenuItemVO> menus = memberFacade.selectRouterMenuTreeByMemberId(memberId);
        // 封装数据返回
        return ResponseResult.okResult(new RouterVO(menus));
    }
}
