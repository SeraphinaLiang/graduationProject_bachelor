package com.example.bbs.service.basic.implement;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.bbs.common.domain.ResponseDTO;
import com.example.bbs.mapper.comment.CommentDao;
import com.example.bbs.mapper.comment.bean.CommentEntity;
import com.example.bbs.mapper.module.ModuleDao;
import com.example.bbs.mapper.module.bean.ModuleEntity;
import com.example.bbs.mapper.post.PostDao;
import com.example.bbs.mapper.post.bean.PostEntity;
import com.example.bbs.mapper.star.StarDao;
import com.example.bbs.mapper.star.bean.StarEntity;
import com.example.bbs.mapper.tag.TagDao;
import com.example.bbs.mapper.tag.bean.TagEntity;
import com.example.bbs.mapper.user.bean.UserEntity;
import com.example.bbs.service.basic.ModuleService;
import lombok.Data;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ModuleServiceImpl extends ServiceImpl<ModuleDao, ModuleEntity> implements ModuleService {

    private ModuleDao moduleDao;
    private PostDao postDao;
    private CommentDao commentDao;
    private TagDao tagDao;
    private StarDao starDao;

    @Autowired
    public void setModuleDao(ModuleDao moduleDao) {
        this.moduleDao = moduleDao;
    }

    @Autowired
    public void setPostDao(PostDao postDao) {
        this.postDao = postDao;
    }

    @Autowired
    public void setCommentDao(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Autowired
    public void setTagDao(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Autowired
    public void setStarDao(StarDao starDao) {
        this.starDao = starDao;
    }

    @Override
    public ResponseDTO getPostsOfThisModule(int id, int currentPage) {

        // 返回的数据
        ResPageData resPageData = new ResPageData();

        // 1. 拿到分页的帖子 --------------
        Page page = new Page(currentPage, 10);
        QueryWrapper<PostEntity> queryWrapper = new QueryWrapper<PostEntity>()
                .eq("module", id)
                .orderByDesc("create_time");
        IPage pageData = postDao.selectPage(page, queryWrapper);

        // 加入到返回的数据中
        resPageData.setPage(pageData);

        //2. 拿到当前分页帖子的评论-------------------

        // 要返回的评论
        List<List<CommentEntity>> allCommentEntities = new ArrayList<>();

        // 根据当前的帖子拿到相应的评论
        Iterator iterator = pageData.getRecords().iterator();

        while (iterator.hasNext()) {
            // 拿到每一个帖子的评论
            PostEntity postEntity = (PostEntity) iterator.next();
            QueryWrapper<CommentEntity> qw = new QueryWrapper<>();
            qw.eq("subject_id", postEntity.getId()).orderByDesc("create_time");
            // 查询得到当前帖子的评论
            List<CommentEntity> commentEntityList = commentDao.selectList(qw);

            // 存储当前帖子的评论
            List<CommentEntity> com = new ArrayList<>();

            Iterator iter = commentEntityList.iterator();
            while (iter.hasNext()) {
                CommentEntity c = (CommentEntity) iter.next();
                com.add(c);
            }
            allCommentEntities.add(com);
        }
        // System.out.println(allCommentEntities);

        resPageData.setComments(allCommentEntities);

        // 3. 拿到当前帖子的标签-------------------
        List<List<String>> AllTags = new ArrayList<>();

        Iterator iter = pageData.getRecords().iterator();

        while (iter.hasNext()) {
            // 拿到每一个帖子的tag
            PostEntity postEntity = (PostEntity) iter.next();
            QueryWrapper<TagEntity> qw = new QueryWrapper<>();
            qw.eq("id", postEntity.getId());
            // 查询得到当前帖子的tag
            List<TagEntity> tagEntities = tagDao.selectList(qw);

            // 存储当前帖子的tag
            List<String> com = new ArrayList<>();

            Iterator i = tagEntities.iterator();
            while (i.hasNext()) {
                TagEntity t = (TagEntity) i.next();
                com.add(t.getName());
            }
            AllTags.add(com);
        }

        resPageData.setTags(AllTags);

        // 如果用户已登录
        UserEntity user = (UserEntity) SecurityUtils.getSubject().getPrincipal();

        if(user != null){
            // 4. 拿到当前 用户帖子 的标星
            List<Boolean> stared = new ArrayList<>();

            Iterator iterPost = pageData.getRecords().iterator();
            while (iterPost.hasNext()) {
                PostEntity p = (PostEntity) iterPost.next();
                QueryWrapper<StarEntity> starQueryWrapper = new QueryWrapper<>();
                starQueryWrapper.eq("user_id", user.getId())
                        .eq("subject_id", p.getId());

                int num = starDao.selectCount(starQueryWrapper);
                if (num == 0) {
                    stared.add(false);
                } else {
                    stared.add(true);
                }
            }
            resPageData.setStared(stared);
        }

        return ResponseDTO.succData(resPageData);
    }

    @Override
    public ResponseDTO getHotModule() {

        // 按照发帖数量排序的帖子模块
        List<Integer> module = postDao.getHotModule();
        // 拿到发帖数最高的5个模块，并获得其每月发帖数量
        List<HotModuleInfo> resData = new ArrayList<>(5);

        String fr = "2021-";
        String ba = "-1 00:00:00";

        for(int j=0;j<5;j++){
            // 当前模块每月发帖数量
            HotModuleInfo hotModuleInfo = new HotModuleInfo();
            hotModuleInfo.setName(module.get(j));

            for (int i = 1 ;i<=6;i++){ // 6 mouths
                String startDate = fr+i+ba;
                String endDate = fr+(i+1)+ba;

                // 拿到当前月的帖子
                QueryWrapper<PostEntity> qw = new QueryWrapper<>();
                qw.eq("module",module.get(j)).between("create_time",startDate,endDate);
                int num = postDao.selectCount(qw);
                hotModuleInfo.number.add(num);
            }

            resData.add(hotModuleInfo);
        }

        return ResponseDTO.succData(resData);
    }


    @Data
    class ResPageData {
        IPage page;
        List<List<CommentEntity>> comments;
        List<List<String>> tags;
        List<Boolean> stared = new ArrayList<>(10);
    }

    @Data
    class HotModuleInfo{
        String name;
        List<Integer> number = new ArrayList<>();

        public void setName(int n) {
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
            String[] s = {
                    "-1",
                    "欧洲 Europe",
                    "北美 North America",
                    "亚洲 Asia",
                    "澳洲 Australia",
                    "香港 Hong kong",
                    "英国 UK",
                    "留学申请 Application",
                    "签证移民 VISA & Immigration",
                    "海外生活 Local Experience",
                    "本地求职 Job & Career",
                    "学习研究 Study & Research",
                    "好物分享 Market & Deals"
            };

            this.name = s[n];
        }
    }

}
