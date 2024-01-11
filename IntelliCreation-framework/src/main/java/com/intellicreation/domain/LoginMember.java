//package com.intellicreation.model.domain;
//
//import com.alibaba.fastjson.annotation.JSONField;
//import com.intellicreation.domain.UmsMember;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * @author za
// */
//@Data
//@NoArgsConstructor
//public class LoginMember implements UserDetails {
//
//    private UmsMember umsMember;
//
//    private List<String> permissions;
//
//    public LoginMember(UmsMember umsMember, List<String> permissions) {
//        this.umsMember = umsMember;
//        this.permissions = permissions;
//    }
//
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
//
//    @Override
//    public String getPassword() {
//        return umsMember.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return umsMember.getUid();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
