package com.it.pkj.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @BelongsProject: DangeiFeeManagement
 * @BelongsPackage: com.it.pkj.domain
 * @Author: kanimito
 * @CreateTime: 2025-01-16  15:23
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MySysUserDetails implements UserDetails {
    private Long userId;

    /**
     *
     */
    private String username;

    /**
     *
     */
    private String password;

    /**
     *
     */
    private String role;
    public MySysUserDetails(SysUser sysUser) {
        this.userId = sysUser.getUserId();
        this.username = sysUser.getUsername();
        this.password = sysUser.getPassword();
        this.role = sysUser.getRole();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
