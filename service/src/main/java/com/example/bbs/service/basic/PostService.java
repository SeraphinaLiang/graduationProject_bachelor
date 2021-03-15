package com.example.bbs.service.basic;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.bbs.common.domain.ResponseDTO;
import com.example.bbs.mapper.post.bean.PostEntity;

public interface PostService extends IService<PostEntity> {
    ResponseDTO postSubmit(String newPost);

    ResponseDTO offerSubmit(String newOffer);

    ResponseDTO getAllPosts();
}
