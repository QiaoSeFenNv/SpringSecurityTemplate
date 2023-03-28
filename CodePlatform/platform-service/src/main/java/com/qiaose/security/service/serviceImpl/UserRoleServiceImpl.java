package com.qiaose.security.service.serviceImpl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiaose.mapper.security.UserRoleMapper;
import com.qiaose.entity.security.UserRole;
import com.qiaose.security.service.UserRoleService;
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService{

}
