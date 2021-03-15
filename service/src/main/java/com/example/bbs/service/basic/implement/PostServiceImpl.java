package com.example.bbs.service.basic.implement;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.bbs.common.domain.ResponseDTO;
import com.example.bbs.mapper.post.PostDao;
import com.example.bbs.mapper.post.bean.PostEntity;
import com.example.bbs.mapper.tag.TagDao;
import com.example.bbs.mapper.tag.bean.TagEntity;
import com.example.bbs.mapper.user.bean.UserEntity;
import com.example.bbs.service.basic.PostService;
import com.example.bbs.service.functional.SensitiveService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Service
public class PostServiceImpl extends ServiceImpl<PostDao, PostEntity> implements PostService {

    private PostDao postDao;
    private TagDao tagDao;
    private SensitiveService sensitiveService;

    @Autowired
    public void setSensitiveService(SensitiveService sensitiveService) {
        this.sensitiveService = sensitiveService;
    }

    @Autowired
    public void setPostDao(PostDao postDao) {
        this.postDao = postDao;
    }

    @Autowired
    public void setTagDao(TagDao tagDao){
        this.tagDao= tagDao;
    }

    @Override
    public ResponseDTO postSubmit(String newPost) {

        JSONObject post= new JSONObject(newPost);

        //title,briefing,detail
        String title=(String) post.get("title");
        String briefing=(String)post.get("briefing");
        String detail = (String)post.get("detail");
        //select（选择模块）
        String select=(String) post.get("select");
        int id= this.getIdOfModules(select);

        //1. 先创建post--------------
        PostEntity postEntity = new PostEntity();
        postEntity.setModule(id);//从属模块id
        UserEntity user = (UserEntity) SecurityUtils.getSubject().getPrincipal();
        postEntity.setPublishUser(user.getId());//发布用户id

        //帖子内容-敏感词过滤
        postEntity.setTitle(sensitiveService.filter(title));
        postEntity.setBriefing(sensitiveService.filter(briefing));
        postEntity.setDetail(sensitiveService.filter(detail));

        postEntity.setType(1);

        try {
            postDao.insert(postEntity);//插入数据库
        }catch (DataIntegrityViolationException e){
            return ResponseDTO.failMsg("标题或概述过长！ Title or Briefing is too long.");
        }


        //插入数据库后，给原对象返回id
        String currentPostId = postEntity.getId();

        //2. 再生成tags---------------
        JSONArray chosenTags = (JSONArray) post.get("chosenTags");
        Iterator iterator=chosenTags.iterator();
        while (iterator.hasNext()){
            TagEntity tag= new TagEntity();
            tag.setID(currentPostId);
            tag.setName(sensitiveService.filter((String) iterator.next()));
            tagDao.insert(tag);
        }

        return ResponseDTO.succMsg("post submit");
    }

    @Override
    public ResponseDTO offerSubmit(String newOffer) {
        JSONObject offer= new JSONObject(newOffer);

        //module ID
        String selectPlace = (String) offer.get("selectPlace");
        int id = this.getIdOfModules(selectPlace);

        String dateStart = (String)offer.get("dateStart");
        String dateEnd = (String)offer.get("dateEnd");
        String major = (String)offer.get("major");
        String school=(String)offer.get("school");
        String background=(String)offer.get("background");
        String selectDegree=(String)offer.get("selectDegree");

        //1. 先创建post--------------
        PostEntity postEntity = new PostEntity();
        postEntity.setModule(id);//从属模块id
        UserEntity user = (UserEntity) SecurityUtils.getSubject().getPrincipal();
        postEntity.setPublishUser(user.getId());//发布用户id

        //帖子内容
        postEntity.setTitle(school);
        postEntity.setBriefing(selectDegree+" "+major);
        postEntity.setDetail("TL: "+dateStart+" ---> "+dateEnd+"\n"+sensitiveService.filter(background));

        postEntity.setType(2);

        postDao.insert(postEntity);//插入数据库

        String currentPostId = postEntity.getId();

        //2. ----------再生成tags---------------
        JSONArray chosenTags = (JSONArray) offer.get("chosenTags");
        Iterator iterator=chosenTags.iterator();
        while (iterator.hasNext()){
            TagEntity tag= new TagEntity();
            tag.setID(currentPostId);
            tag.setName(sensitiveService.filter((String) iterator.next()));
            tagDao.insert(tag);
        }

        return ResponseDTO.succMsg("offer submit");
    }

    @Override
    public ResponseDTO getAllPosts() {
        QueryWrapper<PostEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("update_time");
        List<PostEntity> list = postDao.selectList(queryWrapper);
        return ResponseDTO.succData(list);
    }

    /**
     * 1,欧洲 Europe
     * 2,北美 north america
     * 3,亚洲 Asia
     * 4,澳洲 Australia
     * 5,香港 Hong kong
     * 6,英国 UK
     * 7,留学申请 Application
     * 8,签证移民 VISA & Immigration
     * 9,海外生活 Local Experience
     * 10,本地求职 Job & Career
     * 11,学习研究 Study & Research
     * 12,好物分享 Market & Deals
     */
     private int getIdOfModules(String s){
         String[] modules =new String[12];
         modules[0]="欧洲 Europe";
         modules[1]="北美 north america";
         modules[2]="亚洲 Asia";
         modules[3]="澳洲 Australia";
         modules[4]="香港 Hong kong";
         modules[5]="英国 UK";
         modules[6]="留学申请 Application";
         modules[7]="签证移民 VISA & Immigration";
         modules[8]="海外生活 Local Experience";
         modules[9]="本地求职 Job & Career";
         modules[10]="学习研究 Study & Research";
         modules[11]="好物分享 Market & Deals";

         int id=0;

         for (int i=0;i<modules.length;i++){
             if (modules[i].equals(s)){
                 id=i+1;
                 break;
             }
         }

         return id;
     }
}
