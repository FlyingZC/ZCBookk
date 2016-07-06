package com.example.administrator.zcbook.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.zcbook.R;
import com.example.administrator.zcbook.entity.Book;

/**
 * Created by Administrator on 2016/7/5.
 */
public class BookDetailFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=View.inflate(getActivity(), R.layout.fragment_bookdetail, null);
        v.findViewById(R.id.textView_bookdetail);
        TextView textViewName= (TextView) v.findViewById(R.id.textView_bookdetail_name);
        TextView textViewEditor= (TextView) v.findViewById(R.id.textView_bookdetail_editor);
        TextView textViewPress= (TextView) v.findViewById(R.id.textView_bookdetail_press);
        Button btnYuDing = (Button) v.findViewById(R.id.btn_yuding);
        Button reset = (Button) v.findViewById(R.id.btn_bookdetail_reset);
        //获取Bundle里的数据
        Bundle bundle=getArguments();
        final Book currentBook =(Book)bundle.getSerializable("book");
        Log.i("BookDetailFragment", currentBook.getBookName());
        textViewName.setText(currentBook.getBookName());
        textViewEditor.setText(currentBook.getBookEditor());
        textViewPress.setText(currentBook.getBookPress());
        btnYuDing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                YuDingFragment yuDingFragment = new YuDingFragment();
                Bundle bundle = new Bundle();

                bundle.putSerializable("book", currentBook);
                yuDingFragment.setArguments(bundle);
                ft.replace(R.id.framLayout_container, yuDingFragment);
                ft.commit();
            }
        });


        return v;
    }
}
