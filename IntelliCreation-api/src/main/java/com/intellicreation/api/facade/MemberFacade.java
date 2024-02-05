package com.intellicreation.api.facade;

import com.intellicreation.article.domain.dto.TagQueryParamDTO;
import com.intellicreation.common.vo.PageVO;
import com.intellicreation.member.domain.dto.MemberQueryParamDTO;
import com.intellicreation.member.domain.dto.MenuQueryParamDTO;
import com.intellicreation.member.domain.dto.PermissionQueryParamDTO;
import com.intellicreation.member.domain.dto.RoleQueryParamDTO;
import com.intellicreation.member.domain.entity.UmsMemberDO;
import com.intellicreation.member.domain.vo.MemberInfoVO;
import com.intellicreation.member.domain.vo.RoleVO;

/**
 * @author za
 */
public interface MemberFacade {

    /**
     * 获取用户信息
     *
     * @return
     */
    MemberInfoVO memberInfo();

    /**
     * 更新用户信息
     *
     * @param member
     * @return
     */
    void updateMemberInfo(UmsMemberDO member);

    /**
     * 注册
     *
     * @param member
     * @return
     */
    void register(UmsMemberDO member);

    /**
     * 根据查询条件，查询用户列表
     *
     * @param pageNum
     * @param pageSize
     * @param memberQueryParamDTO
     * @return
     */
    PageVO queryMemberList(Integer pageNum, Integer pageSize, MemberQueryParamDTO memberQueryParamDTO);

    /**
     * 根据条件查询菜单
     *
     * @param pageNum
     * @param pageSize
     * @param menuQueryParamDTO
     * @return
     */
    PageVO queryMenuList(Integer pageNum, Integer pageSize, MenuQueryParamDTO menuQueryParamDTO);

    /**
     * 根据条件查询权限列表
     *
     * @param pageNum
     * @param pageSize
     * @param permissionQueryParamDTO
     * @return
     */
    PageVO queryPermissionList(Integer pageNum, Integer pageSize, PermissionQueryParamDTO permissionQueryParamDTO);

    /**
     * 根据条件查询角色
     *
     * @param pageNum
     * @param pageSize
     * @param roleQueryParamDTO
     * @return
     */
    PageVO queryRoleList(Integer pageNum, Integer pageSize, RoleQueryParamDTO roleQueryParamDTO);

    /**
     * 根据id获取角色详情
     *
     * @param id
     * @return
     */
    RoleVO getRoleDetail(Long id);

    /**
     * 根据条件查询标签
     *
     * @param pageNum
     * @param pageSize
     * @param tagQueryParamDTO
     * @return
     */
    PageVO queryTagList(Integer pageNum, Integer pageSize, TagQueryParamDTO tagQueryParamDTO);
}
