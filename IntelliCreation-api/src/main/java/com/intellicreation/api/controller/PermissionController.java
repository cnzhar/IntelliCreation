package com.intellicreation.api.controller;


import com.intellicreation.api.facade.MemberFacade;
import com.intellicreation.common.ResponseResult;
import com.intellicreation.common.vo.PageVO;
import com.intellicreation.member.domain.dto.PermissionQueryParamDTO;
import com.intellicreation.member.service.UmsPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author za
 * @since 2024-01-02
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private MemberFacade memberFacade;

    @GetMapping("/permissionList")
    public ResponseResult getPermissionList(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "5") Integer pageSize,
                                        PermissionQueryParamDTO permissionQueryParamDTO) {
        PageVO pageVO = memberFacade.queryPermissionList(pageNum, pageSize, permissionQueryParamDTO);
        return ResponseResult.okResult(pageVO);
    }

}
