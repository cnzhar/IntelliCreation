package com.intellicreation.api.facade;

import com.intellicreation.member.domain.entity.UmsMemberDO;
import com.intellicreation.member.domain.vo.AdminInfoVO;
import com.intellicreation.member.domain.vo.MenuVO;

import java.util.List;
import java.util.Map;

/**
 * @author za
 */
public interface LoginFacade {

    /**
     * 登录
     *
     * @param umsMemberDO
     * @return
     */
    Map<String, String> login(UmsMemberDO umsMemberDO);

    /**
     * 登出
     */
    void logout();

    AdminInfoVO getInfo();

    List<MenuVO> selectRouterMenuTreeByMemberId(Long memberId);
}
