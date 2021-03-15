package com.example.bbs.mapper.post;

import com.example.bbs.mapper.module.bean.ModuleEntity;
import com.example.bbs.mapper.post.bean.PostEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

@Mapper
@Component
public interface PostDao extends BaseMapper<PostEntity>{
    List<Integer> getHotModule();
}
