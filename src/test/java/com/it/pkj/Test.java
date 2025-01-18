package com.it.pkj;

import com.it.pkj.domain.SysUser;
import com.it.pkj.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: DangeiFeeManagement
 * @BelongsPackage: com.it.pkj
 * @Author: kanimito
 * @CreateTime: 2025-01-16  15:43
 * @Description: TODO
 * @Version: 1.0
 */
@SpringBootTest
public class Test {
    @Autowired
    private UserMapper sysUserMapper;

    @Autowired private PasswordEncoder passwordEncoder;

    @org.junit.jupiter.api.Test
    void contextLoads() { //导入了一个用户

        SysUser sysUser=new SysUser();

        sysUser.setUsername("zhangsan");
        sysUser.setPassword(passwordEncoder.encode("123456"));
        sysUser.setRole("USER");
        sysUserMapper.insert(sysUser);

    }
}
