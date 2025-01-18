package com.it.pkj.controller;

import com.it.pkj.domain.SysUser;
import com.it.pkj.dto.UserDto;
import com.it.pkj.service.UserService;
import com.it.pkj.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // 注册用户
    @PostMapping("/register")
    public Result<SysUser> registerUser(@RequestBody UserDto userDto) {
        try {
            SysUser user = userService.registerUser(userDto);
            return Result.success(user);
        } catch (Exception e) {
            return Result.error(500, "注册用户失败：" + e.getMessage());
        }
    }

    // 登录用户
    @PostMapping("/login")
    public Result<String> loginUser(@RequestBody UserDto userDto) {
        return Result.success(userService.loginUser(userDto));
    }
    // 更新用户信息
    @PostMapping("/update/{id}")
    public Result<SysUser> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        try {
            SysUser user = userService.updateUser(id, userDto);
            return Result.success(user);
        } catch (Exception e) {
            return Result.error(500, "更新用户信息失败：" + e.getMessage());
        }
    }

    // 删除用户信息
    @PostMapping("/delete/{id}")
    public Result<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return Result.success(null);
        } catch (Exception e) {
            return Result.error(500, "删除用户信息失败：" + e.getMessage());
        }
    }
    // 获取用户信息
    @GetMapping("/{id}")
    public Result<SysUser> getUserById(@PathVariable Long id) {
        try {
            SysUser user = userService.getUserById(id);
            if (user == null) {
                return Result.error(404, "未找到用户信息");
            }
            return Result.success(user);
        } catch (Exception e) {
            return Result.error(500, "获取用户信息失败：" + e.getMessage());
        }
    }

    // 获取所有用户信息
    @GetMapping
    public Result<List<SysUser>> getAllUsers() {
        try {
            List<SysUser> users = userService.getAllUsers();
            return Result.success(users);
        } catch (Exception e) {
            return Result.error(500, "获取所有用户信息失败：" + e.getMessage());
        }
    }
}
