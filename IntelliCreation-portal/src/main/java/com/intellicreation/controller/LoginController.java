package com.intellicreation.controller;

import com.intellicreation.constant.AppHttpCodeEnums;
import com.intellicreation.domain.dto.ResponseResult;
import com.intellicreation.domain.model.UmsMemberDO;
import com.intellicreation.exception.SystemException;
import com.intellicreation.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author za
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    // Todo 改用logindto接收
    @PostMapping("/login")
    public ResponseResult login(@RequestBody UmsMemberDO umsMemberDO){
        if(!StringUtils.hasText(umsMemberDO.getUid())){
            // 抛出异常，提示必须要传用户名
            throw new SystemException(AppHttpCodeEnums.REQUIRE_USERNAME);
        }
        return loginService.login(umsMemberDO);
    }

    @RequestMapping("/logout")
    public ResponseResult logout(){
        return loginService.logout();
    }
}
