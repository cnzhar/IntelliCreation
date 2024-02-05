package com.intellicreation.api.controller;


import com.intellicreation.api.facade.MemberFacade;
import com.intellicreation.common.ResponseResult;
import com.intellicreation.common.vo.PageVO;
import com.intellicreation.member.domain.dto.RoleQueryParamDTO;
import com.intellicreation.member.domain.vo.RoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author za
 * @since 2024-01-25
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private MemberFacade memberFacade;

    @GetMapping("/roleList")
    public ResponseResult getRoleList(@RequestParam(defaultValue = "1") Integer pageNum,
                                      @RequestParam(defaultValue = "5") Integer pageSize,
                                      RoleQueryParamDTO roleQueryParamDTO) {
        PageVO pageVO = memberFacade.queryRoleList(pageNum, pageSize, roleQueryParamDTO);
        return ResponseResult.okResult(pageVO);
    }

    @GetMapping("/getRole/{id}")
    public ResponseResult getRoleDetail(@PathVariable Long id) {
        RoleVO roleVO = memberFacade.getRoleDetail(id);
        return ResponseResult.okResult(roleVO);
    }
}
