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

@ApiModel(value="user_role")
@Data
@Builder
@TableName(value = "user_role")
@AllArgsConstructor //全参构造函数
@NoArgsConstructor  //无参构造函数
public class UserRole {

    @ApiModelProperty(value="")
    private Integer userId;


    @ApiModelProperty(value="")
    private Integer roleId;


}