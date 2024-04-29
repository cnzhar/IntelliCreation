package com.intellicreation.api.facade;

import com.intellicreation.common.vo.PageVO;
import com.intellicreation.member.domain.dto.LoginMemberDTO;
import com.intellicreation.member.domain.entity.UmsMemberDO;
import com.intellicreation.member.domain.vo.AdminInfoVO;
import com.intellicreation.member.domain.vo.MenuItemVO;

import java.util.List;
import java.util.Map;

/**
 * @author za
 */
public interface MemberFacade {

    /**
     * 登录
     *
     * @param loginMemberDTO
     * @return
     */
    Map<String, String> login(LoginMemberDTO loginMemberDTO);

    /**
     * 登出
     */
    void logout();

    AdminInfoVO getInfo();

    List<MenuItemVO> selectRouterMenuTreeByMemberId(Long memberId);
}
