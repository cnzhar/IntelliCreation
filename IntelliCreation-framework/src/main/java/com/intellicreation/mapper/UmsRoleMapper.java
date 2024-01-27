package com.intellicreation.mapper;

import com.intellicreation.domain.model.UmsRoleDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author za
 * @since 2024-01-25
 */
public interface UmsRoleMapper extends BaseMapper<UmsRoleDO> {

    /**
     * 根据用户id获取用户的所有角色
     *
     * @param id
     * @return
     */
    List<String> selectRoleKeyByMemberId(Long id);
}
