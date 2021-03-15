package com.example.bbs.mapper.user.bean.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserModifiedDTO  implements Serializable {

    private String fullname;

    private String username;

    private String email;

    private int genderNumber;

}
