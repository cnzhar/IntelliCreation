package com.intellicreation.service.impl;

import com.intellicreation.constant.SystemConstants;
import com.intellicreation.domain.model.UmsRoleDO;
import com.intellicreation.mapper.UmsRoleMapper;
import com.intellicreation.service.UmsRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.intellicreation.util.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author za
 * @since 2024-01-25
 */
@Service
public class UmsRoleServiceImpl extends ServiceImpl<UmsRoleMapper, UmsRoleDO> implements UmsRoleService {

    @Override
    public List<String> selectRoleKeyByMemberId(Long id) {
        // 判断是否为超级管理员 如果是返回集合中只需要有Super Admin
        if (SecurityUtils.isSuperAdmin()) {
            List<String> roleKeys = new ArrayList<>();
            roleKeys.add(SystemConstants.KEY_OF_SUPER_ADMIN);
            return roleKeys;
        }
        // 否则查询用户所具有的角色信息
        return getBaseMapper().selectRoleKeyByMemberId(id);
    }
}
