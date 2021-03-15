package com.example.bbs.util.nlp.dataType;

import java.util.*;

public class SentenceArray<String> extends ArrayList<String> {

    ArrayList<String> list;

    public SentenceArray() {
        list = new ArrayList<>();
    }

    /**
     * 增加可推荐文本
     * @param s 可推荐文本
     */
    public void addElement(String s){
        s= (String) ("'"+s+"'");
        list.add(s);
    }

    /**
     *
     * @return 所有可推荐文本
     */
    public java.lang.String getSentenceArray(){
        return list.toString();
    }
}
