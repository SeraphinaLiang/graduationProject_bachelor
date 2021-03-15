package com.example.bbs.controller.basic;

import com.example.bbs.common.domain.ResponseDTO;
import com.example.bbs.mapper.user.bean.dto.UserModifiedDTO;
import com.example.bbs.mapper.user.bean.dto.UserPasswordEditDTO;
import com.example.bbs.mapper.user.bean.dto.UserRegisterDTO;
import com.example.bbs.service.basic.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 修改用户基本信息（不包括照片）
     *
     * @param userModifiedDTO
     * @return
     * @RequiresAuthentication 需要登录后才可进行的操作
     */
    @PostMapping("/user/edit")
    @RequiresAuthentication
    public ResponseDTO userEdit(@RequestBody UserModifiedDTO userModifiedDTO) {
        return userService.userEdit(userModifiedDTO);
    }

    /**
     * 获得当前登录的用户
     * @return
     */
    @GetMapping("/user/info")
    @RequiresAuthentication
    public ResponseDTO getCurrentUser() {
        return userService.getCurrentUser();
    }

    /**
     * 修改用户密码
     * @return
     */
    @PostMapping("/user/edit/password")
    @RequiresAuthentication
    public ResponseDTO editPassword(@RequestBody UserPasswordEditDTO userPasswordEditDTO){
        return userService.editPassword(userPasswordEditDTO);
    }

    /**
     * 上传照片
     */
    @PostMapping("/user/upload/photo")
    @RequiresAuthentication
    public ResponseDTO uploadPhoto(@RequestBody MultipartFile photo) throws IOException {
        return userService.uploadPhoto(photo);
    }

    /**
     * 通过用户ID得到用户照片
     * @param id
     */
    @RequestMapping(value = "/user/photo",method = RequestMethod.POST)
    @RequiresAuthentication
    public ResponseDTO getPhotoById(@RequestBody String id){
        return userService.getPhotoByID(id);
    }

    /**
     * 通过用户ID得到用户照片(缩略图)
     * @param id
     */
    @PostMapping("/user/minorPhoto")
    public ResponseDTO getMinorPhotoById(@RequestBody String id){
        return userService.getMinorPhotoByID(id);
    }


    /**
     * 用户注册(未验证验证码)
     *
     * @param userRegisterDTO
     * @return 使用SecureUtil.md5(" password ")对密码进行单向加密
     * @Validated 用于表单验证
     */
    @PostMapping("/register/check")
    public ResponseDTO registerCheck(@Validated @RequestBody UserRegisterDTO userRegisterDTO) {
        return userService.registerCheck(userRegisterDTO);
    }

    /**
     * 用户注册（验证验证码）
     */
    @PostMapping("/register")
    public ResponseDTO register(@Validated @RequestBody UserRegisterDTO userRegisterDTO){
        return userService.register(userRegisterDTO);
    }


}
