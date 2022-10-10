package com.example.crawler;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @Author: yzy
 * @Date: 2022/10/4-21:36
 * @Description:
 */
public class HttpGetTest {
    public static void main(String[] args) throws Exception {

        // 创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 创建HttpGet对象，设置url访问地址
        // HttpGet httpGet = new HttpGet("https://www.baidu.com");
        // 设置请求参数
        // 创建UriBuilder
        URIBuilder uriBuilder = new URIBuilder("https://www.baidu.com");
        uriBuilder.setParameter("oq", "Java");
        HttpGet httpGet = new HttpGet(uriBuilder.build());

        // 使用HttpClient对象发起请求
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            // 解析响应
            if(response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                String content = EntityUtils.toString(entity, "utf8");
                System.out.println(content);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            try {
                httpClient.close();
            } catch (IOException e) {

                throw new RuntimeException(e);
            }
        }
    }
}
