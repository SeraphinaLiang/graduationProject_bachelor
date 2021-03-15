package com.example.bbs.controller.basic;

import com.example.bbs.common.domain.ResponseDTO;
import com.example.bbs.service.basic.StarService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StarController {

    private StarService starService;

    @Autowired
    public void setStarService(StarService starService) {
        this.starService = starService;
    }

    @GetMapping("/star/add/{postID}")
    @RequiresAuthentication
    public ResponseDTO addStarToPost(@PathVariable String postID){
        return this.starService.addStarToPost(postID);
    }

    @GetMapping("/star/delete/{postID}")
    @RequiresAuthentication
    public ResponseDTO deleteStarToPost(@PathVariable String postID){
        return this.starService.deleteStarToPost(postID);
    }

}
