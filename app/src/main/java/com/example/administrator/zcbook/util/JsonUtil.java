package com.example.administrator.zcbook.util;

import android.util.Log;

import com.example.administrator.zcbook.entity.BanJi;
import com.example.administrator.zcbook.entity.Book;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/4.
 */
public class JsonUtil {
    public String parseJsonLogin(String resultJson) throws JSONException {
        Log.i("JsonUtil", resultJson);
        JSONObject data = new JSONObject(resultJson);
        Log.i("JsonUtil",data.toString());
        String result = data.getString("result");
        Log.i("JsonUtil",result);
        return result;
    }

    public ArrayList<Book> parseBook(String resultJson) throws JSONException {
        JSONObject result=new JSONObject(resultJson);
        JSONArray data = result.getJSONArray("data");
        ArrayList<Book> books=new ArrayList<Book>();
        for(int i=0;i<data.length();i++){
            JSONObject book= data.getJSONObject(i);
            int bookId = book.getInt("bookId");
            String bookName = book.getString("bookName");
            String bookCover = book.getString("bookCover");
            String bookEditor = book.getString("bookEditor");
            String bookPress = book.getString("bookPress");
            Book bookEntity=new Book();
            bookEntity.setBookId(bookId);
            bookEntity.setBookName(bookName);
            bookEntity.setBookCover(bookCover);
            bookEntity.setBookEditor(bookEditor);
            bookEntity.setBookPress(bookPress);
            books.add(bookEntity);
        }
        Log.i("JsonUtil",books.toString());
        return books;
    }

    public ArrayList<BanJi> parseBanJi(String jsonResult) throws JSONException {
        JSONArray result=new JSONArray(jsonResult);
        ArrayList<BanJi> banJis=new ArrayList<BanJi>();
        for(int i=0;i<result.length();i++){
            JSONObject banJi=result.getJSONObject(i);
            int banJiId=banJi.getInt("banJiId");
            String banJiName=banJi.getString("banJiName");
            BanJi banJiEntity=new BanJi();
            banJiEntity.setBanJiId(banJiId);
            banJiEntity.setBanJiName(banJiName);
            banJis.add(banJiEntity);
        }
        return banJis;
    }
}
