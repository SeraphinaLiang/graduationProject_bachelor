package com.example.bbs.mapper.comment.bean;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@Data
@TableName("t_comment")
public class CommentEntity {

    @ApiModelProperty("主键")
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty("评论用户ID")
    private String publishUser;

    @ApiModelProperty("评论帖子/offer的ID")
    private String subjectId;

    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("评论时间")
    private Date createTime;

    @ApiModelProperty("评论内容")
    private String content;

    @TableLogic
    private Integer deleted;//逻辑删除 0-未删除 1-删除

    @Version
    private Integer version;//乐观锁
}
