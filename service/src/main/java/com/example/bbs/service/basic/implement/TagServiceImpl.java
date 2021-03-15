package com.example.bbs.service.basic.implement;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.bbs.common.domain.ResponseDTO;
import com.example.bbs.mapper.tag.TagDao;
import com.example.bbs.mapper.tag.bean.TagEntity;
import com.example.bbs.mapper.tag.bean.TagHot;
import com.example.bbs.service.basic.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl extends ServiceImpl<TagDao, TagEntity> implements TagService {

    private TagDao tagDao;

    @Autowired
    public void setTagDao(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    public ResponseDTO popularTag() {

        List<TagHot> list = tagDao.hotTag();

        List resList = new ArrayList();

        for (int i=0; i <10 && i< list.size() ;i++){
            resList.add(list.get(i));
        }
       // System.out.println(resList);

        return ResponseDTO.succData(resList);
    }
}
