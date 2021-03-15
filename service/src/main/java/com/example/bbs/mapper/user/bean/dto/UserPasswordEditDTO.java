package com.example.bbs.mapper.user.bean.dto;

import lombok.Data;

@Data
public class UserPasswordEditDTO {
    private String old;
    private String new1;
    private String new2;
}
