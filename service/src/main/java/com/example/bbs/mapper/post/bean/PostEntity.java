package com.example.bbs.mapper.post.bean;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_post")
public class PostEntity {

    @TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("发布用户ID")
    private String publishUser;

    @ApiModelProperty("从属模块")
    private Integer module;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("更新时间")
    private Date updateTime;

    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("发布时间")
    private Date createTime;

    @ApiModelProperty("帖子内容")
    private String detail;

    private String briefing;

    private String title;

    @TableLogic
    private Integer deleted;//逻辑删除 0-未删除 1-删除

    @Version
    private Integer version;//乐观锁

    private Integer type; // 1-post  2 - offer

}
