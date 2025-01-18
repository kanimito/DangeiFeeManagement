package com.it.pkj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.it.pkj.domain.MySysUserDetails;
import com.it.pkj.domain.SysUser;
import com.it.pkj.dto.UserDto;
import com.it.pkj.mapper.UserMapper;
import com.it.pkj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

/**
* @author Administrator
* @description 针对表【user】的数据库操作Service实现
* @createDate 2025-01-16 12:42:17
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, SysUser>
    implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Override
    public SysUser registerUser(UserDto userDto) {
        SysUser user = convertToEntity(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.insert(user);
        return user;
    }

    @Override
    public String loginUser(UserDto userDto) {
        //        传入用户名和密码
        UsernamePasswordAuthenticationToken usernamePassword =
                new UsernamePasswordAuthenticationToken(userDto.getUsername(),userDto.getPassword());
        //是实现登录逻辑，此时就回去调用LoadUserByUsername方法
        Authentication authentication = authenticationManager.authenticate(usernamePassword);
        //        获取返回的用户信息
        Object principal = authentication.getPrincipal();
        //强转为MySysUserDetails类型
        MySysUserDetails mySysUserDetails = (MySysUserDetails) principal;
        //        输出用户信息
        System.err.println(mySysUserDetails);
        //返回token
        String token= UUID.randomUUID().toString();
        return token;
    }

    @Override
    public SysUser updateUser(Long id, UserDto userDto) {
        SysUser user = convertToEntity(userDto);
        user.setUserId(id);
        userMapper.updateById(user);
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        userMapper.deleteById(id);
    }

    @Override
    public SysUser getUserById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public List<SysUser> getAllUsers() {
        return userMapper.selectList(null);
    }

    private SysUser convertToEntity(UserDto userDto) {
        SysUser user = new SysUser();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        return user;
    }
}




