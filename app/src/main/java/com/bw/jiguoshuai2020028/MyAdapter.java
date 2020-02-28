package com.bw.jiguoshuai2020028;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    Context context;
    List<MyBean.ResultBean.MlssBean.CommodityListBeanXX> list;
    public MyAdapter(Context context, List<MyBean.ResultBean.MlssBean.CommodityListBeanXX> list) {
        this.context=context;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.item, null);
        MyBean.ResultBean.MlssBean.CommodityListBeanXX xx = list.get(position);

        TextView t1 = view.findViewById(R.id.t1);
        TextView t2 = view.findViewById(R.id.t2);
        t1.setText(xx.getCommodityName());
        t2.setText(xx.getPrice()+"");
        ImageView iv = view.findViewById(R.id.iv);
        Glide.with(context).load(xx.getMasterPic()).into(iv);
        return view;
    }
}
