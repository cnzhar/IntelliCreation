package com.intellicreation.member.mapper;

import com.intellicreation.member.domain.entity.UmsMenuDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.intellicreation.member.domain.vo.MenuVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author za
 * @since 2024-01-26
 */
public interface UmsMenuMapper extends BaseMapper<UmsMenuDO> {

    /**
     * 获取所有的路由
     *
     * @return
     */
    List<MenuVO> selectAllRouterMenu();

    /**
     * 根据用户id获取用户菜单路由
     *
     * @param memberId
     * @return
     */
    List<MenuVO> selectRouterMenuByMemberId(Long memberId);
}
