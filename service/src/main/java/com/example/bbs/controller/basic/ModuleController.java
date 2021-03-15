package com.example.bbs.controller.basic;

import com.example.bbs.common.domain.ResponseDTO;
import com.example.bbs.service.basic.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ModuleController {

    private ModuleService moduleService;

    @Autowired
    public void setModuleService(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    //--------------------------------------------------------------------------------------

    @GetMapping("/module/{id}/{currentPage}")
    public ResponseDTO getPostsOfThisModule(@PathVariable int id,@PathVariable int currentPage){
        return moduleService.getPostsOfThisModule(id,currentPage);
    }

}
