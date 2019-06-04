package com.example.jifenshop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jifenshop.R;
import com.example.jifenshop.bean.GoodsBean;
import com.example.jifenshop.util.OkHttpUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

public class sj_shanpin_xiugai_jiage extends AppCompatActivity {
    private EditText gname;
    private EditText stock;
    private EditText colorf;
    private EditText price;
    private TextView wancheng;
    private ImageView back;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String response = (String) msg.obj;
            Gson gson = new Gson();
            com.example.jifenshop.bean.GoodsBean rm = gson.fromJson(response, GoodsBean.class);
            if (rm.getStatus() == 1) {
                Intent intent = new Intent();
                intent.setClass(sj_shanpin_xiugai_jiage.this, sj_mygoods.class);
                startActivity(intent);
                Toast.makeText(sj_shanpin_xiugai_jiage.this, "修改成功", Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(sj_shanpin_xiugai_jiage.this, "修改失败", Toast.LENGTH_LONG).show();
            }
        }


    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sj_shanpin_xiugai_jiage);
        gname = findViewById(R.id.gname);
        stock = findViewById(R.id.stock);
        colorf = findViewById(R.id.colorf);
        back = findViewById(R.id.back);
        wancheng = findViewById(R.id.wancheng);
        price = findViewById(R.id.price);

        Bundle bundle = getIntent().getBundleExtra("Message");
        final String name = bundle.getString("goods_name");
        final String jiage = bundle.getString("goods_jiage");
        final String color = bundle.getString("goods_color");
        final String kucun = bundle.getString("goods_kucun");

        gname.setText(name.toString());
        stock.setText(kucun.toString());
        colorf.setText(color.toString());
        price.setText(jiage.toString());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(sj_shanpin_xiugai_jiage.this,sj_mygoods.class);
                startActivity(intent);
            }
        });
       wancheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> map = new HashMap<>();
                map.put("gname", gname.getText().toString());
                map.put("sellnum", stock.getText().toString());
                map.put("colorf", colorf.getText().toString());
                OkHttpUtils.getInstance().postDataAsyn(APIUtil.HOST_NAME + "/changegoods", map, new OkHttpUtils.MyNetCall() {
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
            };
        });
    }
}
