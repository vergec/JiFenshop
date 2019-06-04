package com.example.jifenshop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jifenshop.R;
import com.example.jifenshop.application.MyApplication;
import com.example.jifenshop.bean.DingdanBean;
import com.example.jifenshop.bean.NewdingdanBean;
import com.example.jifenshop.util.OkHttpUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;


public class sj_dingdan extends AppCompatActivity {
    private ImageView back;
    private ListView dingdan;
    private List<NewdingdanBean> list_goods;
    private BdapterDelivery bdapterDelivery;

    private Handler handler= new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String response = (String)msg.obj;
            Gson gson = new Gson();
            DingdanBean dingdanBeans = gson.fromJson(response,DingdanBean.class);
            Toast.makeText(sj_dingdan.this, response, Toast.LENGTH_LONG).show();
            list_goods.addAll(dingdanBeans.getData());
            bdapterDelivery.notifyDataSetChanged();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sj_dingdan);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(sj_dingdan.this,sj_order.class);
                startActivity(intent);
            }
        });
        dingdan = findViewById(R.id.dd);
        list_goods= new ArrayList<>();
        bdapterDelivery = new BdapterDelivery(this,list_goods);
        dingdan.setAdapter(bdapterDelivery);
        MyApplication myApplication = (MyApplication) getApplication();
        final String sphone = myApplication.getSphone();
        HashMap<String, String> map = new HashMap<>();
        map.put("sphone", sphone);
        OkHttpUtils.getInstance().postDataAsyn(APIUtil.HOST_NAME + "/getallorder",map,new OkHttpUtils.MyNetCall(){
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
