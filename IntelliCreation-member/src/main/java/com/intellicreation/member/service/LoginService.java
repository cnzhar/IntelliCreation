package com.intellicreation.member.service;

import com.intellicreation.common.ResponseResult;
import com.intellicreation.member.domain.dto.LoginMemberDTO;
import com.intellicreation.member.domain.entity.UmsMemberDO;

import java.util.Map;

/**
 * @author za
 */
public interface LoginService {

    /**
     * 登录
     *
     * @param loginMemberDTO
     * @return
     */
    Map<String, String> login(LoginMemberDTO loginMemberDTO);

    /**
     * 登出
     *
     * @return
     */
    void logout();

}
