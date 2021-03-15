package com.example.bbs.util.nlp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


public class TextSuggest {
    // 基于 Hanlp API 接口的文本推荐

    //请求头中的token
    private static String token= com.example.bbs.util.nlp.token.textSuggest;

    //申请的接口地址
    private static String url="http://comdo.hanlp.com/hanlp/v1/suggester/textSuggest";

    /**
     *
     * @param segmentText 已完成分词的文本
     * @param recommendSource 能够进行推荐的列表
     * @param size 需要提取的推荐个数
     * @return size个推荐的文本
     */
    public static List<String> textRecommend(String segmentText, String recommendSource, int size){
        /**
         * 参数示例：
         * params.put("text", "你");
         * params.put("sentenceArray", "['我的天空','你的天空']");
         * params.put("size", "1");
         */
        //所有参数
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("text", segmentText);
        params.put("sentenceArray", recommendSource);
        params.put("size", String.valueOf(size));
        //执行api
        String result=doHanlpApi(token,url,params);

        System.out.println(result);

        // 处理返回结果
        List<String> resultHandle = jsonToString(result);

        return resultHandle;
    }

    private static List<String> jsonToString(String jsonString){

        JSONObject array = JSON.parseObject(jsonString);
        int code = array.getIntValue("code");
        if(code !=0 ){
            Exception e =new Exception("-------------token 过期---------------");
            // e.printStackTrace();
        }else {
            JSONArray data = array.getJSONArray("data");
            // JSONArray -> list
            List<String> list = JSONObject.parseArray(data.toJSONString(), String.class);

            return list;
        }
        return null;
    }

    private static String doHanlpApi(String token,String url,Map<String,Object> params) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            //添加header请求头，token请放在header里
            httpPost.setHeader("token", token);
            // 创建参数列表
            List<NameValuePair> paramList = new ArrayList<>();
            if (params != null) {
                for (String key : params.keySet()) {
                    //所有参数依次放在paramList中
                    paramList.add(new BasicNameValuePair(key, (String) params.get(key)));
                }
                //模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, "utf-8");
                httpPost.setEntity(entity);
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
            return resultString;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(response!=null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
