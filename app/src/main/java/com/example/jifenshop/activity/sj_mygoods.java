package com.example.jifenshop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jifenshop.R;
import com.example.jifenshop.application.MyApplication;
import com.example.jifenshop.bean.GoodsBean;
import com.example.jifenshop.bean.NewgoodsBean;
import com.example.jifenshop.util.OkHttpUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;
public class sj_mygoods extends AppCompatActivity {
    private TextView addgoods,addjifengoods;
    private ImageView back;
    private ListView sjwp;
    private List<NewgoodsBean> list_goods;
    private AdapterDelivery adapterDelivery;

    private Handler handler= new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String response = (String)msg.obj;
            Gson gson = new Gson();
            GoodsBean goodsBeans = gson.fromJson(response,GoodsBean.class);
            Toast.makeText(sj_mygoods.this, response, Toast.LENGTH_LONG).show();
            list_goods.addAll(goodsBeans.getData());
            adapterDelivery.notifyDataSetChanged();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sj_mygoods);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(sj_mygoods.this,sj_order.class);
                startActivity(intent);
            }
        });

        addgoods = findViewById(R.id.addgoods);
        addgoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(sj_mygoods.this,sj_addgoods.class);
                startActivity(intent);
                }
      });
        addjifengoods = findViewById(R.id.addjifengoods);
        addjifengoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(sj_mygoods.this,sj_addjifengoods.class);
                startActivity(intent);
            }
        });
        sjwp = findViewById(R.id.sp);
        list_goods= new ArrayList<>();
        adapterDelivery = new AdapterDelivery(this,list_goods);
        sjwp.setAdapter(adapterDelivery);
        MyApplication myApplication = (MyApplication) getApplication();
        final String sphone = myApplication.getSphone();
            HashMap<String, String> map = new HashMap<>();
            map.put("sphone", sphone);
            OkHttpUtils.getInstance().postDataAsyn(APIUtil.HOST_NAME + "/allgoods",map,new OkHttpUtils.MyNetCall(){
                @Override
                public void success(Call call, Response response) throws IOException {
                    Message m = new Message();
                    m.obj = response.body().string();
                    handler.sendMessage(m);
                }
                @Override
                public void failed(Call call, IOException e) {

                }
          });
    }
}
