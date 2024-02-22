package com.intellicreation.api.facade.impl;


import com.intellicreation.api.facade.MemberManagementFacade;
import com.intellicreation.common.vo.PageVO;
import com.intellicreation.member.domain.dto.*;
import com.intellicreation.member.domain.entity.UmsMemberDO;
import com.intellicreation.member.domain.vo.MemberInfoVO;
import com.intellicreation.member.domain.vo.MenuVO;
import com.intellicreation.member.domain.vo.PermissionVO;
import com.intellicreation.member.domain.vo.RoleVO;
import com.intellicreation.member.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author za
 */
@Component
public class MemberManagementFacadeImpl implements MemberManagementFacade {

    @Autowired
    private UmsMemberService umsMemberService;

    @Autowired
    private UmsMenuService umsMenuService;

    @Autowired
    private UmsPermissionService umsPermissionService;

    @Autowired
    private UmsRoleService umsRoleService;

    @Autowired
    private UmsRolePermissionRelationService umsRolePermissionRelationService;

    @Autowired
    private UmsMemberRoleRelationService umsMemberRoleRelationService;

    @Override
    public void register(RegisterMemberDTO registerMemberDTO) {
        umsMemberService.register(registerMemberDTO);
    }

    @Override
    public void addMember(AddMemberDTO addMemberDTO) {
        umsMemberService.addMember(addMemberDTO);
    }

    @Override
    public void deleteMember(Long id) {
        umsMemberService.removeById(id);
    }

    @Override
    public void updateMemberInfo(UmsMemberDO member) {
        umsMemberService.updateMemberInfo(member);
    }

    @Override
    public PageVO queryMemberList(Integer pageNum, Integer pageSize, MemberQueryParamDTO memberQueryParamDTO) {
        return umsMemberService.queryMemberList(pageNum, pageSize, memberQueryParamDTO);
    }

    @Override
    public MemberInfoVO getMemberInfo(Long id) {
        return umsMemberService.getMemberInfo(id);
    }

    @Override
    public void addMenu(AddMenuDTO addMenuDTO) {
        umsMenuService.addMenu(addMenuDTO);
    }

    @Override
    public void deleteMenu(Long id) {
        umsMenuService.removeById(id);
    }

    @Override
    public void updateMenuInfo(UpdateMenuDTO updateMenuDTO) {
        umsMenuService.updateMenuInfo(updateMenuDTO);
    }

    @Override
    public PageVO queryMenuList(Integer pageNum, Integer pageSize, MenuQueryParamDTO menuQueryParamDTO) {
        return umsMenuService.queryMenuList(pageNum, pageSize, menuQueryParamDTO);
    }

    @Override
    public MenuVO getMenuDetail(Long id) {
        return umsMenuService.getMenuDetail(id);
    }

    @Override
    public void addPermission(AddPermissionDTO addPermissionDTO) {
        umsPermissionService.addPermission(addPermissionDTO);
    }

    @Override
    public void deletePermission(Long id) {
        umsPermissionService.removeById(id);
    }

    @Override
    public void updatePermissionInfo(UpdatePermissionDTO updatePermissionDTO) {
        umsPermissionService.updatePermissionInfo(updatePermissionDTO);
    }

    @Override
    public PageVO queryPermissionList(Integer pageNum, Integer pageSize, PermissionQueryParamDTO permissionQueryParamDTO) {
        return umsPermissionService.queryPermissionList(pageNum, pageSize, permissionQueryParamDTO);
    }

    @Override
    public PermissionVO getPermissionDetail(Long id) {
        return umsPermissionService.getPermissionDetail(id);
    }

    @Override
    public void addRole(AddRoleDTO addRoleDTO) {
        umsRoleService.addRole(addRoleDTO);
    }

    @Override
    public void deleteRole(Long id) {
        umsRoleService.removeById(id);
    }

    @Override
    public void updateRoleInfo(UpdateRoleDTO updateRoleDTO) {
        umsRoleService.updateRoleInfo(updateRoleDTO);
    }

    @Override
    public PageVO queryRoleList(Integer pageNum, Integer pageSize, RoleQueryParamDTO roleQueryParamDTO) {
        return umsRoleService.queryRoleList(pageNum, pageSize, roleQueryParamDTO);
    }

    @Override
    public RoleVO getRoleDetail(Long id) {
        return umsRoleService.getRoleDetail(id);
    }

    @Override
    public PageVO getPermissionsByRole(Integer pageNum, Integer pageSize, Long roleId) {
        List<Long> idList = umsRolePermissionRelationService.getPermissionIdsByRole(roleId);
        return umsPermissionService.getPermissionListByIds(pageNum, pageSize, idList);
    }

    @Override
    public PageVO getMembersByRole(Integer pageNum, Integer pageSize, Long roleId) {
        List<Long> idList = umsMemberRoleRelationService.getMemberIdsByRole(roleId);
        return umsMemberService.getMemberListById(pageNum, pageSize, idList);
    }
}
