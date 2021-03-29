package com.gh.services.common.domain;

import com.gh.config.security.Role;
import com.gh.utils.auth.AuthoritiesUtils;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
public class User implements UserDetails {

    private Long id;
    private String username;
    private String password;

    private Set<Role> roles = new HashSet<>() ;
    private Collection<? extends  GrantedAuthority> authorities;

    public static User build(User user) {
        Collection<? extends  GrantedAuthority> authorities = AuthoritiesUtils.createAuthorities(user);
        return user;
    }

    public User () {}
    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
    public User(Long id, String username, String password,
                Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
