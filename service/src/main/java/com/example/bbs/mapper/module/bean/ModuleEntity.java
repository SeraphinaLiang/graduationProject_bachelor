package com.example.bbs.mapper.module.bean;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;

@Data
@TableName("t_module")
public class ModuleEntity {


    @ApiModelProperty("模块id")
    @TableId
    private Integer id;

    @ApiModelProperty("模块主题")
    private String topic;

/**
 * 1,欧洲 Europe
 * 2,北美 north america
 * 3,亚洲 Asia
 * 4,澳洲 Australia
 * 5,香港 Hong kong
 * 6,英国 UK
 * 7,留学申请 Application
 * 8,签证移民 VISA & Immigration
 * 9,海外生活 Local Experience
 * 10,本地求职 Job & Career
 * 11,学习研究 Study & Research
 * 12,好物分享 Market & Deals
 */

}
