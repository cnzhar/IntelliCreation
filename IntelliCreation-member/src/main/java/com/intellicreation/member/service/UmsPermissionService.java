package com.intellicreation.member.service;

import com.intellicreation.common.vo.PageVO;
import com.intellicreation.member.domain.dto.AddPermissionDTO;
import com.intellicreation.member.domain.dto.PermissionQueryParamDTO;
import com.intellicreation.member.domain.dto.UpdatePermissionDTO;
import com.intellicreation.member.domain.entity.UmsPermissionDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.intellicreation.member.domain.vo.PermissionVO;

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
     * 新增权限
     *
     * @param addPermissionDTO
     */
    void addPermission(AddPermissionDTO addPermissionDTO);

    /**
     * 更新权限
     *
     * @param updatePermissionDTO
     */
    void updatePermissionInfo(UpdatePermissionDTO updatePermissionDTO);

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

    /**
     * 根据id获取角色详情
     *
     * @param id
     * @return
     */
    PermissionVO getPermissionDetail(Long id);

    /**
     * 根据id列表批量获取权限
     *
     * @param pageNum
     * @param pageSize
     * @param idList
     * @return
     */
    PageVO getPermissionByRoleIdBatch(Integer pageNum, Integer pageSize, List<Long> idList);
}
