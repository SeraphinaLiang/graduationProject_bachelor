package com.example.bbs.service.basic.implement;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.bbs.common.domain.ResponseDTO;
import com.example.bbs.mapper.comment.CommentDao;
import com.example.bbs.mapper.comment.bean.AddCommentDTO;
import com.example.bbs.mapper.comment.bean.CommentEntity;
import com.example.bbs.mapper.user.bean.UserEntity;
import com.example.bbs.service.basic.CommentService;
import com.example.bbs.service.functional.SensitiveService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentDao, CommentEntity> implements CommentService {

    private CommentDao commentDao;
    private SensitiveService sensitiveService;

    @Autowired
    public void setSensitiveService(SensitiveService sensitiveService) {
        this.sensitiveService = sensitiveService;
    }

    @Autowired
    public void setCommentDao(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    public ResponseDTO addComment(AddCommentDTO addCommentDTO) {
        UserEntity user = (UserEntity) SecurityUtils.getSubject().getPrincipal();

        CommentEntity commentEntity = new CommentEntity();
        // 敏感词过滤
        commentEntity.setContent(sensitiveService.filter(addCommentDTO.getContent()));
        commentEntity.setSubjectId(addCommentDTO.getSubjectId());
        commentEntity.setPublishUser(user.getId());

        commentDao.insert(commentEntity);

        return ResponseDTO.succMsg("发表评论成功");
    }

    @Override
    public ResponseDTO getAllComments() {
        QueryWrapper<CommentEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time");
        List<CommentEntity> list = commentDao.selectList(queryWrapper);
        return ResponseDTO.succData(list);
    }
}
