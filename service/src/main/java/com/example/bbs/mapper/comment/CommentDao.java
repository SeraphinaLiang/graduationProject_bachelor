package com.example.bbs.mapper.comment;

import com.example.bbs.mapper.comment.bean.CommentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
@Component
public interface CommentDao extends BaseMapper<CommentEntity>{

}
