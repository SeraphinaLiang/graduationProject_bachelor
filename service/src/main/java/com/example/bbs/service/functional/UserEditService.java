package com.example.bbs.service.functional;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.bbs.common.domain.ResponseDTO;
import com.example.bbs.mapper.user.bean.UserEntity;

public interface UserEditService extends IService<UserEntity> {
    ResponseDTO getUserPost(int type);

    ResponseDTO getUserStar();

    ResponseDTO getUserComment();

    ResponseDTO userDeletePost(String postID);

    ResponseDTO userDeleteStar(String subjectID);

    ResponseDTO userDeleteComment(String commentID);

    ResponseDTO getPostById(String postID);
}
