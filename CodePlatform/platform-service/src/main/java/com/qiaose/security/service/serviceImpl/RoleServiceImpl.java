package com.qiaose.security.service.serviceImpl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiaose.entity.security.Role;
import com.qiaose.mapper.security.RoleMapper;
import com.qiaose.security.service.RoleService;
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService{

}
