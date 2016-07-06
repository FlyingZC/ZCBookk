package com.example.administrator.zcbook.fragment;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.zcbook.R;
import com.example.administrator.zcbook.entity.BanJi;
import com.example.administrator.zcbook.util.HttpUtil;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/5.
 */
public class SubmitFragment extends Fragment {
    private  ArrayList<BanJi>  selectBanJis;
    private int bookId;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=View.inflate(getActivity(), R.layout.fragment_submit, null);
        EditText ev1= (EditText) v.findViewById(R.id.editText_bookNum1);
        EditText ev2= (EditText) v.findViewById(R.id.editText_bookNum2);
        EditText ev3= (EditText) v.findViewById(R.id.editText_bookNum3);
        EditText ev4= (EditText) v.findViewById(R.id.editText_bookNum4);
        EditText ev5= (EditText) v.findViewById(R.id.editText_bookNum5);
        EditText ev6= (EditText) v.findViewById(R.id.editText_bookNum6);
        EditText ev7= (EditText) v.findViewById(R.id.editText_bookNum7);

        View linear1 = v.findViewById(R.id.linear_submitfrag1);
        View linear2 = v.findViewById(R.id.linear_submitfrag2);
        View linear3 = v.findViewById(R.id.linear_submitfrag3);
        View linear4 = v.findViewById(R.id.linear_submitfrag4);
        View linear5 = v.findViewById(R.id.linear_submitfrag5);
        View linear6 = v.findViewById(R.id.linear_submitfrag6);
        View linear7 = v.findViewById(R.id.linear_submitfrag7);


        TextView tv1= (TextView) v.findViewById(R.id.textView_submitBan1);
        TextView tv2= (TextView) v.findViewById(R.id.textView_submitBan2);
        TextView tv3= (TextView) v.findViewById(R.id.textView_submitBan3);
        TextView tv4= (TextView) v.findViewById(R.id.textView_submitBan4);
        TextView tv5= (TextView) v.findViewById(R.id.textView_submitBan5);
        TextView tv6= (TextView) v.findViewById(R.id.textView_submitBan6);
        TextView tv7= (TextView) v.findViewById(R.id.textView_submitBan7);

        Button btnSubmit= (Button) v.findViewById(R.id.btn_banji_submit);

        ArrayList<View> linears=new ArrayList<>();
        linears.add(linear1);linears.add(linear2); linears.add(linear3);linears.add(linear4);
        linears.add(linear5);linears.add(linear6); linears.add(linear7);

        ArrayList<TextView> tvs=new ArrayList<>();
        tvs.add(tv1);tvs.add(tv2);tvs.add(tv3);tvs.add(tv4);tvs.add(tv5);tvs.add(tv6);tvs.add(tv7);

        final ArrayList<EditText> ets=new ArrayList<>();
        ets.add(ev1);ets.add(ev2);ets.add(ev3);ets.add(ev4);ets.add(ev5);ets.add(ev6);ets.add(ev7);

        Bundle bundle=getArguments();
        selectBanJis =(ArrayList<BanJi>)bundle.getSerializable("selectBanJis");
        bookId=bundle.getInt("bookId");
        Log.i("SubmitFragment","selectBanJis大小"+selectBanJis.size());
        Log.i("SubmitFragment","bookId"+bookId);
        for(int i=0;i<selectBanJis.size();i++){
            linears.get(i+1).setVisibility(View.VISIBLE);
            tvs.get(i+1).setText(selectBanJis.get(i).getBanJiName());
        }

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<selectBanJis.size();i++){
                    Log.i("SubmitFragment", "文本框数目数据" + ets.get(i + 1).getText().toString());
                    selectBanJis.get(i).setBookCount(Integer.parseInt(ets.get(i + 1).getText().toString().trim()));
                }
                new AddBookTask().execute();
            }
        });
        return v;
    }

    private class AddBookTask extends AsyncTask<Void,Void,String> {

        @Override
        protected String doInBackground(Void... params){
            // ArrayList<Book> books=null;
            try{
                for(int i=0;i<selectBanJis.size();i++){
                    String requestParam = "banJiId="+selectBanJis.get(i).getBanJiId()+"&bookCount="+selectBanJis.get(i).getBookCount()+"&method=addBook&bookId="+bookId;
                    Log.i("SubmitFragment", "发送请求" + requestParam);
                    String resultJson= HttpUtil.getRequest(requestParam);
                    Log.i("SubmitFragment","解析之前"+resultJson.toString());
                }

                //banJis=new JsonUtil().parseBanJi(resultJson);
               // Log.i("YuDingFragment","解析完成"+banJis.toString());
            }catch(Exception e){

            }
            return null;
        }

        @Override
        protected void onPostExecute(String flag) {
            super.onPostExecute(flag);
            //三个参数context,ListView每一项的布局文件,填充内容
        }
    }
}
