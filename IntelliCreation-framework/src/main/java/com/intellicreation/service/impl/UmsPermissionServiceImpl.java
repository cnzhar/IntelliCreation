package com.intellicreation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.intellicreation.constant.SystemConstants;
import com.intellicreation.domain.model.UmsPermissionDO;
import com.intellicreation.mapper.UmsPermissionMapper;
import com.intellicreation.service.UmsPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.intellicreation.util.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author za
 * @since 2024-01-02
 */
@Service
public class UmsPermissionServiceImpl extends ServiceImpl<UmsPermissionMapper, UmsPermissionDO> implements UmsPermissionService {

    @Override
    public List<String> selectPermissionByMemberId(Long id) {
        // 如果是管理员，返回所有的权限
        if (SecurityUtils.isSuperAdmin()) {
            LambdaQueryWrapper<UmsPermissionDO> wrapper = new LambdaQueryWrapper<>();
            wrapper.in(UmsPermissionDO::getPermissionType, SystemConstants.MENU_PERMISSION, SystemConstants.BUTTON_PERMISSION);
            wrapper.eq(UmsPermissionDO::getStatus, SystemConstants.STATUS_NORMAL);
            List<UmsPermissionDO> permissions = list(wrapper);
            return permissions.stream()
                    .map(UmsPermissionDO::getPermissionKey)
                    .collect(Collectors.toList());
        }
        // 否则返回所具有的权限
        return getBaseMapper().selectPermissionByMemberId(id);
    }
}
