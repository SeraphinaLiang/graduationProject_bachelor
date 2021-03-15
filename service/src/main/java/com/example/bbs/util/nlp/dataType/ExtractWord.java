package com.example.bbs.util.nlp.dataType;

import com.example.bbs.util.nlp.Crf;
import com.example.bbs.util.nlp.KeyWordExtract;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashSet;

public class ExtractWord {
    HashSet<String> hashSet;

    public ExtractWord(){
        hashSet = new HashSet<>();
    }

    /**
     *
     * @param source 待切割文本
     * @return 切割后的文本
     */
    public String getSlice(String source){
        String keyWord = KeyWordExtract.extract(source);
        String wordSegment = Crf.crfWordSegment(source);

        jsonToString(keyWord);
        jsonToString(wordSegment);

        String result = hashSet.toString();

        return result;
    }

    private void jsonToString(String jsonStr){

        JSONObject jsarray = JSON.parseObject(jsonStr);

        int code = jsarray.getIntValue("code");
        if (code != 0){
            System.out.println("crf/keyword token 过期 !");
        }else {
            JSONArray data = jsarray.getJSONArray("data");
            for(int i=0;i<data.size();i++){
                JSONObject obj = data.getJSONObject(i);
                String word = obj.getString("word");
                hashSet.add(word);
            }
        }
    }


}
