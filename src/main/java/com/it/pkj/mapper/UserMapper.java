package com.it.pkj.mapper;

import com.it.pkj.domain.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Administrator
* @description 针对表【user】的数据库操作Mapper
* @createDate 2025-01-16 12:42:17
* @Entity com.it.pkj.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<SysUser> {

}




