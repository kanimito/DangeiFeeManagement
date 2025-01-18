package com.it.pkj.service;

import com.it.pkj.domain.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.it.pkj.dto.UserDto;

import java.util.List;

/**
* @author Administrator
* @description 针对表【user】的数据库操作Service
* @createDate 2025-01-16 12:42:17
*/
public interface UserService extends IService<SysUser> {
    SysUser registerUser(UserDto userDto);
    String loginUser(UserDto userDto);
    SysUser updateUser(Long id, UserDto userDto);
    void deleteUser(Long id);
    SysUser getUserById(Long id);
    List<SysUser> getAllUsers();
}
