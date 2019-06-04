package com.example.jifenshop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jifenshop.R;
import com.example.jifenshop.application.MyApplication;
import com.example.jifenshop.bean.DingdanBean;
import com.example.jifenshop.bean.GoodsBean;
import com.example.jifenshop.util.OkHttpUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

public class sj_order extends AppCompatActivity {
    private ImageView shangpin;
    private ImageView dingdan;
    private ImageView guanli;
    private TextView sj_name;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String response = (String) msg.obj;
            Gson gson = new Gson();
            com.example.jifenshop.bean.GoodsBean rm = gson.fromJson(response, GoodsBean.class);
            if (rm.getStatus() == 1) {
                Intent intent = new Intent();
                intent.setClass(sj_order.this, sj_mygoods.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(sj_order.this, "暂无商品", Toast.LENGTH_LONG).show();
            }
        }
    };
    private Handler handler2 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String response = (String) msg.obj;
            Gson gson = new Gson();
            DingdanBean bean = gson.fromJson(response,  DingdanBean.class);
            if (bean.getStatus() == 1) {
                Intent intent = new Intent();
                intent.setClass(sj_order.this, sj_dingdan.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(sj_order.this, "暂无订单", Toast.LENGTH_LONG).show();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sj_order);
        shangpin=findViewById(R.id.shangpin);
        dingdan=findViewById(R.id.dingdan);

        guanli=findViewById(R.id.guanli);
        sj_name=findViewById(R.id.sj_name);
        MyApplication myApplication = (MyApplication) getApplication();
        final String sphone = myApplication.getSphone();
        Log.i("aaa","SPHOENE " + (sphone==null));
        final String sname = myApplication.getSname();

        sj_name.setText(sname);

        dingdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> map = new HashMap<>();
                map.put("sphone", sphone);
                OkHttpUtils.getInstance().postDataAsyn(APIUtil.HOST_NAME + "/getallorder", map, new OkHttpUtils.MyNetCall() {
                    @Override
                    public void success(Call call, Response response) throws IOException {
                        Message m = new Message();
                        m.obj = response.body().string();
                        handler2.sendMessage(m);
                    }

                    @Override
                    public void failed(Call call, IOException e) {

                    }
                });
            }
        });

        guanli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(sj_order.this, sj_guanli.class);
                startActivity(intent);
            }
        });

        shangpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> map = new HashMap<>();
                map.put("sphone", sphone);
                OkHttpUtils.getInstance().postDataAsyn(APIUtil.HOST_NAME + "/allgoods", map, new OkHttpUtils.MyNetCall() {
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
        });
    }
}
