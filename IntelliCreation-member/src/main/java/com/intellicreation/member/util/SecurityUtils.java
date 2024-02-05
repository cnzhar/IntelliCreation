package com.intellicreation.member.util;

import com.intellicreation.common.constant.SystemConstants;
import com.intellicreation.member.domain.dto.MemberDetailsDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author za
 */
public class SecurityUtils {

    /**
     * 获取用户
     **/
    public static MemberDetailsDTO getLoginMember() {
        return (MemberDetailsDTO) getAuthentication().getPrincipal();
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 根据id判断是否是超级管理员
     *
     * @param id
     * @return
     */
    public static Boolean isSuperAdmin(Long id){
        return id != null && id == SystemConstants.ID_OF_SUPER_ADMIN;
    }

    /**
     * 判断当前用户是否是超级管理员
     *
     * @return
     */
    public static Boolean isSuperAdmin(){
        Long id = getLoginMember().getUmsMemberDO().getId();
        return id != null && id == SystemConstants.ID_OF_SUPER_ADMIN;
    }

    public static Long getMemberId() {
        return getLoginMember().getUmsMemberDO().getId();
    }
}
