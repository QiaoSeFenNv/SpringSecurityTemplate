package com.qiaose.mapper.security;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qiaose.entity.security.Role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {
}