package com.intellicreation.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.intellicreation.common.vo.PageVO;
import com.intellicreation.member.domain.entity.UmsRolePermissionRelationDO;
import com.intellicreation.member.mapper.UmsRolePermissionRelationMapper;
import com.intellicreation.member.service.UmsRolePermissionRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author za
 * @since 2024-02-21
 */
@Service
public class UmsRolePermissionRelationServiceImpl extends ServiceImpl<UmsRolePermissionRelationMapper, UmsRolePermissionRelationDO> implements UmsRolePermissionRelationService {

    @Override
    public List<Long> getPermissionIdsByRole(Long roleId) {
        LambdaQueryWrapper<UmsRolePermissionRelationDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .select(UmsRolePermissionRelationDO::getPermissionId)
                .eq(UmsRolePermissionRelationDO::getRoleId, roleId);
        List<UmsRolePermissionRelationDO> resultList = list(lambdaQueryWrapper);
        return resultList.stream()
                .map(UmsRolePermissionRelationDO::getPermissionId)
                .collect(Collectors.toList());
    }
}
