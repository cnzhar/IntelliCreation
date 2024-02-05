package com.intellicreation.api.controller;


import com.intellicreation.api.facade.MemberFacade;
import com.intellicreation.common.vo.PageVO;
import com.intellicreation.common.annotation.SystemLog;
import com.intellicreation.common.ResponseResult;
import com.intellicreation.member.domain.dto.MemberQueryParamDTO;
import com.intellicreation.member.domain.entity.UmsMemberDO;
import com.intellicreation.member.domain.vo.MemberInfoVO;
import com.intellicreation.member.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author za
 * @since 2024-01-01
 */
@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private UmsMemberService umsMemberService;

    @Autowired
    private MemberFacade memberFacade;

    @GetMapping("/memberInfo")
    public ResponseResult memberInfo() {
        MemberInfoVO memberInfoVO = memberFacade.memberInfo();
        return ResponseResult.okResult(memberInfoVO);
    }

    // todo 看看要不要用dto接收
    @SystemLog(businessName = "更新用户信息")
    @PutMapping("/updateMemberInfo")
    public ResponseResult updateMemberInfo(@RequestBody UmsMemberDO member) {
        memberFacade.updateMemberInfo(member);
        return ResponseResult.okResult();
    }

    // todo 看看有没有必要在前端就加密，不然明文在网络上传太不安全了
    // todo 看看要不要用dto接收
    @PostMapping("/register")
    public ResponseResult register(@RequestBody UmsMemberDO member) {
        memberFacade.register(member);
        return ResponseResult.okResult();
    }

    @GetMapping("/memberList")
    public ResponseResult getMemberList(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "5") Integer pageSize,
                                        MemberQueryParamDTO memberQueryParamDTO) {
        PageVO pageVO = memberFacade.queryMemberList(pageNum, pageSize, memberQueryParamDTO);
        return ResponseResult.okResult(pageVO);
    }
}
