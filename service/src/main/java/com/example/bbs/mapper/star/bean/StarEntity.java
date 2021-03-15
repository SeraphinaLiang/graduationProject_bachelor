package com.example.bbs.mapper.star.bean;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("t_star")
public class StarEntity {

    @ApiModelProperty("标星用户ID")
    private String userId;

    @ApiModelProperty("标星目标ID")
    private String subjectId;


}
