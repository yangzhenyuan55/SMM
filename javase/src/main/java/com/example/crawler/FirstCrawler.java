package com.example.crawler;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @Author: yzy
 * @Date: 2022/10/4-21:19
 * @Description:
 */
public class FirstCrawler {
    public static void main(String[] args) throws IOException {
        // 1.打开浏览器
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 2.输入网址
        HttpGet httpGet = new HttpGet("http://jwgl.cqjtu.edu.cn/jsxsd/");
        // 3.发起请求
        CloseableHttpResponse response = httpClient.execute(httpGet);
        // 4.解析响应，获取数据
        if(response.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity, "utf8");
            System.out.println(content);
        }

    }
}
