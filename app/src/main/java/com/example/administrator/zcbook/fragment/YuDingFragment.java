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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import com.example.administrator.zcbook.R;
import com.example.administrator.zcbook.adapter.BanJiAdapter;
import com.example.administrator.zcbook.entity.BanJi;
import com.example.administrator.zcbook.entity.Book;
import com.example.administrator.zcbook.util.HttpUtil;
import com.example.administrator.zcbook.util.JsonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/5.
 */
public class YuDingFragment extends Fragment {
    private ArrayList<BanJi> banJis;
    private ListView banJiListView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //执行
        new BanJiTask().execute();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=View.inflate(getActivity(), R.layout.fragment_yuding, null);
        CheckBox checkBox=new CheckBox(getActivity());
        banJiListView= (ListView)v.findViewById(R.id.listView_class);
        Button btnSubmit= (Button)v.findViewById(R.id.btn_banji_submit);
        Button btnReset= (Button)v.findViewById(R.id.btn_banji_reset);
        Bundle bundle=getArguments();
        final Book currentBook =(Book)bundle.getSerializable("book");
        banJiListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 取得ViewHolder对象，这样就省去了通过层层的findViewById去实例化我们需要的cb实例的步骤

                BanJiAdapter.ViewHolder holder = (BanJiAdapter.ViewHolder) view.getTag();

                // 改变CheckBox的状态
                holder.checkBox.toggle();
                // 将CheckBox的选中状况记录下来
                BanJiAdapter.getIsSelected().put(position, holder.checkBox.isChecked());
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<Integer, Boolean> selects = BanJiAdapter.getIsSelected();
                Iterator<Map.Entry<Integer, Boolean>> it = selects.entrySet().iterator();
                //要发送的数据
                ArrayList<BanJi> selectBanJis=new ArrayList<BanJi>();
                while (it.hasNext()) {
                    Map.Entry<Integer, Boolean> entry = it.next();
                    if(entry.getValue()==true){
                        Log.i("YuDingFragment", "key" + entry.getKey().toString());
                        Log.i("YuDingFragment", "banJis大小" + banJis.size());
                        BanJi selectBanJi=new BanJi();
                        selectBanJi.setBanJiId(banJis.get(entry.getKey().intValue()).getBanJiId());
                        selectBanJi.setBanJiName(banJis.get(entry.getKey().intValue()).getBanJiName());
                        selectBanJis.add(selectBanJi);
                    }
                }
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                SubmitFragment submitFragment = new SubmitFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("selectBanJis", selectBanJis);
                bundle.putInt("bookId",currentBook.getBookId());
                submitFragment.setArguments(bundle);
                ft.replace(R.id.framLayout_container, submitFragment);
                ft.commit();
            }
        });
        return v;
    }

    private class BanJiTask extends AsyncTask<Void,Void,ArrayList<BanJi>> {

        @Override
        protected ArrayList<BanJi> doInBackground(Void... params){
            // ArrayList<Book> books=null;
            try{
                String requestParam="method=findBanJi";
                Log.i("YuDingFragment", "发送请求" + requestParam);
                String resultJson= HttpUtil.getRequest(requestParam);
                Log.i("YuDingFragment","解析之前"+resultJson.toString());
                banJis=new JsonUtil().parseBanJi(resultJson);
                Log.i("YuDingFragment","解析完成"+banJis.toString());
            }catch(Exception e){

            }
            return banJis;
        }

        @Override
        protected void onPostExecute(ArrayList<BanJi> banJis) {
            super.onPostExecute(banJis);
            //三个参数context,ListView每一项的布局文件,填充内容
           BanJiAdapter adapter = new BanJiAdapter(getActivity(), R.layout.item_class, banJis);
            banJiListView.setAdapter(adapter);
        }
    }

}
