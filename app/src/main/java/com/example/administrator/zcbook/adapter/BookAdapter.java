package com.example.administrator.zcbook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.zcbook.R;
import com.example.administrator.zcbook.entity.Book;

import java.util.List;

/**
 * Created by Administrator on 2016/7/5.
 */
public class BookAdapter extends ArrayAdapter<Book> {

private int resourceId;

public BookAdapter(Context context, int textViewResourceId,
        List<Book> objects) {
        super(context,textViewResourceId, objects);
        resourceId = textViewResourceId;
        }

@Override
public View getView(int position, View convertView, ViewGroup parent) {
    Book book = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
        view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        viewHolder = new ViewHolder();
        viewHolder.bookImage = (ImageView) view.findViewById(R.id.book_image);
        viewHolder.bookName = (TextView) view.findViewById(R.id.book_name);
        view.setTag(viewHolder);
        } else {
        view = convertView;
        viewHolder = (ViewHolder) view.getTag();
        }
    //设置图片

        viewHolder.bookImage.setImageResource(R.drawable.c0);
        viewHolder.bookName.setText(book.getBookName());
        return view;
        }

class ViewHolder {

    ImageView bookImage;

    TextView bookName;

}

}
