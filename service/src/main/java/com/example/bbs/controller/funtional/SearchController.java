package com.example.bbs.controller.funtional;

import com.example.bbs.common.domain.ResponseDTO;
import com.example.bbs.mapper.post.bean.PostEntity;
import com.example.bbs.service.basic.PostService;
import com.example.bbs.util.hanlp.TextSuggest;
import com.example.bbs.util.hanlp.WordSegment;
import com.example.bbs.util.nlp.dataType.ExtractWord;
import com.example.bbs.util.nlp.dataType.SentenceArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class SearchController {

    private PostService postService;

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    /**
     * service层还没写
     * Elasticsearch 分词搜索
     * 模糊搜索
     * @param message
     * @return
     */
    @GetMapping("/search/{message}")
    public ResponseDTO search(@PathVariable String message){
        /*System.out.println("搜索内容: "+message);

        // 得到分词后的结果
        ExtractWord extractWord = new ExtractWord();
        String segmentWord = extractWord.getSlice(message);

        System.out.println("分词结果: "+segmentWord);

        // 设定待推荐文本
        SentenceArray sentenceArray = new SentenceArray();

        List<PostEntity> list = postService.list();
        Iterator iterator = list.iterator();

        int i=0;
        while (iterator.hasNext() && i<10){
            i++;
            PostEntity post = (PostEntity) iterator.next();
            // String id = "@"+post.getId();
            // String title = "@"+post.getTitle();
            String briefing = post.getBriefing();
            // String detail = "@" +post.getDetail();

            String element = briefing;
            sentenceArray.addElement(element);
        }
        String recommendText = sentenceArray.getSentenceArray();

        System.out.println("待推荐文本 "+recommendText);

        // 获取推荐结果
        List<String> suggestion= TextSuggest.textRecommend(segmentWord,recommendText,1);

        System.out.println(suggestion.toString());
*/

        WordSegment wordSegment = new WordSegment();
        String keywords = wordSegment.sliceSentence(message);

        List<String> list = new ArrayList<>();

        // 得到所有帖子的数据
        List<PostEntity> postEntityList = postService.list();
        Iterator iterator = postEntityList.iterator();

        while (iterator.hasNext()){
            PostEntity post = (PostEntity) iterator.next();

            String id = post.getId();
            String title = " "+post.getTitle();
            String briefing = " "+post.getBriefing();
            String detail = " "+post.getDetail();

            String element = id+title+briefing+detail;
            list.add(element);
        }

        List<String> result = TextSuggest.getSuggestion(list,keywords,5);
        List<PostEntity> postEntities = getPostList(result);

        return ResponseDTO.succData(postEntities);
    }

    private List<PostEntity> getPostList(List<String> result){
        List<PostEntity> list = new ArrayList<>();

        Iterator iterator = result.iterator();
        while (iterator.hasNext()){
            String s = (String) iterator.next();

            // 取前32位为id
            String id = s.substring(0,32);
            //System.out.println(id);

            PostEntity post = postService.getById(id);
            list.add(post);
        }
        return list;
    }
}
