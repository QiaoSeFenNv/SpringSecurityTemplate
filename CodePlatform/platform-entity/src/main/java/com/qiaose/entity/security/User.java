package com.qiaose.entity.security;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.security.acl.Permission;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@ApiModel(value="`user`")
@Data
@Builder
@TableName(value = "`user`")
@AllArgsConstructor //全参构造函数
@NoArgsConstructor  //无参构造函数
public class User implements UserDetails {
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value="")
    private Integer id;

    @TableField(value = "username")
    @ApiModelProperty(value="")
    private String username;

    @TableField(value = "`password`")
    @ApiModelProperty(value="")
    private String password;

    @TableField(value = "email")
    @ApiModelProperty(value="")
    private String email;

    @TableField(value = "phone")
    @ApiModelProperty(value="")
    private String phone;

    @TableField(value = "enabled")
    @ApiModelProperty(value="")
    private Byte enabled;

    @TableField(value = "last_login_time")
    @ApiModelProperty(value="")
    private Date lastLoginTime;

    @TableField(exist = false)
    private Set<Role> roles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Role role : roles) {
//            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
            for (Authority authority : role.getAuthorities()) {
                authorities.add(new SimpleGrantedAuthority(authority.getName()));
            }
        }
        return authorities;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}