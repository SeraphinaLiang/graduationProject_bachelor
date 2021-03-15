package com.example.bbs.service.basic;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.bbs.common.domain.ResponseDTO;
import com.example.bbs.mapper.comment.bean.AddCommentDTO;
import com.example.bbs.mapper.comment.bean.CommentEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface CommentService extends IService<CommentEntity> {

    ResponseDTO addComment(AddCommentDTO addCommentDTO);

    ResponseDTO getAllComments();
}
