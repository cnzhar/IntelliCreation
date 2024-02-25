package com.intellicreation.api.controller;


import com.intellicreation.api.facade.MemberManagementFacade;
import com.intellicreation.common.constant.SystemConstants;
import com.intellicreation.member.domain.dto.UpdateMemberInfoDTO;
import com.intellicreation.common.vo.PageVO;
import com.intellicreation.api.annotation.SystemLog;
import com.intellicreation.common.ResponseResult;
import com.intellicreation.member.domain.dto.*;
import com.intellicreation.member.domain.vo.MemberInfoVO;
import com.intellicreation.member.domain.vo.MenuVO;
import com.intellicreation.member.domain.vo.PermissionVO;
import com.intellicreation.member.domain.vo.RoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author za
 * @since 2024-01-01
 */
@RestController
@RequestMapping("/memberManagement")
public class MemberManagementController {

    @Autowired
    private MemberManagementFacade memberManagementFacade;

    // todo 看看有没有必要在前端就加密，不然明文在网络上传太不安全了
    @PostMapping("/register")
    public ResponseResult register(@Valid @RequestBody RegisterMemberDTO registerMemberDTO) {
        memberManagementFacade.register(registerMemberDTO);
        return ResponseResult.okResult();
    }

    @SystemLog(businessName = "新增用户", operationType = SystemConstants.ADMIN_TYPE)
    @PostMapping("/addMember")
    public ResponseResult addMember(@Valid @RequestBody AddMemberDTO addMemberDTO) {
        memberManagementFacade.addMember(addMemberDTO);
        return ResponseResult.okResult();
    }

    @DeleteMapping("/deleteMember/{id}")
    public ResponseResult deleteMember(@PathVariable Long id) {
        memberManagementFacade.deleteMember(id);
        return ResponseResult.okResult();
    }

    @SystemLog(businessName = "更新用户信息", operationType = SystemConstants.ADMIN_TYPE)
    @PutMapping("/updateMemberInfo")
    public ResponseResult updateMemberInfo(@Valid @RequestBody UpdateMemberInfoDTO updateMemberInfoDTO) {
        memberManagementFacade.updateMemberInfo(updateMemberInfoDTO);
        return ResponseResult.okResult();
    }

