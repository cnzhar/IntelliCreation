package com.intellicreation.api.facade.impl;

import com.intellicreation.api.facade.MemberFacade;
import com.intellicreation.article.domain.dto.TagQueryParamDTO;
import com.intellicreation.article.service.AmsTagService;
import com.intellicreation.common.vo.PageVO;
import com.intellicreation.member.domain.dto.MemberQueryParamDTO;
import com.intellicreation.member.domain.dto.MenuQueryParamDTO;
import com.intellicreation.member.domain.dto.PermissionQueryParamDTO;
import com.intellicreation.member.domain.dto.RoleQueryParamDTO;
import com.intellicreation.member.domain.entity.UmsMemberDO;
import com.intellicreation.member.domain.vo.MemberInfoVO;
import com.intellicreation.member.domain.vo.RoleVO;
import com.intellicreation.member.service.UmsMemberService;
import com.intellicreation.member.service.UmsMenuService;
import com.intellicreation.member.service.UmsPermissionService;
import com.intellicreation.member.service.UmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author za
 */
@Component
public class MemberFacadeImpl implements MemberFacade {

    @Autowired
    private UmsMemberService umsMemberService;

    @Autowired
    private UmsMenuService umsMenuService;

    @Autowired
    private UmsPermissionService umsPermissionService;

    @Autowired
    private UmsRoleService umsRoleService;

    @Autowired
    private AmsTagService amsTagService;

    @Override
    public MemberInfoVO memberInfo() {
        return umsMemberService.memberInfo();
    }

    @Override
    public void updateMemberInfo(UmsMemberDO member) {
        umsMemberService.updateMemberInfo(member);
    }

    @Override
    public void register(UmsMemberDO member) {
        umsMemberService.register(member);
    }

    @Override
    public PageVO queryMemberList(Integer pageNum, Integer pageSize, MemberQueryParamDTO memberQueryParamDTO) {
        return umsMemberService.queryMemberList(pageNum, pageSize, memberQueryParamDTO);
    }

    @Override
    public PageVO queryMenuList(Integer pageNum, Integer pageSize, MenuQueryParamDTO menuQueryParamDTO) {
        return umsMenuService.queryMenuList(pageNum, pageSize, menuQueryParamDTO);
    }

    @Override
    public PageVO queryPermissionList(Integer pageNum, Integer pageSize, PermissionQueryParamDTO permissionQueryParamDTO) {
        return umsPermissionService.queryPermissionList(pageNum, pageSize, permissionQueryParamDTO);
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
    public PageVO queryTagList(Integer pageNum, Integer pageSize, TagQueryParamDTO tagQueryParamDTO) {
        return amsTagService.queryTagList(pageNum, pageSize, tagQueryParamDTO);
    }
}
