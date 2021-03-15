package com.example.bbs.service.basic;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.bbs.common.domain.ResponseDTO;
import com.example.bbs.mapper.message.MessageDao;
import com.example.bbs.mapper.message.MessageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private MessageDao messageDao;

    @Autowired
    public void setMessageDao(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public ResponseDTO sendMsg(MessageEntity m) {
        messageDao.insert(m);
        return ResponseDTO.succ();
    }

    public ResponseDTO allMsg() {
        QueryWrapper<MessageEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time");
        List<MessageEntity> list = messageDao.selectList(queryWrapper);

        return ResponseDTO.succData(list);
    }

    public ResponseDTO deleteMsg(String id) {
        messageDao.deleteById(id);
        return ResponseDTO.succ();
    }
}
