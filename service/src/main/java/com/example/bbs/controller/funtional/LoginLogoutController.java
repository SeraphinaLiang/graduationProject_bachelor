package com.example.bbs.controller.funtional;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.bbs.common.domain.ResponseDTO;
import com.example.bbs.mapper.user.bean.UserEntity;
import com.example.bbs.mapper.user.bean.dto.UserLoginDTO;
import com.example.bbs.service.basic.UserService;
import com.example.bbs.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
@Slf4j
@RestController
public class LoginLogoutController {

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping("/login")
    public ResponseDTO login(@Validated @RequestBody UserLoginDTO loginDto, HttpServletResponse response) {
        UserEntity user = userService.getOne(new QueryWrapper<UserEntity>().eq("email", loginDto.getEmail()));
        if(user==null){
            return ResponseDTO.failMsg("用户不存在！");
        }
        //使用了md5为密码单向加密
        if(!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))) {
            return ResponseDTO.failMsg("密码错误！");
        }
        String jwt = jwtUtils.generateToken(user.getId());
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        // 用户可以另一个接口
        return ResponseDTO.succData(user,"登录成功");
    }

    // 退出
    @GetMapping("/logout")
    @RequiresAuthentication
    public ResponseDTO logout() {
      //  Object obj = SecurityUtils.getSubject().getPrincipal();
        //log.debug(obj.toString());
        SecurityUtils.getSubject().logout();
        return ResponseDTO.succMsg("退出登录成功");
    }

}
