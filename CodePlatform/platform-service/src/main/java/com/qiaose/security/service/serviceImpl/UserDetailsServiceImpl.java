package com.qiaose.security.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qiaose.entity.security.*;
import com.qiaose.security.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    AuthorityService authorityService;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    RoleAuthorityService roleAuthorityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username:{}",username) ;

        User user = userService.getBaseMapper().selectOne(
                new LambdaQueryWrapper<User>() .eq(User::getUsername,username)
        );

        if (null != user) {
            //从中间表 根据用户id 查找 角色id 并转为 roleId 集合
            List<Integer> roleIds = userRoleService.getBaseMapper().selectList( new LambdaQueryWrapper<UserRole>() .eq(UserRole::getUserId,user.getId()) )
                    .stream().map(UserRole::getRoleId).collect(Collectors.toList());

            //根据roleId 查找 角色
            List<Role> role = roleService.getBaseMapper().selectList( new QueryWrapper<Role>().in("id", roleIds) );
            List<Integer> collect = role.stream().map(Role::getId).collect(Collectors.toList());

            ////从中间表 根据角色id 查找 权限id 并转为  authorityId集合
            List<Integer> authorityIds = roleAuthorityService.getBaseMapper().selectList(
                    new LambdaQueryWrapper<RoleAuthority>() .in(RoleAuthority::getRoleId,collect ))
                    .stream().map(RoleAuthority::getAuthorityId).collect(Collectors.toList());

            List<Authority> authorities = authorityService.getBaseMapper().selectList(new QueryWrapper<Authority>().in("id", authorityIds));
            //将角色权限赋予角色
            role.forEach(v -> v.setAuthorities(new HashSet<>(authorities)));
            //将角色赋予给用户
            user.setRoles(new HashSet<>(role));

            return user;
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }



}