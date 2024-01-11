package com.intellicreation.service;

import com.intellicreation.domain.ResponseResult;
import com.intellicreation.domain.UmsMember;

/**
 * @author za
 */
public interface LoginServcie {
    ResponseResult login(UmsMember umsMember);

    ResponseResult logout();

}
