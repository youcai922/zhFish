package com.example.zhFish.service;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.zhFish.entity.Content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author yucan
 * @date 2024/5/24 15:31
 */
public class MessageService {
    public static List<Content> getConteng() {
        List<Content> result = new ArrayList<>();
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("limit", "10");
        paramMap.put("desktop", "true");
        String result3 = HttpUtil.get("https://www.zhihu.com/api/v3/feed/topstory/recommend", paramMap);
        JSONObject jsonObject = JSONUtil.parseObj(result3);
        //获取到请求到的内容信息
        JSONArray contentInfoJSONArray = (JSONArray) jsonObject.get("data");
        contentInfoJSONArray.forEach(contentInfo -> {
            JSONObject targetObject = (JSONObject) ((JSONObject) contentInfo).get("target");
            //当前问题对象
            JSONObject questionObject = (JSONObject) targetObject.get("question");
            String title = (String) questionObject.get("title");
            String url = (String) targetObject.get("url");
            //当前回答内容
            StringBuilder contentString = new StringBuilder("\n");
            JSONObject json = JSONUtil.parseFromXml((String) targetObject.get("content"));
            try {
                if (json.get("p") instanceof JSONObject) {
                    contentString.append(((JSONObject) (json.get("p"))).get("content"));
                    return;
                }
                JSONArray pJsonArray = (JSONArray) json.get("p");
                pJsonArray.forEach(p -> {
                    if (p instanceof String) {
                        contentString.append(p);
                        return;
                    }
                    Object temp = ((JSONObject) p).get("content");
                    if (temp instanceof JSONArray) {
                        ((JSONArray) temp).forEach(tempp -> contentString.append(tempp + "\n"));
                    } else if (temp instanceof String) {
                        contentString.append(temp);
                    }
                });
                result.add(new Content(title, url, contentString.toString()));
            } catch (Exception exception) {
                System.out.println(json.toString());
                exception.printStackTrace();
            }
        });
        return result;
    }
}
