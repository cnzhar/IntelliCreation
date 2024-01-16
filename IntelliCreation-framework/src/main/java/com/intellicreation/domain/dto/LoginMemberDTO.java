package com.intellicreation.domain.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.intellicreation.domain.model.UmsMemberDO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author za
 */
@Data
@NoArgsConstructor
public class LoginMemberDTO implements UserDetails {

    private UmsMemberDO umsMemberDO;

    private List<String> permissions;

//    public LoginMemberDTO(UmsMemberDO umsMemberDO, List<String> permissions) {
//        this.umsMemberDO = umsMemberDO;
//        this.permissions = permissions;
//    }

    public LoginMemberDTO(UmsMemberDO umsMemberDO) {
        this.umsMemberDO = umsMemberDO;
    }

//    @JSONField(serialize = false)
//    private List<SimpleGrantedAuthority> authorities;
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        if (authorities != null) {
//            return authorities;
//        }
//        authorities = permissions.stream()
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
//        return authorities;
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return umsMemberDO.getPassword();
    }

    @Override
    public String getUsername() {
        return umsMemberDO.getUid();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
