package com.example.bbs.service.basic;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.bbs.common.domain.ResponseDTO;
import com.example.bbs.mapper.user.bean.UserEntity;
import com.example.bbs.mapper.user.bean.dto.UserModifiedDTO;
import com.example.bbs.mapper.user.bean.dto.UserPasswordEditDTO;
import com.example.bbs.mapper.user.bean.dto.UserRegisterDTO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface UserService extends IService<UserEntity> {

    ResponseDTO userEdit(UserModifiedDTO userModifiedDTO);

    ResponseDTO registerCheck(UserRegisterDTO userRegisterDTO);

    ResponseDTO register(UserRegisterDTO userRegisterDTO);

    ResponseDTO getCurrentUser();

    ResponseDTO editPassword(UserPasswordEditDTO userPasswordEditDTO);

    ResponseDTO uploadPhoto(MultipartFile photo) throws IOException;

    ResponseDTO getPhotoByID(String id);

    ResponseDTO getMinorPhotoByID(String id);

    ResponseDTO getAllUsers();

    ResponseDTO deleteUser(String id);

    ResponseDTO userDistribution();

    ResponseDTO userIncrease();

    ResponseDTO getTodayUserCount(String dateString);
}
