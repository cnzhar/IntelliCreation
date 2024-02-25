package com.intellicreation.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.intellicreation.common.util.BeanCopyUtils;
import com.intellicreation.common.vo.PageVO;
import com.intellicreation.member.domain.dto.AddRoleDTO;
import com.intellicreation.member.domain.dto.RoleQueryParamDTO;
import com.intellicreation.member.domain.dto.UpdateRoleDTO;
import com.intellicreation.member.domain.entity.UmsRoleDO;
import com.intellicreation.common.constant.SystemConstants;
import com.intellicreation.member.domain.vo.RoleVO;
import com.intellicreation.member.mapper.UmsRoleMapper;
import com.intellicreation.member.service.UmsRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.intellicreation.member.util.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
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
    public void addRole(AddRoleDTO addRoleDTO) {
        UmsRoleDO umsRoleDO = BeanCopyUtils.copyBean(addRoleDTO, UmsRoleDO.class);
        save(umsRoleDO);
    }

    @Override
    public void updateRoleInfo(UpdateRoleDTO updateRoleDTO) {
        // todo 改成update(wrapper....), 不要更新不必要字段
        UmsRoleDO umsRoleDO = BeanCopyUtils.copyBean(updateRoleDTO, UmsRoleDO.class);
        updateById(umsRoleDO);
    }

    @Override
    public PageVO queryRoleList(Integer pageNum, Integer pageSize, RoleQueryParamDTO roleQueryParamDTO) {
        // 分页查询
        LambdaQueryWrapper<UmsRoleDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.select(UmsRoleDO::getId, UmsRoleDO::getRoleName, UmsRoleDO::getRoleKey)
                .like(!ObjectUtils.isEmpty(roleQueryParamDTO.getId()), UmsRoleDO::getId, roleQueryParamDTO.getId())
                .like(StringUtils.hasText(roleQueryParamDTO.getRoleName()), UmsRoleDO::getRoleName, roleQueryParamDTO.getRoleName())
                .like(StringUtils.hasText(roleQueryParamDTO.getRoleKey()), UmsRoleDO::getRoleKey, roleQueryParamDTO.getRoleKey());
        Page<UmsRoleDO> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, lambdaQueryWrapper);
        // 封装数据返回
        return new PageVO(page.getRecords(), page.getTotal());
    }

    @Override
    public RoleVO getRoleDetail(Long id) {
        UmsRoleDO umsRoleDO = getById(id);
        return BeanCopyUtils.copyBean(umsRoleDO, RoleVO.class);
    }

    @Override
    public PageVO getRoleListByIds(Integer pageNum, Integer pageSize, List<Long> idList) {
        // 如果传入的id列表为空，则返回空分页
        if (idList == null || idList.isEmpty()) {
            return new PageVO(Collections.emptyList(), 0L);
        }
        // 分页查询
        LambdaQueryWrapper<UmsRoleDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .select(UmsRoleDO::getId, UmsRoleDO::getRoleName, UmsRoleDO::getRoleKey)
                .in(UmsRoleDO::getId, idList);
        Page<UmsRoleDO> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, lambdaQueryWrapper);
        // 封装数据返回
        return new PageVO(page.getRecords(), page.getTotal());
    }

    @Override
    public List<String> selectRoleKeyByMemberId(Long id) {
        // 判断是否为超级管理员 如果是返回集合中只需要有Super Admin
        if (SecurityUtils.isSuperAdmin(id)) {
            List<String> roleKeys = new ArrayList<>();
            roleKeys.add(SystemConstants.KEY_OF_SUPER_ADMIN);
            return roleKeys;
        }
        // 否则查询用户所具有的角色信息
        return getBaseMapper().selectRoleKeyByMemberId(id);
    }
}
