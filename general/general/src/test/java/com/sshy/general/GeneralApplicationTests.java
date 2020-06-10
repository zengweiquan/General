package com.sshy.general;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sshy.general.exception.GeneralException;
import com.sshy.general.utils.BaseController;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.*;
import java.util.*;

@SpringBootTest
public class GeneralApplicationTests {
    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void contextLoads() {
        String s = UUID.randomUUID().toString().replace("-","");
        long endSecond = Instant.now().getEpochSecond();
        long startSecond=endSecond-(60*60*24*3);
        //三天前的时间戳从凌晨开始
        long l = LocalDate.now().plusDays(-3).atStartOfDay().atZone(ZoneId.of("Asia/Shanghai")).toInstant().toEpochMilli()/1000;


        System.out.println(l);

    }
    @Test
    public void t1(){

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msgid",1);
        JSONObject data = new JSONObject();
        data.put("UserID",12346);
        data.put("Guild",44);
        data.put("lscore",55);
        data.put("Summary",3);
        jsonObject.put("data",data);
        String url ="http://192.168.2.195:8650";
//        System.err.println(jsonObject);
//        Object o = restTemplate.postForObject(url, jsonObject, Object.class);
//        System.err.println(o);


        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
        // paramMap.add(null,jsonObject);

        String str="{\"data\":{\"UserID\":12346,\"lscore\":55,\"Summary\":3,\"Guild\":44},\"msgid\":1}";
        System.out.println(str);

        System.out.println(paramMap);

        HttpHeaders headers = new HttpHeaders();
        //headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(paramMap,headers);
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(url, str, String.class);
        //System.out.println("result2====================" + response2.getBody());

    }

    @Test
    public void t2() throws IOException {
        String str="{\"data\":{\"UserID\":12346,\"lscore\":55,\"Summary\":3,\"Guild\":44},\"msgid\":1}";
        String url1 ="http://192.168.2.195:8650";
        URL url = new URL(url1);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5 * 1000);
        conn.setReadTimeout(5 * 1000);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        conn.setInstanceFollowRedirects(false);
        conn.setRequestMethod("POST");
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
        writer.write(str);
        writer.close();
        int responseCode = conn.getResponseCode();
        if (responseCode == 200) {
            StringBuilder builder = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
            for (String s = br.readLine(); s != null; s = br.readLine()) {
                builder.append(s);
            }
            br.close();
            System.out.println(builder.toString());
        }
    }
    @Test
    public void t3(){
        char code='8';
        switch (code){
            case '8' :
                System.out.println("公会不存在");
                break;
            case '2' :
                System.out.println("游戏中不能提现");
                break;
        }
    }


}
