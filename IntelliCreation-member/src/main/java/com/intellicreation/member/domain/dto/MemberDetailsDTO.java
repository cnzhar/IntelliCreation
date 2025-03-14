package com.intellicreation.member.domain.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.intellicreation.member.domain.entity.UmsMemberDO;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
public class MemberDetailsDTO implements UserDetails {

    private UmsMemberDO umsMemberDO;

    private List<String> permissions;

    @JSONField(serialize = false)
    private List<SimpleGrantedAuthority> authorities;

    public MemberDetailsDTO(UmsMemberDO umsMemberDO, List<String> permissionList) {
        this.umsMemberDO = umsMemberDO;
        this.permissions = permissionList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities != null) {
            return authorities;
        }
        authorities = permissions.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return authorities;
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
