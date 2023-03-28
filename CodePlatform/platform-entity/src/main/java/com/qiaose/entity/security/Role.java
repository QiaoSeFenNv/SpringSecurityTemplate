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

import java.util.Set;

@ApiModel(value="`role`")
@Data
@Builder
@TableName(value = "`role`")
@AllArgsConstructor //全参构造函数
@NoArgsConstructor  //无参构造函数
public class Role {
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value="")
    private Integer id;

    @TableField(value = "`name`")
    @ApiModelProperty(value="")
    private String name;

    @TableField(value = "description")
    @ApiModelProperty(value="")
    private String description;

    @TableField(exist = false)
    private Set<Authority> authorities;
}