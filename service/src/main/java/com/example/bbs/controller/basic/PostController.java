package com.example.bbs.controller.basic;

import com.example.bbs.common.domain.ResponseDTO;
import com.example.bbs.service.basic.PostService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {

    private PostService postService;

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    //--------------------------------------------------------------------------------------

    @PostMapping("/post/submit")
    @RequiresAuthentication
    public ResponseDTO postSubmit(@RequestBody String newPost) {
        return postService.postSubmit(newPost);
    }

    @PostMapping("/offer/submit")
    @RequiresAuthentication
    public ResponseDTO offerSubmit(@RequestBody String newOffer) {
        return postService.offerSubmit(newOffer);
    }

}
