package com.intellicreation.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.intellicreation.common.util.BeanCopyUtils;
import com.intellicreation.common.vo.PageVO;
import com.intellicreation.member.domain.dto.AddMenuDTO;
import com.intellicreation.member.domain.dto.MenuQueryParamDTO;
import com.intellicreation.member.domain.dto.UpdateMenuDTO;
import com.intellicreation.member.domain.entity.UmsMemberDO;
import com.intellicreation.member.domain.entity.UmsMenuDO;
import com.intellicreation.member.domain.vo.MenuItemVO;
import com.intellicreation.common.constant.SystemConstants;
import com.intellicreation.member.domain.vo.MenuVO;
import com.intellicreation.member.mapper.UmsMenuMapper;
import com.intellicreation.member.service.UmsMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.intellicreation.member.util.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

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
public class UmsMenuServiceImpl extends ServiceImpl<UmsMenuMapper, UmsMenuDO> implements UmsMenuService {

    @Override
    public void addMenu(AddMenuDTO addMenuDTO) {
        UmsMenuDO umsMenuDO = BeanCopyUtils.copyBean(addMenuDTO, UmsMenuDO.class);
        save(umsMenuDO);
    }

    @Override
    public void updateMenuInfo(UpdateMenuDTO updateMenuDTO) {
        UmsMenuDO umsMenuDO = BeanCopyUtils.copyBean(updateMenuDTO, UmsMenuDO.class);
        updateById(umsMenuDO);
    }

    @Override
    public List<MenuItemVO> selectRouterMenuTreeByMemberId(Long memberId) {
        UmsMenuMapper menuMapper = getBaseMapper();
        List<MenuItemVO> menus;
        // 判断是否是管理员
        if (SecurityUtils.isSuperAdmin(memberId)) {
            // 如果是，获取所有符合要求的Menu
            menus = menuMapper.selectAllRouterMenu();
        } else {
            // 否则，获取当前用户所具有的Menu
            menus = menuMapper.selectRouterMenuByMemberId(memberId);
        }
        // 构建tree
        return builderMenuTree(menus);
    }

    @Override
    public PageVO queryMenuList(Integer pageNum, Integer pageSize, MenuQueryParamDTO menuQueryParamDTO) {
        // 分页查询
        LambdaQueryWrapper<UmsMenuDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.select(UmsMenuDO::getId, UmsMenuDO::getIcon, UmsMenuDO::getMenuName, UmsMenuDO::getParent, UmsMenuDO::getSort, UmsMenuDO::getPath)
                .like(!ObjectUtils.isEmpty(menuQueryParamDTO.getId()), UmsMenuDO::getId, menuQueryParamDTO.getId())
                .like(StringUtils.hasText(menuQueryParamDTO.getIcon()), UmsMenuDO::getIcon, menuQueryParamDTO.getIcon())
                .like(StringUtils.hasText(menuQueryParamDTO.getMenuName()), UmsMenuDO::getMenuName, menuQueryParamDTO.getMenuName())
                .like(!ObjectUtils.isEmpty(menuQueryParamDTO.getParent()), UmsMenuDO::getParent, menuQueryParamDTO.getParent())
                .like(!ObjectUtils.isEmpty(menuQueryParamDTO.getSort()), UmsMenuDO::getSort, menuQueryParamDTO.getSort())
                .like(StringUtils.hasText(menuQueryParamDTO.getPath()), UmsMenuDO::getPath, menuQueryParamDTO.getPath());
        Page<UmsMenuDO> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, lambdaQueryWrapper);
        // 封装数据返回
        return new PageVO(page.getRecords(), page.getTotal());
    }

    @Override
    public MenuVO getMenuDetail(Long id) {
        UmsMenuDO umsMenuDO = getById(id);
        return BeanCopyUtils.copyBean(umsMenuDO, MenuVO.class);
    }

    /**
     * 构建tree
     * 先找出第一层的菜单,然后去找他们的子菜单设置到children属性中
     *
     * @param menus
     * @return
     */
    private List<MenuItemVO> builderMenuTree(List<MenuItemVO> menus) {
        return menus.stream()
                .filter(menu -> menu.getParent().equals(SystemConstants.NULL_PARENT))
                .map(menu -> menu.setChildren(getChildren(menu, menus)))
                .collect(Collectors.toList());
    }

    /**
     * 获取存入参数的子Menu集合
     *
     * @param menuItemVO
     * @param menus
     * @return
     */
    private List<MenuItemVO> getChildren(MenuItemVO menuItemVO, List<MenuItemVO> menus) {
        return menus.stream()
                .filter(m -> m.getParent().equals(menuItemVO.getId()))
                .map(m -> m.setChildren(getChildren(m, menus)))
                .collect(Collectors.toList());
    }
}
