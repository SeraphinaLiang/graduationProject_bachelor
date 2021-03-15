package com.example.bbs.shiro;


import lombok.Data;

import java.io.Serializable;

@Data
public class AccountProfile implements Serializable {

    /**
     * 登录返回的 非敏感信息
     */
    private String id;

    private String username;

    private String fullname;

    private String email;

}
