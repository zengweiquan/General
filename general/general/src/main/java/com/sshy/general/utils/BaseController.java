package com.sshy.general.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sshy.general.exception.GeneralException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import javax.annotation.PostConstruct;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class BaseController {

    private static String url ="http://192.168.2.195:8650";

    public String token = "SSHY_GAME_123";
    public String redisChannel = "gamemoney";
    public Boolean debug = true;


    //对象转换
    public JSONObject getData(String str){
        return JSON.parseObject(str);
    }

    /**
     * 验证多个字符串是否为空
     * @param args
     * @return
     */
    public static boolean strIsEmpty(String... args){
        if(args.length==1) return StringUtils.isEmpty(args);
        for (int i = 0; i <args.length ; i++) {
           if (StringUtils.isEmpty(args[i])){
               return false;
           }
        }
        return true;
    }

    /**
     * 字符串验证
     * @param str
     * @param message
     * @return
     */
    public static String requireNonNull(String str, String message) {
        if (StringUtils.isEmpty(str))
            throw new NullPointerException(message);
        return str;
    }

    /**
     * 自定义当前时间的前后时间戳
     * @param day
     * @return
     */
    public static int getTimeSecond(int day){
        return (int) (LocalDate.now().plusDays(day).atStartOfDay().atZone(ZoneId.of("Asia/Shanghai")).toInstant().toEpochMilli()/1000);
    }


    @SneakyThrows
    public Boolean checkSign(Map map, String sign)
    {
        if(debug){return true;}
        LinkedList<String> mapSort = new LinkedList<String>();
        mapSort.addAll(map.keySet());
        Collections.sort(mapSort);
        String temp = "";
        for(final Iterator<String> map_iter = mapSort.iterator(); map_iter.hasNext();) {
            String key = map_iter.next();
            Object value = map.get(key);
            temp += value;
        }
        temp += token+"ssss";
        temp = md5(temp).toUpperCase();
        if(temp.equals(sign))
        {
            return true;
        }
        return false;
    }

    public String md5(String text) throws Exception {
        String encodeStr = DigestUtils.md5DigestAsHex(text.getBytes());
        return encodeStr;
    }

    public String UrlEncode(String str)
    {
        try {
            String urlString = URLEncoder.encode(str, "UTF-8").toLowerCase();
            return urlString;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String MapToURLParam(HashMap<String, String> map) {
        LinkedList<String> mapSort = new LinkedList<String>();
        mapSort.addAll(map.keySet());
        Collections.sort(mapSort);
        String temp = "";
        for(final Iterator<String> map_iter = mapSort.iterator(); map_iter.hasNext();) {
            String key = map_iter.next();
            String value = map.get(key);
            temp += key + "=" + value + "&";
        }
        return temp;
    }



    public String sendURL(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 调用C 程序
     * @param msgid        执行积分操作
     * @param UserID        用户id
     * @param Guild         公会id
     * @param lscore        积分
     * @param Summary       增加积分  2增加积分 3减少积分
     * @return
     */
    public static JSONObject enable_C_routine(int msgid, Integer UserID, Integer Guild, Integer lscore, int Summary) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msgid",1);                          //1修改积分
        JSONObject jsonArray = new JSONObject();
        jsonArray.put("UserID",UserID);
        jsonArray.put("Guild",Guild);
        jsonArray.put("lscore",lscore);
        jsonArray.put("Summary",Summary);                  //2添加积分 3减积分
        jsonObject.put("data",jsonArray);
        //2.调c程序
        String str = httpPost(url, jsonObject.toString());
        return JSON.parseObject(str);
    }

    /**
     * post 请求
     * @param strUrl
     * @param params
     * @return
     * @throws Exception
     */
    public static String httpPost(String strUrl, String params) throws Exception {
        URL url = new URL(strUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5 * 1000);
        conn.setReadTimeout(5 * 1000);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        conn.setInstanceFollowRedirects(false);
        conn.setRequestMethod("POST");
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
        writer.write(params);
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
            return builder.toString();
        }
        return "";
    }
}
