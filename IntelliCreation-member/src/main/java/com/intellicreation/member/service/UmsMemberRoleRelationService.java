package com.intellicreation.member.service;

import com.intellicreation.member.domain.entity.UmsMemberRoleRelationDO;
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
public interface UmsMemberRoleRelationService extends IService<UmsMemberRoleRelationDO> {

    /**
     * 获取拥有某个角色的所有用户
     *
     * @param roleId
     * @return
     */
    List<Long> getMemberIdsByRole(Long roleId);
}
