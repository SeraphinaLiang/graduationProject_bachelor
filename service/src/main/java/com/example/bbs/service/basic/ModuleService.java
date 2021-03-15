package com.example.bbs.service.basic;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.bbs.common.domain.ResponseDTO;
import com.example.bbs.mapper.module.bean.ModuleEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface ModuleService extends IService<ModuleEntity> {

    ResponseDTO getPostsOfThisModule(int id,int currentPage);

    ResponseDTO getHotModule();
}
