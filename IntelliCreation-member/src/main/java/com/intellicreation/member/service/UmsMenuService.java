package com.intellicreation.member.service;

import com.intellicreation.member.domain.entity.UmsMenu;
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
public interface UmsMenuService extends IService<UmsMenu> {

    /**
     * 根据用户id获取菜单
     *
     * @param memberId
     * @return
     */
    List<MenuVO> selectRouterMenuTreeByMemberId(Long memberId);
}
