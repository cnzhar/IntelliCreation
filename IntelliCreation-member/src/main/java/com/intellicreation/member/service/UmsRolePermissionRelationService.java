package com.intellicreation.member.service;


import com.intellicreation.member.domain.entity.UmsRolePermissionRelationDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author za
 * @since 2024-02-21
 */
public interface UmsRolePermissionRelationService extends IService<UmsRolePermissionRelationDO> {

    /**
     * 获取某个角色所拥有的全部权限id
     *
     * @param roleId
     * @return
     */
    List<Long> getPermissionIdsByRole(Long roleId);

    /**
     * 获取拥有某个权限的全部角色
     *
     * @param permissionId
     * @return
     */
    List<Long> getRoleIdsByPermission(Long permissionId);
}
