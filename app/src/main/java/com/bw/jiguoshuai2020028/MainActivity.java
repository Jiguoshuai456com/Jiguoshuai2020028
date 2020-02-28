package com.bw.jiguoshuai2020028;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private XBanner xb;
    private ListView lv;
    private GridView gv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xb = findViewById(R.id.xb);
        lv = findViewById(R.id.lv);
        gv = findViewById(R.id.gv);
        String url="http://mobile.bwstudent.com/small/commodity/v1/bannerShow";
        VollerUtiuls.getInstance().doGet(url, new VollerUtiuls.ICallback() {
            @Override
            public void onSuccess(String json) {
                Log.i("xxx",json);
                Gson gson = new Gson();
                Bean bean = gson.fromJson(json, Bean.class);
                final List<Bean.ResultBean> result = bean.getResult();
                xb.setBannerData(result);
                xb.loadImage(new XBanner.XBannerAdapter() {
                    @Override
                    public void loadBanner(XBanner banner, Object model, View view, int position) {
                        String imageUrl = result.get(position).getImageUrl();
                        Glide.with(MainActivity.this).load(imageUrl).into((ImageView) view);

                    }
                });
            }

            @Override
            public void onError(String json) {
                Log.i("xxx",json);
            }
        });
        String path="http://mobile.bwstudent.com/small/commodity/v1/commodityList";
        VollerUtiuls.getInstance().doGet(path, new VollerUtiuls.ICallback() {
            @Override
            public void onSuccess(String json) {
                Log.i("xxx",json);
                Gson gson = new Gson();
                MyBean myBean = gson.fromJson(json, MyBean.class);
                MyBean.ResultBean result = myBean.getResult();
                MyBean.ResultBean.MlssBean mlss = result.getMlss();
                List<MyBean.ResultBean.MlssBean.CommodityListBeanXX> list = mlss.getCommodityList();
                MyAdapter adapter = new MyAdapter(MainActivity.this, list);
                lv.setAdapter(adapter);
                MyBean.ResultBean.PzshBean pzsh = result.getPzsh();
                List<MyBean.ResultBean.PzshBean.CommodityListBeanX> list1 = pzsh.getCommodityList();
                MyAdapter2 adapter2 = new MyAdapter2(MainActivity.this, list1);
                gv.setAdapter(adapter2);

            }

            @Override
            public void onError(String json) {

            }
        });
    }
}
