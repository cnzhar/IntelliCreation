package com.intellicreation.member.service;

import com.intellicreation.member.domain.entity.UmsRoleDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author za
 * @since 2024-01-25
 */
public interface UmsRoleService extends IService<UmsRoleDO> {

    /**
     * 根据用户id获取用户所有角色
     *
     * @param id
     * @return
     */
    List<String> selectRoleKeyByMemberId(Long id);
}
