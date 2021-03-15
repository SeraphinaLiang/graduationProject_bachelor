package com.example.bbs.mapper.tag.bean;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("t_tag")
@AllArgsConstructor
@NoArgsConstructor
public class TagEntity {

    @ApiModelProperty("标签内容")
    private String name;

    @ApiModelProperty("标签关联ID")
    private String ID;

}
