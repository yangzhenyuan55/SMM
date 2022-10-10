package com.example.crawler;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author: yzy
 * @Date: 2022/10/4-17:00
 * @Description:
 */
public class SchoolWebsiteCrawler {
    //all static for convenience

    //file request parameters
    static String personType = "0";
    static String userNumber = "631909120523";
    static String password = "20160910Yzy@";
    static String verifyCode = "";


    final static String loginHomePage = "http://jwgl.cqjtu.edu.cn/jsxsd/xk/LoginToXk";

    final static String verifyImgSrcURL = "http://jwgl.cqjtu.edu.cn/jsxsd/verifycode.servlet?t=0.40226793120586435";

    final static String verifyImgDestURL = "E:\\Java\\verifyCodeImg.jpg";

    // http://jwgl.cqjtu.edu.cn/jsxsd/tkglAction.do?method=goListKbByXs
    final static String getTableBaseURL = "http://jwgl.cqjtu.edu.cn/jsxsd/tkglAction.do?method=goListKbByXs";

    // 获取当前时间
    final static String currentTimeURL = "http://jwgl.cqjtu.edu.cn/jsxsd/app.do?method=getCurrentTime&currDate=2022-07-12";

    //cookie name
    final static String sessionName = "JSESSIONID";

    // cookie value
    static String sessionValue = "";


    static Map<String, String> cookies = new HashMap<>();       //save cookies

    // 'reqData' initialization
    //


    public static void main(String[] args) throws IOException {

        //download verifyImg and
        //get the cookie we getfrom the img
        //use the cookie everywhere around the site.
        downloadImgReturnCookie(verifyImgSrcURL, verifyImgDestURL);

        System.out.println("Download Image Successfully");

        //print cookie
        System.out.println(cookies);

        //input verifycode
        System.out.print("Please input your verifyCode: ");
        verifyCode = new Scanner(System.in).nextLine();

        String encodename = "NjMxOTA5MTIwNTIz";
        String encodepwd = "MjAxNjA5MTBZenlA";
        Map<String, String> data = new HashMap<>();
        data.put("a", "insert");
        data.put("encoded", encodename+"%%%"+encodepwd);
        data.put("RANDOMCODE", verifyCode);

        // login using cookie prepared.
        Connection loginConn = Jsoup.connect(loginHomePage)
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36")
                .method(Connection.Method.POST)
                .data(data)
                .timeout(3000);


        // login using cookies
        loginConn.cookies(cookies);

        // try to login
        Connection.Response loginResponse = loginConn.execute();


        // System.out.println( loginResponse.body());
        System.out.println("login status : " + loginResponse.statusCode());
        System.out.println(loginResponse.body());


        Connection indexConn = Jsoup.connect("http://jwgl.cqjtu.edu.cn/jsxsd/framework/xsMain.jsp")
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36")
                .method(Connection.Method.GET)
                .timeout(3000);
        indexConn.cookies(cookies);


        Connection.Response indexResponse = indexConn.execute();
        System.out.println(indexResponse.body());

    }


    // download verifyCode image from 'srcUrl' and save to 'dest' at localhost
    private static void downloadImgReturnCookie (String srcUrl, String dest) throws
            MalformedURLException, IOException {

        // get image using the cookie
        HttpURLConnection imgConn = (HttpURLConnection) (new URL(srcUrl)).openConnection();


        // get the cookie
        Map<String, List<String>> headerFields = imgConn.getHeaderFields();
        // sessionValue = cookie.substring( cookie.indexOf('=') + 1, cookie.indexOf(';') );
        // cookies.put( sessionName,  sessionValue );
        String temp = headerFields.get("Set-Cookie").get(1);
        String cookie = temp.substring(temp.indexOf("=") + 1, temp.indexOf(";"));
        cookies.put(sessionName, cookie);
        //cookies.put("SERVERID", "124");

        try (BufferedInputStream imgInputStream = new BufferedInputStream(imgConn.getInputStream())) {
            // new output to local file system
            try (BufferedOutputStream imgOutputStream = new BufferedOutputStream(new FileOutputStream(dest))) {
                byte[] buf = new byte[1024];
                while (-1 != (imgInputStream.read(buf))) {
                    imgOutputStream.write(buf);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
