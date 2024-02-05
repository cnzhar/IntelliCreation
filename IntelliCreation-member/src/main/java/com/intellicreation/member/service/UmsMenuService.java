package com.intellicreation.member.service;

import com.intellicreation.common.vo.PageVO;
import com.intellicreation.member.domain.dto.MenuQueryParamDTO;
import com.intellicreation.member.domain.entity.UmsMenuDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.intellicreation.member.domain.vo.MenuVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author za
 * @since 2024-01-26
 */
public interface UmsMenuService extends IService<UmsMenuDO> {

    /**
     * 根据用户id获取菜单
     *
     * @param memberId
     * @return
     */
    List<MenuVO> selectRouterMenuTreeByMemberId(Long memberId);

    /**
     * 根据条件查询菜单
     *
     * @param pageNum
     * @param pageSize
     * @param menuQueryParamDTO
     * @return
     */
    PageVO queryMenuList(Integer pageNum, Integer pageSize, MenuQueryParamDTO menuQueryParamDTO);
}
