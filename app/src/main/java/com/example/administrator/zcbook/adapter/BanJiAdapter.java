package com.example.administrator.zcbook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.administrator.zcbook.R;
import com.example.administrator.zcbook.entity.BanJi;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/7/5.
 */
public class BanJiAdapter extends ArrayAdapter<BanJi> {

    private int resourceId;
    private List<BanJi> list;
    // 用来控制CheckBox的选中状况
    public static HashMap<Integer,Boolean> isSelected;

    public BanJiAdapter(Context context, int textViewResourceId,
                        List<BanJi> objects) {
        super(context,textViewResourceId, objects);
        resourceId = textViewResourceId;
        this.list=objects;
        isSelected = new HashMap<Integer, Boolean>();

        initDate();
    }

    // 初始化isSelected的数据
    private void initDate(){
        for(int i=0; i<list.size();i++) {
            getIsSelected().put(i,false);
        }
    }

    public static HashMap<Integer,Boolean> getIsSelected() {
        return isSelected;
    }
    public static void setIsSelected(HashMap<Integer,Boolean> isSelected) {
        BanJiAdapter.isSelected = isSelected;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BanJi banJi = getItem(position);
        View view;
        // 获得ViewHolder对象
        ViewHolder viewHolder;
        if (convertView == null) {
            // 导入布局并赋值给convertview
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.banJiName = (TextView) view.findViewById(R.id.textView_class);
            viewHolder.checkBox= (CheckBox) view.findViewById(R.id.checkBox_class);
            //为view设置标签
            view.setTag(viewHolder);
        } else {
            // 取出holder
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        //设置图片
        viewHolder.banJiName.setText(banJi.getBanJiName());
        // 根据isSelected来设置checkbox的选中状况
        viewHolder.checkBox.setChecked(getIsSelected().get(position));
        //Log.i("BanJiAdapter",);
        return view;
    }

   public class ViewHolder {
        // CheckBox checkBox;
        TextView banJiName;
        public CheckBox checkBox;
    }

}
