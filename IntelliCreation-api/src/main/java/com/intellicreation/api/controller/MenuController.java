package com.intellicreation.api.controller;


import com.intellicreation.api.facade.MemberFacade;
import com.intellicreation.common.ResponseResult;
import com.intellicreation.common.vo.PageVO;
import com.intellicreation.member.domain.dto.MenuQueryParamDTO;
import com.intellicreation.member.service.UmsMenuService;
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
 * @since 2024-01-26
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MemberFacade memberFacade;

    @GetMapping("/menuList")
    public ResponseResult getMenuList(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "5") Integer pageSize,
                                        MenuQueryParamDTO menuQueryParamDTO) {
        PageVO pageVO = memberFacade.queryMenuList(pageNum, pageSize, menuQueryParamDTO);
        return ResponseResult.okResult(pageVO);
    }

}
