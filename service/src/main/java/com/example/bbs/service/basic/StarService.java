package com.example.bbs.service.basic;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.bbs.common.domain.ResponseDTO;
import com.example.bbs.mapper.star.bean.StarEntity;

public interface StarService extends IService<StarEntity> {

    ResponseDTO addStarToPost(String postID);

    ResponseDTO deleteStarToPost(String postID);
}
