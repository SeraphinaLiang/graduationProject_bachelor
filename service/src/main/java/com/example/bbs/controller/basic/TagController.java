package com.example.bbs.controller.basic;

import com.example.bbs.service.basic.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TagController {

    private TagService tagService;

    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    //--------------------------------------------------------------------------------------

}
