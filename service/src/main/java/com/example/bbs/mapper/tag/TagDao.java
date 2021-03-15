package com.example.bbs.mapper.tag;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.bbs.mapper.tag.bean.TagEntity;
import com.example.bbs.mapper.tag.bean.TagHot;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TagDao extends BaseMapper<TagEntity> {
    List<TagHot> hotTag();
}
