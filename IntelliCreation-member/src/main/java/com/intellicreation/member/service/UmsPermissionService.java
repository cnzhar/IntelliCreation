package com.intellicreation.member.service;

import com.intellicreation.common.vo.PageVO;
import com.intellicreation.member.domain.dto.PermissionQueryParamDTO;
import com.intellicreation.member.domain.entity.UmsPermissionDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author za
 * @since 2024-01-02
 */
public interface UmsPermissionService extends IService<UmsPermissionDO> {

    /**
     * 根据用户id获取用户所有权限
     *
     * @param id
     * @return
     */
    List<String> selectPermissionByMemberId(Long id);

    /**
     * 根据条件查询权限列表
     *
     * @param pageNum
     * @param pageSize
     * @param permissionQueryParamDTO
     * @return
     */
    PageVO queryPermissionList(Integer pageNum, Integer pageSize, PermissionQueryParamDTO permissionQueryParamDTO);
}
