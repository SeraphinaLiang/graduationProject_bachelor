package com.example.bbs.service.basic;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.bbs.common.domain.ResponseDTO;
import com.example.bbs.mapper.tag.bean.TagEntity;

public interface TagService extends IService<TagEntity> {
    ResponseDTO popularTag();
}
