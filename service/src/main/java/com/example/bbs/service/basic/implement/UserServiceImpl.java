package com.example.bbs.service.basic.implement;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.bbs.common.constant.ResponseCodeConst;
import com.example.bbs.common.domain.ResponseDTO;
import com.example.bbs.mapper.user.UserDao;
import com.example.bbs.mapper.user.bean.UserEntity;
import com.example.bbs.mapper.user.bean.dto.UserModifiedDTO;
import com.example.bbs.mapper.user.bean.dto.UserPasswordEditDTO;
import com.example.bbs.mapper.user.bean.dto.UserRegisterDTO;
import com.example.bbs.service.basic.UserService;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public ResponseDTO userEdit(UserModifiedDTO userModifiedDTO) {
        UserEntity user = userDao.selectOne(new QueryWrapper<UserEntity>()
                .eq("email", userModifiedDTO.getEmail()));
        user.setUsername(userModifiedDTO.getUsername());
        user.setFullname(userModifiedDTO.getFullname());
        user.setGender(userModifiedDTO.getGenderNumber());
        //user.setPhoto((byte) 0);
        userDao.updateById(user);

        return new ResponseDTO(ResponseCodeConst.SUCCESS, "修改成功");
    }

    @Override
    public ResponseDTO registerCheck(UserRegisterDTO userRegisterDTO) {
        // 1. 邮箱已注册
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("email", userRegisterDTO.getEmail());
        if (userDao.selectOne(wrapper) != null) {
            return ResponseDTO.failMsg("该邮箱已注册");
        }
        // 2. 密码不一致
        if (!userRegisterDTO.getPassword1().equals(userRegisterDTO.getPassword2())) {
            return ResponseDTO.failMsg("密码输入不一致");
        }

        return ResponseDTO.succ();
    }

    @Override
    public ResponseDTO register(UserRegisterDTO userRegisterDTO) {

        // 前端已经判断过验证码是否正确，直接往数据库中新增用户
        UserEntity user = new UserEntity();
        user.setFullname(userRegisterDTO.getFullname());
        user.setUsername(userRegisterDTO.getUsername());
        user.setEmail(userRegisterDTO.getEmail());
        //加密密码
        user.setPassword(SecureUtil.md5(userRegisterDTO.getPassword1()));
        userDao.insert(user);

        return ResponseDTO.succ();
    }

    @Override
    public ResponseDTO getCurrentUser() {
        //当前登录的用户
        UserEntity user = (UserEntity) SecurityUtils.getSubject().getPrincipal();
        //  System.out.println(user.toString());
        return ResponseDTO.succData(user);
    }

    @Override
    public ResponseDTO editPassword(UserPasswordEditDTO pass) {
        //System.out.println(pass.toString());
        UserEntity user = (UserEntity) SecurityUtils.getSubject().getPrincipal();

        if (!user.getPassword().equals(SecureUtil.md5(pass.getOld()))) {
            return ResponseDTO.failMsg("旧密码输入错误");
        }
        if (!pass.getNew1().equals(pass.getNew2())) {
            return ResponseDTO.failMsg("两次新密码输入不一致");
        }

        user.setPassword(SecureUtil.md5(pass.getNew1()));
        userDao.updateById(user);

        return ResponseDTO.succMsg("密码修改成功！");
    }

    @Override
    public ResponseDTO uploadPhoto(MultipartFile photo) {
        // System.out.println(photo.toString());

        UserEntity user = (UserEntity) SecurityUtils.getSubject().getPrincipal();
        try {
            //头像
            user.setPhoto(photo.getBytes());
            //头像缩略图
            user.setMinorPhoto(this.minorPhotoToByte(photo));

            userDao.updateById(user);
            return ResponseDTO.succMsg("上传成功");
        } catch (Exception e) {
            return ResponseDTO.failMsg("上传失败");
        }
    }

    @Override
    public ResponseDTO getPhotoByID(String id) {

      /*  1. 先把二进制转成BASE64，再传。
          2. 前端直接显示base64格式照片 */

        byte[] b = null;
        //从数据库拿到照片，存近 b (二进制)
        UserEntity userEntity = userDao.selectById(id);
        b = userEntity.getPhoto();

        String base64Data=null;
        try {
            base64Data = getImageString(b);
        }catch (Exception e){
            e.printStackTrace();
        }

        return ResponseDTO.succData(base64Data);
    }

    @Override
    public ResponseDTO getMinorPhotoByID(String id) {
        byte[] b = null;
        //从数据库拿到照片缩略图，存近 b
        UserEntity userEntity = userDao.selectById(id);
        b = userEntity.getMinorPhoto();

        String base64Data=null;
        try {
            base64Data = getImageString(b);
        }catch (Exception e){
            e.printStackTrace();
        }

        return ResponseDTO.succData(base64Data);
    }

    @Override
    public ResponseDTO getAllUsers() {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time");
        List<UserEntity> list = userDao.selectList(queryWrapper);
        return ResponseDTO.succData(list);
    }

    @Override
    public ResponseDTO deleteUser(String id) {
        try {
            userDao.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDTO.failMsg("删除失败");
        }
        return ResponseDTO.succ();
    }

    @Override
    public ResponseDTO userDistribution() {
        int num[] = new int[4];

        for (int i = 0; i < 4; i++) {
            QueryWrapper<UserEntity> qw = new QueryWrapper<>();
            qw.eq("gender", i);
            int n = userDao.selectCount(qw);
            num[i] = n;
        }

        return ResponseDTO.succData(num);
    }

    @Override
    public ResponseDTO userIncrease() {
        return ResponseDTO.succ();
    }

    @Override
    public ResponseDTO getTodayUserCount(String dateString) {

        dateString = dateString.replace('/', '-');
        String dateTime = dateString + " 23:59:59";

        int count[] = new int[2];

        QueryWrapper<UserEntity> qw1 = new QueryWrapper<>();
        String start = dateString + " 00:00:00";
        String end = dateString + " 23:59:59";
        qw1.between("create_time",start,end); // 当日
        int todayNumber = userDao.selectCount(qw1);
        count[0] = todayNumber;

        QueryWrapper<UserEntity> qw2 = new QueryWrapper<>();
        qw2.le("create_time",dateTime);  // 累计
        int accNumber = userDao.selectCount(qw2);
        count[1] = accNumber;

        return ResponseDTO.succData(count);
    }


    //---------------------工具函数----------------------------------

    /**
     * 获取图片缩略图并转换为byte
     *
     * @param file
     * @return
     */
    public byte[] minorPhotoToByte(MultipartFile file) {
        try {
            //获取图片缩略图
            Thumbnails.Builder<? extends InputStream> builder = Thumbnails.of(file.getInputStream()).size(100, 100);
            //将返回值转换为byte
            BufferedImage bufferedImage = builder.asBufferedImage();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 二进制流转Base64字符串
     * @param data 二进制流
     * @return data
     * @throws IOException 异常
     */
    public static String getImageString(byte[] data) throws IOException {
        BASE64Encoder encoder = new BASE64Encoder();
        return data != null ? encoder.encode(data) : "";
    }
}
