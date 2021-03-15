package com.example.bbs.service.basic.implement;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.bbs.common.domain.ResponseDTO;
import com.example.bbs.mapper.star.StarDao;
import com.example.bbs.mapper.star.bean.StarEntity;
import com.example.bbs.mapper.user.bean.UserEntity;
import com.example.bbs.service.basic.StarService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service
public class StarServiceImpl extends ServiceImpl<StarDao, StarEntity> implements StarService {

    private StarDao starDao;

    @Autowired
    public void setStarDao(StarDao starDao) {
        this.starDao = starDao;
    }

    @Override
    public ResponseDTO addStarToPost(String postID) {
        UserEntity user = (UserEntity) SecurityUtils.getSubject().getPrincipal();

        StarEntity starEntity = new StarEntity();
        starEntity.setUserId(user.getId());
        starEntity.setSubjectId(postID);

        starDao.insert(starEntity);

        return ResponseDTO.succMsg("标星成功");
    }

    @Override
    public ResponseDTO deleteStarToPost(String postID) {
        UserEntity user = (UserEntity) SecurityUtils.getSubject().getPrincipal();
        QueryWrapper<StarEntity> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",user.getId())
                    .eq("subject_id",postID);
        starDao.delete(queryWrapper);

        return ResponseDTO.succMsg("取消标星成功");
    }
}
