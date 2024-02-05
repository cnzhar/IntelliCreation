package com.intellicreation.member.service;

import com.intellicreation.common.vo.PageVO;
import com.intellicreation.member.domain.dto.RoleQueryParamDTO;
import com.intellicreation.member.domain.entity.UmsRoleDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.intellicreation.member.domain.vo.RoleVO;

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

    /**
     * 根据条件查询角色
     *
     * @param pageNum
     * @param pageSize
     * @param roleQueryParamDTO
     * @return
     */
    PageVO queryRoleList(Integer pageNum, Integer pageSize, RoleQueryParamDTO roleQueryParamDTO);

    /**
     * 根据id获取角色详情
     *
     * @param id
     * @return
     */
    RoleVO getRoleDetail(Long id);
}
