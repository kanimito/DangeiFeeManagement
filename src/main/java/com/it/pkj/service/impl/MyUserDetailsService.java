package com.it.pkj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.it.pkj.domain.MySysUserDetails;
import com.it.pkj.domain.SysUser;
import com.it.pkj.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: DangeiFeeManagement
 * @BelongsPackage: com.it.pkj.service.impl
 * @Author: kanimito
 * @CreateTime: 2025-01-16  15:14
 * @Description: TODO
 * @Version: 1.0
 */
@Component
public class MyUserDetailsService implements UserDetailsService {
    @Autowired private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = userMapper.selectOne(new LambdaQueryWrapper<SysUser>()
                .eq(username != null,SysUser::getUsername,username));
        if (sysUser == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        MySysUserDetails mySysUserDetails = new MySysUserDetails(sysUser);
        return mySysUserDetails;
    }
}
