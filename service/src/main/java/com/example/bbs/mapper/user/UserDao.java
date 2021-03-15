package com.example.bbs.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.bbs.mapper.user.bean.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
@Component
public interface UserDao extends BaseMapper<UserEntity> {

}
