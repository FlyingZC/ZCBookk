package com.example.administrator.zcbook.util;

import android.graphics.Bitmap;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/7/4.
 */
public class HttpUtil {
    private static final String TAG="HttpUtil";
    /**
     * @param
     * */
    public static String getRequest(String requestParams){
                HttpURLConnection connection=null;
                String resultJson=null;
                try{
                    // URL url=new URL("http://172.25.174.1:8080/MyAjax/cityServlet?method=getCityByMobile");
                    String path="http://153.36.162.18:8080/BookServer/bookServlet?"+requestParams;
                    //URL url=new URL("http://10.20.18.83:8080/MyAjax/cityServlet?method=getCityByMobile");
                    URL url=new URL(path);
                    Log.i(TAG,url.toString());
                    connection= (HttpURLConnection) url.openConnection();
                    Log.i(TAG, "建立连接");
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.connect();
                    if (connection.getResponseCode()!=HttpURLConnection.HTTP_OK){
                        Log.i(TAG,"连接错误");
                        return null;
                    }
                    InputStream in=connection.getInputStream();
                    BufferedReader reader=new BufferedReader(new InputStreamReader(in));
                    StringBuilder response=new StringBuilder();
                    String line;
                    while((line=reader.readLine())!=null){
                        response.append(line);
                    }
                   resultJson=response.toString();

                }catch (Exception e){
                }finally {
                    if(connection!=null){
                        connection.disconnect();
                    }
                }
             Log.i(TAG,resultJson);
             return resultJson;
            }

    public static String postRequest(String requestParams) throws Exception {
        //username = URLEncoder.encode(username);// 中文数据需要经过URL编码
        //String params = "username=" + username + "&password=" + password;
        String resultJson=null;
        HttpURLConnection connection=null;
        try{
            byte[] data = requestParams.getBytes();
            URL url = new URL("http://153.36.162.18:8080/BookServer/bookServlet");
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(3000);
            //这是请求方式为POST
            connection.setRequestMethod("POST");
            //设置post请求必要的请求头
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");// 请求头, 必须设置
            connection.setRequestProperty("Content-Length", data.length + "");// 注意是字节长度, 不是字符长度
            Log.i(TAG, "建立post连接");
            connection.setDoOutput(true);// 准备写出
            connection.getOutputStream().write(data);// 写出数据
            if (connection.getResponseCode()!=HttpURLConnection.HTTP_OK){
                Log.i(TAG,"连接错误");
                return null;
            }
            InputStream in=connection.getInputStream();
            BufferedReader reader=new BufferedReader(new InputStreamReader(in));
            StringBuilder response=new StringBuilder();
            String line;
            while((line=reader.readLine())!=null){
                response.append(line);
            }
            resultJson=response.toString();
     }catch(Exception e){
        }finally {
            if(connection!=null){
                connection.disconnect();
            }
    }
    Log.i(TAG,resultJson);
    return resultJson;
    }
    public Bitmap downLoadPic(){
        return null;
    }
}
