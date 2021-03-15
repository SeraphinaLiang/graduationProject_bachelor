package com.example.bbs.mapper.module;

import com.example.bbs.mapper.module.bean.ModuleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
@Component
public interface ModuleDao extends BaseMapper<ModuleEntity>{


}
