package com.qiaose.mapper.security;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qiaose.entity.security.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}