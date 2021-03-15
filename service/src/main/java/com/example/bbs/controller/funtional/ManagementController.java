package com.example.bbs.controller.funtional;

import com.example.bbs.common.domain.ResponseDTO;
import com.example.bbs.mapper.message.MessageEntity;
import com.example.bbs.mapper.post.bean.PostEntity;
import com.example.bbs.service.basic.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.security.pkcs11.Secmod;

@RestController
public class ManagementController {

    private UserService userService;
    private PostService postService;
    private CommentService commentService;
    private MessageService messageService;
    private TagService tagService;
    private ModuleService moduleService;

    private String token = "hjhuvyjki8979@xwe8923hwd823AASW";

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPostService(PostService postService){
        this.postService = postService;
    }

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    @Autowired
    public void setModuleService(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    @PostMapping("/management/users")
    public ResponseDTO getAllUsers(@RequestParam String token) {
        if (this.token.equals(token)){
            return userService.getAllUsers();
        }
        return ResponseDTO.failMsg("不是管理员,没限权");
    }

    @PostMapping("/management/deleteUser")
    public ResponseDTO deleteUser(@RequestParam String token,@RequestParam String id){
        if (this.token.equals(token)){
            return userService.deleteUser(id);
        }
        return ResponseDTO.failMsg("不是管理员,没限权");
    }

    @PostMapping("/management/posts")
    public ResponseDTO getAllPosts(@RequestParam String token){
        if (this.token.equals(token)){
            return postService.getAllPosts();
        }
        return ResponseDTO.failMsg("不是管理员,没限权");
    }

    @PostMapping("/management/deletePost")
    public ResponseDTO deletePost(@RequestParam String token,@RequestParam String id){
        if (this.token.equals(token)){
            postService.removeById(id);
            return ResponseDTO.succ();
        }
        return ResponseDTO.failMsg("不是管理员,没限权");
    }

    @PostMapping("/management/comments")
    public ResponseDTO getAllComments(@RequestParam String token){
        if (this.token.equals(token)){
            return commentService.getAllComments();
        }
        return ResponseDTO.failMsg("不是管理员,没限权");
    }

    @PostMapping("/management/deleteComment")
    public ResponseDTO deleteComment(@RequestParam String token,@RequestParam String id){
        if (this.token.equals(token)){
            commentService.removeById(id);
            return ResponseDTO.succ();
        }
        return ResponseDTO.failMsg("不是管理员,没限权");
    }

    @PostMapping("/management/getPost")
    public ResponseDTO getPost(@RequestParam String token,@RequestParam String id){
        if (this.token.equals(token)){
            PostEntity postEntity=postService.getById(id);
            return ResponseDTO.succData(postEntity);
        }
        return ResponseDTO.failMsg("不是管理员,没限权");
    }

    @PostMapping("/sendMsg")
    public ResponseDTO sendMsg(@RequestBody MessageEntity m){
        return messageService.sendMsg(m);
    }

    @PostMapping("/allMsg")
    public ResponseDTO allMsg(@RequestParam String token){
        if (this.token.equals(token)){
            return messageService.allMsg();
        }
        return ResponseDTO.failMsg("不是管理员,没限权");
    }

    @PostMapping("/deleteMsg")
    public ResponseDTO deleteMsg(@RequestParam String token,@RequestParam String id){
        if (this.token.equals(token)){
            return messageService.deleteMsg(id);
        }
        return ResponseDTO.failMsg("不是管理员,没限权");
    }

    // dashboard ---------------------------------------------------------------

    @GetMapping("/userDistribution")
    public ResponseDTO userDistribution(){
        return userService.userDistribution();
    }

    @GetMapping("/userIncrease")
    public ResponseDTO userIncrease(){
        return userService.userIncrease();
    }

    @GetMapping("/popularTag")
    public ResponseDTO popularTag(){
        return tagService.popularTag();
    }

    @PostMapping("/userCountDate")
    public ResponseDTO getTodayUserCount(@RequestBody String dateString){
        return userService.getTodayUserCount(dateString);
    }

    @GetMapping("/getHotModule")
    public ResponseDTO getHotModule(){
        return moduleService.getHotModule();
    }

}
