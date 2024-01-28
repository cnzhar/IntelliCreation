package com.intellicreation.member.util;

import com.intellicreation.common.constant.SystemConstants;
import com.intellicreation.member.domain.dto.LoginMemberDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author za
 */
public class SecurityUtils {

    /**
     * 获取用户
     **/
    public static LoginMemberDTO getLoginMember() {
        return (LoginMemberDTO) getAuthentication().getPrincipal();
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static Boolean isSuperAdmin(){
        // todo 这一套体系方法是不是不太对
        // todo 获取的似乎是当前用户，使用这个方法的几个地方似乎都是要获取传入的用户，看看会不会有问题
        Long id = getLoginMember().getUmsMemberDO().getId();
        return id != null && id.equals(SystemConstants.ID_OF_SUPER_ADMIN);
    }

    public static Long getMemberId() {
        return getLoginMember().getUmsMemberDO().getId();
    }
}
