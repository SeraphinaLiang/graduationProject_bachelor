package com.example.bbs;

import com.example.bbs.util.hanlp.TextSuggest;
import com.example.bbs.util.hanlp.WordSegment;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class BbsApplicationTests {

    @Test
    void test() throws IOException {
        WordSegment wordSegment = new WordSegment();
        String keywords = wordSegment.sliceSentence("威廉 王子 发表演说 呼吁保护野生 动物");
        List<String> list = new ArrayList<>();
        list.add("王子今天去了趟城堡");
        list.add("你好吗？");
        List list1 = TextSuggest.getSuggestion(list,keywords,1);
        System.out.println(list1.toString());
    }



}
