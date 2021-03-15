package com.example.bbs.mapper.user.bean;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/*
* annotations explanation
* @Data : 自动生成 getter, setter, toString 等
* @TableName : 对应数据库表名
* @AllArgsConstructor ：全参构造方法
* @NoArgsConstructor ：无参构造方法
* @TableId(type = IdType.ASSIGN_UUID) ：主键，insert时自动生成UUID
* @TableField(fill = FieldFill.INSERT) : insert 时自动生成日期
* */

@Data
@TableName("t_user")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "姓名")
    @NotBlank(message = "姓名不能为空")
    private String fullname;

    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "邮箱")
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "性别")
    private int gender;

    @ApiModelProperty(value = "照片")
    private byte[] photo;

    @ApiModelProperty(value = "照片缩略图")
    private byte[] minorPhoto;

    @ApiModelProperty(value = "注册时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableLogic
    private Integer deleted;//逻辑删除 0-未删除 1-删除

    @Version
    private Integer version;//乐观锁

}
