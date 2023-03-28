package com.qiaose.entity.security;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="role_authority")
@Data
@Builder
@TableName(value = "role_authority")
@AllArgsConstructor //全参构造函数
@NoArgsConstructor  //无参构造函数
public class RoleAuthority {

    @ApiModelProperty(value="")
    private Integer roleId;


    @ApiModelProperty(value="")
    private Integer authorityId;

}