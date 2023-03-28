package com.qiaose.security.service.serviceImpl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiaose.mapper.security.AuthorityMapper;
import com.qiaose.entity.security.Authority;
import com.qiaose.security.service.AuthorityService;

@Service
public class AuthorityServiceImpl extends ServiceImpl<AuthorityMapper, Authority> implements AuthorityService{

}
