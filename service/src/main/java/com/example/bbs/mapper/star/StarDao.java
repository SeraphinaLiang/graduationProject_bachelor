package com.example.bbs.mapper.star;

import com.example.bbs.mapper.star.bean.StarEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
@Component
public interface StarDao extends BaseMapper<StarEntity>{
}
