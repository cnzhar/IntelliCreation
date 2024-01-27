package com.intellicreation.service;

import com.intellicreation.domain.dto.ResponseResult;
import com.intellicreation.domain.model.UmsMemberDO;

/**
 * @author za
 */
public interface MemberLoginService {

    /**
     * 登录
     * @param umsMemberDO
     * @return
     */
    ResponseResult login(UmsMemberDO umsMemberDO);

    /**
     * 登出
     * @return
     */
    ResponseResult logout();

}
