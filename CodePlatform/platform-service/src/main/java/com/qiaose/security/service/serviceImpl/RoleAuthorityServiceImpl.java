package com.qiaose.security.service.serviceImpl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiaose.entity.security.RoleAuthority;
import com.qiaose.mapper.security.RoleAuthorityMapper;
import com.qiaose.security.service.RoleAuthorityService;
@Service
public class RoleAuthorityServiceImpl extends ServiceImpl<RoleAuthorityMapper, RoleAuthority> implements RoleAuthorityService{

}
