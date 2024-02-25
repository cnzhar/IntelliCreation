package com.intellicreation.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.intellicreation.member.domain.entity.UmsMemberRoleRelationDO;
import com.intellicreation.member.mapper.UmsMemberRoleRelationMapper;
import com.intellicreation.member.service.UmsMemberRoleRelationService;
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
public class UmsMemberRoleRelationServiceImpl extends ServiceImpl<UmsMemberRoleRelationMapper, UmsMemberRoleRelationDO> implements UmsMemberRoleRelationService {

    @Override
    public List<Long> getRoleIdsByMember(Long memberId) {
        LambdaQueryWrapper<UmsMemberRoleRelationDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .select(UmsMemberRoleRelationDO::getRoleId)
                .eq(UmsMemberRoleRelationDO::getMemberId, memberId);
        List<UmsMemberRoleRelationDO> resultList = list(lambdaQueryWrapper);
        return resultList.stream()
                .map(UmsMemberRoleRelationDO::getRoleId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Long> getMemberIdsByRole(Long roleId) {
        LambdaQueryWrapper<UmsMemberRoleRelationDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .select(UmsMemberRoleRelationDO::getMemberId)
                .eq(UmsMemberRoleRelationDO::getRoleId, roleId);
        List<UmsMemberRoleRelationDO> resultList = list(lambdaQueryWrapper);
        return resultList.stream()
                .map(UmsMemberRoleRelationDO::getMemberId)
                .collect(Collectors.toList());
    }
}
