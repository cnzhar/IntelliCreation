package com.intellicreation.member.service;

import com.intellicreation.common.ResponseResult;
import com.intellicreation.member.domain.entity.UmsMemberDO;

/**
 * @author za
 */
public interface AdminLoginService {

    /**
     * 登录
     *
     * @param umsMemberDO
     * @return
     */
    ResponseResult login(UmsMemberDO umsMemberDO);

    /**
     * 登出
     *
     * @return
     */
    ResponseResult logout();

}
