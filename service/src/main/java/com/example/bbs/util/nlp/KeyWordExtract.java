package com.example.bbs.util.nlp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


public class KeyWordExtract {
    // 基于 Hanlp API 接口的关键字提取

    //请求头中的token
    private static String token= com.example.bbs.util.nlp.token.keywordExtract;

    //申请的接口地址
    private static String url="http://comdo.hanlp.com/hanlp/v1/keyword/extract";

    /**
     * 提取关键字
     * @param text 想要提取关键词的文本
     * @param size 想要提取关键词的个数，size数不能超过文本字数
     * @return 关键字
     */
    public static String extract(String text,int size){
        //所有参数
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("text", text);
        params.put("size", String.valueOf(size));
        //执行api
        String result=doHanlpApi(token,url,params);
        return result;
    }

    public static String extract(String text){
        //所有参数
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("text", text);
        params.put("size", String.valueOf(text.length()-1));
        //执行api
        String result=doHanlpApi(token,url,params);
        return result;
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
