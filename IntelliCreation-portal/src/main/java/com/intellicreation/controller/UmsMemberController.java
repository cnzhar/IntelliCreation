package com.intellicreation.controller;


import com.intellicreation.annotation.SystemLog;
import com.intellicreation.domain.dto.ResponseResult;
import com.intellicreation.domain.model.UmsMemberDO;
import com.intellicreation.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author za
 * @since 2024-01-01
 */
@RestController
@RequestMapping("/member")
public class UmsMemberController {

    @Autowired
    private UmsMemberService umsMemberService;

    @GetMapping("/memberInfo")
    public ResponseResult memberInfo(){
        return umsMemberService.memberInfo();
    }

    // todo 看看要不要用dto接收
    @SystemLog(businessName = "更新用户信息")
    @PutMapping("/updateMemberInfo")
    public ResponseResult updateMemberInfo(@RequestBody UmsMemberDO member){
        return umsMemberService.updateMemberInfo(member);
    }

    // todo 看看有没有必要在前端就加密，不然明文在网络上传太不安全了
    // todo 看看要不要用dto接收
    @PostMapping("/register")
    public ResponseResult register(@RequestBody UmsMemberDO member){
        return umsMemberService.register(member);
    }
}
