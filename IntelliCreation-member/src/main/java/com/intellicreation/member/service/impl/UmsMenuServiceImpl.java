package com.intellicreation.member.service.impl;

import com.intellicreation.member.domain.entity.UmsMenu;
import com.intellicreation.member.domain.vo.MenuVO;
import com.intellicreation.common.constant.SystemConstants;
import com.intellicreation.member.mapper.UmsMenuMapper;
import com.intellicreation.member.service.UmsMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.intellicreation.member.util.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author za
 * @since 2024-01-26
 */
@Service
public class UmsMenuServiceImpl extends ServiceImpl<UmsMenuMapper, UmsMenu> implements UmsMenuService {

    @Override
    public List<MenuVO> selectRouterMenuTreeByMemberId(Long memberId) {
        UmsMenuMapper menuMapper = getBaseMapper();
        List<MenuVO> menus;
        // 判断是否是管理员
        if (SecurityUtils.isSuperAdmin()) {
            // 如果是，获取所有符合要求的Menu
            menus = menuMapper.selectAllRouterMenu();
        } else {
            // 否则，获取当前用户所具有的Menu
            menus = menuMapper.selectRouterMenuByMemberId(memberId);
        }
        // 构建tree
        return builderMenuTree(menus);
    }

    /**
     * 构建tree
     * 先找出第一层的菜单,然后去找他们的子菜单设置到children属性中
     *
     * @param menus
     * @return
     */
    private List<MenuVO> builderMenuTree(List<MenuVO> menus) {
        return menus.stream()
                .filter(menu -> menu.getParent().equals(SystemConstants.NULL_PARENT))
                .map(menu -> menu.setChildren(getChildren(menu, menus)))
                .collect(Collectors.toList());
    }

    /**
     * 获取存入参数的子Menu集合
     *
     * @param menuVO
     * @param menus
     * @return
     */
    private List<MenuVO> getChildren(MenuVO menuVO, List<MenuVO> menus) {
        return menus.stream()
                .filter(m -> m.getParent().equals(menuVO.getId()))
                .map(m -> m.setChildren(getChildren(m, menus)))
                .collect(Collectors.toList());
    }
}
