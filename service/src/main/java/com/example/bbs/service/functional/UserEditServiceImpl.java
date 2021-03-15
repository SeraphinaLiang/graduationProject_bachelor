package com.example.bbs.service.functional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.bbs.common.domain.ResponseDTO;
import com.example.bbs.mapper.comment.CommentDao;
import com.example.bbs.mapper.comment.bean.CommentEntity;
import com.example.bbs.mapper.post.PostDao;
import com.example.bbs.mapper.post.bean.PostEntity;
import com.example.bbs.mapper.star.StarDao;
import com.example.bbs.mapper.star.bean.StarEntity;
import com.example.bbs.mapper.user.UserDao;
import com.example.bbs.mapper.user.bean.UserEntity;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class UserEditServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserEditService{

    private UserDao userDao;
    private PostDao postDao;
    private StarDao starDao;
    private CommentDao commentDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setPostDao(PostDao postDao) {
        this.postDao = postDao;
    }

    @Autowired
    public void setStarDao(StarDao starDao) {
        this.starDao = starDao;
    }

    @Autowired
    public void setCommentDao(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

   //------------------------------------------------------------------------------------------

    public ResponseDTO getUserPost(int type){
        UserEntity user = (UserEntity) SecurityUtils.getSubject().getPrincipal();

        QueryWrapper<PostEntity> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("publish_user",user.getId())
                .eq("type",type)   // 1-post  2 - offer
                .orderByDesc("create_time");
        List<PostEntity> postEntityList =  postDao.selectList(queryWrapper);

        return ResponseDTO.succData(postEntityList);
    }

    public ResponseDTO getUserStar(){
        UserEntity user = (UserEntity) SecurityUtils.getSubject().getPrincipal();

        // 根据用户id 拿到用户标星对象
        QueryWrapper<StarEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",user.getId());
        List<StarEntity> starEntities =  starDao.selectList(queryWrapper);

        // 根据标星，得到帖子的id
        List<PostEntity> postEntityList = new ArrayList<>();

        Iterator<StarEntity> iterator = starEntities.iterator();
        while (iterator.hasNext()){
            StarEntity s = iterator.next();
            PostEntity p = postDao.selectById(s.getSubjectId());
            if (p !=null){
                postEntityList.add(p);
            }
        }
      //   System.out.println(postEntityList);

        return ResponseDTO.succData(postEntityList);
    }

    public ResponseDTO getUserComment(){
        UserEntity user = (UserEntity) SecurityUtils.getSubject().getPrincipal();

        QueryWrapper<CommentEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("publish_user",user.getId()).orderByDesc("create_time");
        List<CommentEntity> commentEntityList =  commentDao.selectList(queryWrapper);

        return ResponseDTO.succData(commentEntityList);
    }

    //--------------------------------------

    public ResponseDTO userDeletePost(String postID){
        postDao.deleteById(postID);
        return ResponseDTO.succMsg("删除成功");
    }

    public ResponseDTO userDeleteStar(String subjectID){
        UserEntity user = (UserEntity) SecurityUtils.getSubject().getPrincipal();

        QueryWrapper<StarEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("subject_id",subjectID)
                .eq("user_id",user.getId());
        starDao.delete(queryWrapper);

        return ResponseDTO.succMsg("删除成功");
    }

    public ResponseDTO userDeleteComment(String commentID){
        commentDao.deleteById(commentID);
        return ResponseDTO.succMsg("删除成功");
    }

    @Override
    public ResponseDTO getPostById(String postID) {
        PostEntity p = postDao.selectById(postID);
        if (p!=null){
            return ResponseDTO.succData(p);
        }
        return ResponseDTO.failMsg("原贴已删除");
    }
}
