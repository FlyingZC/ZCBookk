package com.example.administrator.zcbook.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.administrator.zcbook.R;
import com.example.administrator.zcbook.adapter.BookAdapter;
import com.example.administrator.zcbook.entity.Book;
import com.example.administrator.zcbook.util.HttpUtil;
import com.example.administrator.zcbook.util.JsonUtil;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/5.
 */
public class BookFragment extends Fragment {
    private ListView bookListView;
    private  ArrayList<Book> books;
    private ImageView bookImage;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new BookTask().execute();
    }

    //返回的view对象显示在界面上
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=View.inflate(getActivity(), R.layout.fragment_book,null);
        bookListView = (ListView) v.findViewById(R.id.book_listView);
        bookImage= (ImageView) v.findViewById(R.id.book_image);

        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                BookDetailFragment bookDetailFragment = new BookDetailFragment();
                Bundle bundle = new Bundle();
                Book book = books.get(position);
                bundle.putSerializable("book", book);
                bookDetailFragment.setArguments(bundle);
                ft.replace(R.id.framLayout_container, bookDetailFragment);
                ft.commit();
            }
        });
        return v;
    }

    private class BookTask extends AsyncTask<Void,Void,ArrayList<Book>>{

        @Override
        protected ArrayList<Book> doInBackground(Void... params){
           // ArrayList<Book> books=null;
            try{
                String requestParam="method=findBook";
                Log.i("BookFragment","发送请求"+requestParam);
                String resultJson= HttpUtil.getRequest(requestParam);
                Log.i("BookFragment","解析之前"+resultJson.toString());
                books=new JsonUtil().parseBook(resultJson);
                Log.i("BookFragment","解析完成"+books.toString());
            }catch(Exception e){

            }
            return books;
        }

        @Override
        protected void onPostExecute(ArrayList<Book> books) {
            super.onPostExecute(books);
            //三个参数context,ListView每一项的布局文件,填充内容
            BookAdapter adapter = new BookAdapter(getActivity(), R.layout.book_item, books);
            bookListView.setAdapter(adapter);
        }
    }



}
