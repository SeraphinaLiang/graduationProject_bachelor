package com.example.bbs.controller.funtional;

import com.example.bbs.common.domain.ResponseDTO;
import com.example.bbs.service.functional.UserEditService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserEditController { // 实现对用户发布的 帖子，offer，标星，评论 修改和删除

    private UserEditService userService;

    @Autowired
    public void setUserService(UserEditService userService) {
        this.userService = userService;
    }

    /**
     * 返回用户的帖子
     * @return
     */
    @GetMapping("/user/post/get")
    @RequiresAuthentication
    public ResponseDTO getUserPost(){
        return userService.getUserPost(1);
    }

    /**
     * 返回用户的offer
     * @return
     */
    @GetMapping("/user/offer/get")
    @RequiresAuthentication
    public ResponseDTO getUserOffer(){
        return userService.getUserPost(2);
    }

    /**
     * 返回用户标星的帖子 和 offer
     * @return
     */
    @GetMapping("/user/star/get")
    @RequiresAuthentication
    public ResponseDTO getUserStar(){
        return userService.getUserStar();
    }

    /**
     * 返回用户的评论
     * @return
     */
    @GetMapping("/user/comment/get")
    @RequiresAuthentication
    public ResponseDTO getUserComment(){
        return userService.getUserComment();
    }

    //-------------------------------------------------------------------

    @GetMapping("/user/delete/post")
    @RequiresAuthentication
    public ResponseDTO userDeletePost(@RequestParam String postID){
        return userService.userDeletePost(postID);
    }

    @GetMapping("/user/delete/star")
    @RequiresAuthentication
    public ResponseDTO userDeleteStar(@RequestParam String subjectID){
        return userService.userDeleteStar(subjectID);
    }

    @GetMapping("/user/delete/comment")
    @RequiresAuthentication
    public ResponseDTO userDeleteComment(@RequestParam String commentID){
        return userService.userDeleteComment(commentID);
    }

    @GetMapping("/post/query")
    public ResponseDTO getPostById(@RequestParam String postID){
        return userService.getPostById(postID);
    }

    //-----------------------------------------------------------------



}
