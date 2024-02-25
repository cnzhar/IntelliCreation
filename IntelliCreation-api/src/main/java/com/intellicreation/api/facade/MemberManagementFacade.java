package com.intellicreation.api.facade;


import com.intellicreation.member.domain.dto.UpdateMemberInfoDTO;
import com.intellicreation.common.vo.PageVO;
import com.intellicreation.member.domain.dto.*;
import com.intellicreation.member.domain.vo.MemberInfoVO;
import com.intellicreation.member.domain.vo.MenuVO;
import com.intellicreation.member.domain.vo.PermissionVO;
import com.intellicreation.member.domain.vo.RoleVO;

/**
 * @author za
 */
public interface MemberManagementFacade {

    /**
     * 注册
     *
     * @param registerMemberDTO
     */
    void register(RegisterMemberDTO registerMemberDTO);

    /**
     * 管理员新增用户
     *
     * @param addMemberDTO
     */
    void addMember(AddMemberDTO addMemberDTO);

    /**
     * 根据id删除用户
     *
     * @param id
     */
    void deleteMember(Long id);

    /**
     * 更新用户信息
     *
     * @param updateMemberInfoDTO
     */
    void updateMemberInfo(UpdateMemberInfoDTO updateMemberInfoDTO);

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
     * 获取用户信息
     *
     * @param id
     * @return
     */
    MemberInfoVO getMemberInfo(Long id);

    /**
     * 获取某个用户拥有的全部角色
     *
     * @param pageNum
     * @param pageSize
     * @param memberId
     * @return
     */
    PageVO getRolesByMember(Integer pageNum, Integer pageSize, Long memberId);

    /**
     * 获取用户登录日志
     *
     * @param pageNum
     * @param pageSize
     * @param memberId
     * @return
     */
    PageVO getMemberLoginLog(Integer pageNum, Integer pageSize, Long memberId);

    /**
     * 新增菜单
     *
     * @param addMenuDTO
     */
    void addMenu(AddMenuDTO addMenuDTO);

    /**
     * 删除菜单
     *
     * @param id
     */
    void deleteMenu(Long id);

    /**
     * 编辑菜单
     *
     * @param updateMenuDTO
     */
    void updateMenuInfo(UpdateMenuDTO updateMenuDTO);

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
     * 获取菜单详情
     *
     * @param id
     * @return
     */
    MenuVO getMenuDetail(Long id);

    /**
     * 新增权限
     *
     * @param addPermissionDTO
     */
    void addPermission(AddPermissionDTO addPermissionDTO);

    /**
     * 删除权限
     *
     * @param id
     */
    void deletePermission(Long id);

    /**
     * 修改权限
     *
     * @param updatePermissionDTO
     */
    void updatePermissionInfo(UpdatePermissionDTO updatePermissionDTO);

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
     * 根据id获取权限详情
     *
     * @param id
     * @return
     */
    PermissionVO getPermissionDetail(Long id);

    /**
     * 获取拥有某个权限的全部角色
     *
     * @param pageNum
     * @param pageSize
     * @param permissionId
     * @return
     */
    PageVO getRolesByPermission(Integer pageNum, Integer pageSize, Long permissionId);

    /**
     * 新增角色
     *
     * @param addRoleDTO
     */
    void addRole(AddRoleDTO addRoleDTO);

    /**
     * 删除角色
     *
     * @param id
     */
    void deleteRole(Long id);

    /**
     * 更新角色信息
     *
     * @param updateRoleDTO
     */
    void updateRoleInfo(UpdateRoleDTO updateRoleDTO);

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
     * 获取某个角色所拥有的全部权限
     *
     * @param pageNum
     * @param pageSize
     * @param roleId
     * @return
     */
    PageVO getPermissionsByRole(Integer pageNum, Integer pageSize, Long roleId);

    /**
     * 获取拥有某个角色的全部用户
     *
     * @param pageNum
     * @param pageSize
     * @param roleId
     * @return
     */
    PageVO getMembersByRole(Integer pageNum, Integer pageSize, Long roleId);
}
