package com.example.bbs.controller.funtional;


import com.example.bbs.common.domain.ResponseDTO;
import com.example.bbs.service.functional.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Controller
public class MailController {
    @Autowired
    private MailService mailService;

    @PostMapping("/getCheckCode")
    @ResponseBody
    public ResponseDTO getCheckCode(@RequestBody String email){
        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
        String message = "毕业设计系统测试\n"
                         +"www.Dreamsup.cn\n"+
                         "您的注册验证码为："+checkCode;
        try {
            mailService.sendSimpleMail(email, "注册验证码", message);
        }catch (Exception e){
            return ResponseDTO.fail();
        }

        System.out.println(checkCode);
        return ResponseDTO.succMsg(checkCode);
    }
}
