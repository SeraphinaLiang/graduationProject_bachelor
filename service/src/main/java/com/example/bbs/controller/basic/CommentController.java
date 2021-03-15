package com.example.bbs.controller.basic;

import com.example.bbs.common.domain.ResponseDTO;
import com.example.bbs.mapper.comment.bean.AddCommentDTO;
import com.example.bbs.service.basic.CommentService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    private CommentService commentService;

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comment/add")
    @RequiresAuthentication
    public ResponseDTO addComment(@RequestBody AddCommentDTO addCommentDTO){
        return commentService.addComment(addCommentDTO);
    }
}
