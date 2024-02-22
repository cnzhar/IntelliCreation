package com.intellicreation.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.intellicreation.common.util.BeanCopyUtils;
import com.intellicreation.common.vo.PageVO;
import com.intellicreation.member.domain.dto.AddPermissionDTO;
import com.intellicreation.member.domain.dto.PermissionQueryParamDTO;
import com.intellicreation.member.domain.dto.UpdatePermissionDTO;
import com.intellicreation.member.domain.entity.UmsPermissionDO;
import com.intellicreation.common.constant.SystemConstants;
import com.intellicreation.member.domain.vo.PermissionVO;
import com.intellicreation.member.mapper.UmsPermissionMapper;
import com.intellicreation.member.service.UmsPermissionService;
import com.intellicreation.member.util.SecurityUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
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
    public void addPermission(AddPermissionDTO addPermissionDTO) {
        UmsPermissionDO umsPermissionDO = BeanCopyUtils.copyBean(addPermissionDTO, UmsPermissionDO.class);
        save(umsPermissionDO);
    }

    @Override
    public void updatePermissionInfo(UpdatePermissionDTO updatePermissionDTO) {
        UmsPermissionDO umsPermissionDO = BeanCopyUtils.copyBean(updatePermissionDTO, UmsPermissionDO.class);
        updateById(umsPermissionDO);
    }

    @Override
    public List<String> selectPermissionByMemberId(Long id) {
        // 如果是管理员，返回所有的权限
        if (SecurityUtils.isSuperAdmin(id)) {
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

    @Override
    public PageVO queryPermissionList(Integer pageNum, Integer pageSize, PermissionQueryParamDTO permissionQueryParamDTO) {
        // 分页查询
        LambdaQueryWrapper<UmsPermissionDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.select(UmsPermissionDO::getId, UmsPermissionDO::getPermissionName, UmsPermissionDO::getPermissionKey)
                .like(!ObjectUtils.isEmpty(permissionQueryParamDTO.getId()), UmsPermissionDO::getId, permissionQueryParamDTO.getId())
                .like(StringUtils.hasText(permissionQueryParamDTO.getPermissionName()), UmsPermissionDO::getPermissionName, permissionQueryParamDTO.getPermissionName())
                .like(StringUtils.hasText(permissionQueryParamDTO.getPermissionKey()), UmsPermissionDO::getPermissionKey, permissionQueryParamDTO.getPermissionKey());
        Page<UmsPermissionDO> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, lambdaQueryWrapper);
        // 封装数据返回
        return new PageVO(page.getRecords(), page.getTotal());
    }

    @Override
    public PermissionVO getPermissionDetail(Long id) {
        UmsPermissionDO umsPermissionDO = getById(id);
        return BeanCopyUtils.copyBean(umsPermissionDO, PermissionVO.class);
    }

    @Override
    public PageVO getPermissionListByIds(Integer pageNum, Integer pageSize, List<Long> idList) {
        if (idList == null || idList.isEmpty()) {
            return new PageVO(Collections.emptyList(), 0L);
        }
        LambdaQueryWrapper<UmsPermissionDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .select(UmsPermissionDO::getId, UmsPermissionDO::getPermissionName, UmsPermissionDO::getPermissionKey)
                .in(UmsPermissionDO::getId, idList);
        Page<UmsPermissionDO> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, lambdaQueryWrapper);
        // 封装数据返回
        return new PageVO(page.getRecords(), page.getTotal());
    }


}
