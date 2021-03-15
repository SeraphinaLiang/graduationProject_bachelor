package com.example.bbs.util.hanlp;

import com.hankcs.hanlp.suggest.Suggester;

import java.util.List;

public class TextSuggest {

    /**
     * 文本推荐
     * @param source 待推荐文本
     * @param keywords 已进行分词的搜索内容
     * @param size 推荐的文本数量
     * @return 推荐的文本列表
     */
    public static List<String> getSuggestion(List<String> source,String keywords,int size) {
        Suggester suggester = new Suggester();

        for (String title : source)
        {
            suggester.addSentence(title);
        }

        List<String> list = suggester.suggest(keywords, size);
        return list;
    }

    //  https://github.com/hankcs/HanLP/tree/1.x
}
