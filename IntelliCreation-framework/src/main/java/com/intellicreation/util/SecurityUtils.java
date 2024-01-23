package com.intellicreation.util;

import com.intellicreation.domain.dto.LoginMemberDTO;
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

//    public static Boolean isAdmin() {
//        Long id = getLoginMember().getUmsMemberDO().getId();
//        return id != null && id.equals(1L);
//    }

    public static Long getMemberId() {
        return getLoginMember().getUmsMemberDO().getId();
    }
}
