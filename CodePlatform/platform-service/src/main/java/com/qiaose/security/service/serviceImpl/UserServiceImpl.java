package com.qiaose.security.service.serviceImpl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiaose.entity.security.User;
import com.qiaose.mapper.security.UserMapper;
import com.qiaose.security.service.UserService;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

}