    @GetMapping("/memberList")
    public ResponseResult getMemberList(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "5") Integer pageSize,
                                        MemberQueryParamDTO memberQueryParamDTO) {
        PageVO pageVO = memberManagementFacade.queryMemberList(pageNum, pageSize, memberQueryParamDTO);
        return ResponseResult.okResult(pageVO);
    }

    @GetMapping("/getMemberInfo/{id}")
    public ResponseResult getMemberInfo(@PathVariable("id") Long id) {
        MemberInfoVO memberInfoVO = memberManagementFacade.getMemberInfo(id);
        return ResponseResult.okResult(memberInfoVO);
    }

    @GetMapping("/getRolesByMember")
    public ResponseResult getRolesByMember(@RequestParam(defaultValue = "1") Integer pageNum,
                                           @RequestParam(defaultValue = "5") Integer pageSize,
                                           @RequestParam Long memberId) {
        PageVO pageVO = memberManagementFacade.getRolesByMember(pageNum, pageSize, memberId);
        return ResponseResult.okResult(pageVO);
    }

    @GetMapping("/getMemberLoginLog")
    public ResponseResult getMemberLoginLog(@RequestParam(defaultValue = "1") Integer pageNum,
                                            @RequestParam(defaultValue = "5") Integer pageSize,
                                            Long memberId) {
        PageVO pageVO = memberManagementFacade.getMemberLoginLog(pageNum, pageSize, memberId);
        return ResponseResult.okResult(pageVO);
    }

    @PostMapping("/addMenu")
    public ResponseResult addMenu(@Valid @RequestBody AddMenuDTO addMenuDTO) {
        memberManagementFacade.addMenu(addMenuDTO);
        return ResponseResult.okResult();
    }

    @DeleteMapping("/deleteMenu/{id}")
    public ResponseResult deleteMenu(@PathVariable Long id) {
        memberManagementFacade.deleteMenu(id);
        return ResponseResult.okResult();
    }

    @PutMapping("updateMenuInfo")
    public ResponseResult updateMenuInfo(@RequestBody UpdateMenuDTO updateMenuDTO) {
        memberManagementFacade.updateMenuInfo(updateMenuDTO);
        return ResponseResult.okResult();
    }

    @GetMapping("/menuList")
    public ResponseResult getMenuList(@RequestParam(defaultValue = "1") Integer pageNum,
                                      @RequestParam(defaultValue = "5") Integer pageSize,
                                      MenuQueryParamDTO menuQueryParamDTO) {
        PageVO pageVO = memberManagementFacade.queryMenuList(pageNum, pageSize, menuQueryParamDTO);
        return ResponseResult.okResult(pageVO);
    }

    @GetMapping("/getMenuDetail/{id}")
    public ResponseResult getMenuDetail(@PathVariable("id") Long id) {
        MenuVO menuVO = memberManagementFacade.getMenuDetail(id);
        return ResponseResult.okResult(menuVO);
    }

    @PostMapping("/addPermission")
    public ResponseResult addPermission(@Valid @RequestBody AddPermissionDTO addPermissionDTO) {
        memberManagementFacade.addPermission(addPermissionDTO);
        return ResponseResult.okResult();
    }

    @DeleteMapping("/deletePermission/{id}")
    public ResponseResult deletePermission(@PathVariable Long id) {
        memberManagementFacade.deletePermission(id);
        return ResponseResult.okResult();
    }

    @PutMapping("/updatePermissionInfo")
    public ResponseResult updatePermissionInfo(@RequestBody UpdatePermissionDTO updatePermissionDTO) {
        memberManagementFacade.updatePermissionInfo(updatePermissionDTO);
        return ResponseResult.okResult();
    }

    @GetMapping("/permissionList")
    public ResponseResult getPermissionList(@RequestParam(defaultValue = "1") Integer pageNum,
                                            @RequestParam(defaultValue = "5") Integer pageSize,
                                            PermissionQueryParamDTO permissionQueryParamDTO) {
        PageVO pageVO = memberManagementFacade.queryPermissionList(pageNum, pageSize, permissionQueryParamDTO);
        return ResponseResult.okResult(pageVO);
    }

    @GetMapping("/getPermissionDetail/{id}")
    public ResponseResult getPermissionDetail(@PathVariable("id") Long id) {
        PermissionVO permissionVO = memberManagementFacade.getPermissionDetail(id);
        return ResponseResult.okResult(permissionVO);
    }

    @GetMapping("/getRolesByPermission")
    public ResponseResult getRolesByPermission(@RequestParam(defaultValue = "1") Integer pageNum,
                                               @RequestParam(defaultValue = "5") Integer pageSize,
                                               Long permissionId) {
        PageVO pageVO = memberManagementFacade.getRolesByPermission(pageNum, pageSize, permissionId);
        return ResponseResult.okResult(pageVO);
    }

    @PostMapping("/addRole")
    public ResponseResult addRole(@RequestBody AddRoleDTO addRoleDTO) {
        memberManagementFacade.addRole(addRoleDTO);
        return ResponseResult.okResult();
    }

    @DeleteMapping("/deleteRole/{id}")
    public ResponseResult deleteRole(@PathVariable Long id) {
        memberManagementFacade.deleteRole(id);
        return ResponseResult.okResult();
    }

    @PutMapping("/updateRoleInfo")
    public ResponseResult updateRoleInfo(@RequestBody UpdateRoleDTO updateRoleDTO) {
        memberManagementFacade.updateRoleInfo(updateRoleDTO);
        return ResponseResult.okResult();
    }

    @GetMapping("/roleList")
    public ResponseResult getRoleList(@RequestParam(defaultValue = "1") Integer pageNum,
                                      @RequestParam(defaultValue = "5") Integer pageSize,
                                      RoleQueryParamDTO roleQueryParamDTO) {
        PageVO pageVO = memberManagementFacade.queryRoleList(pageNum, pageSize, roleQueryParamDTO);
        return ResponseResult.okResult(pageVO);
    }

    @GetMapping("/getRoleDetail/{id}")
    public ResponseResult getRoleDetail(@PathVariable("id") Long id) {
        RoleVO roleVO = memberManagementFacade.getRoleDetail(id);
        return ResponseResult.okResult(roleVO);
    }

    @GetMapping("/getPermissionsByRole")
    public ResponseResult getPermissionsByRole(@RequestParam(defaultValue = "1") Integer pageNum,
                                               @RequestParam(defaultValue = "5") Integer pageSize,
                                               @RequestParam Long roleId) {
        PageVO pageVO = memberManagementFacade.getPermissionsByRole(pageNum, pageSize, roleId);
        return ResponseResult.okResult(pageVO);
    }

    @GetMapping("/getMembersByRole")
    public ResponseResult getMembersByRole(@RequestParam(defaultValue = "1") Integer pageNum,
                                           @RequestParam(defaultValue = "5") Integer pageSize,
                                           @RequestParam Long roleId) {
        PageVO pageVO = memberManagementFacade.getMembersByRole(pageNum, pageSize, roleId);
        return ResponseResult.okResult(pageVO);
    }
}
