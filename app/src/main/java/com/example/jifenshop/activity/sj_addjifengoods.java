package com.example.jifenshop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jifenshop.R;
import com.example.jifenshop.application.MyApplication;
import com.example.jifenshop.bean.addGoodsBean;
import com.example.jifenshop.util.OkHttpUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

public class sj_addjifengoods extends AppCompatActivity {
    private EditText gname,stock,price,colorf,colort,colors,sizef,sizet,sizes;
    private TextView shangjia;
    private ImageView back;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String response = (String) msg.obj;
            Gson gson = new Gson();
            Log.i(TAG,response);
            addGoodsBean bean = new Gson().fromJson(response,addGoodsBean.class);
            if (bean.getStatus() == 1) {
                Intent intent = new Intent();
                Toast.makeText(sj_addjifengoods.this, "添加成功", Toast.LENGTH_LONG).show();
                intent.setClass(sj_addjifengoods.this, sj_mygoods.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(sj_addjifengoods.this, "添加失败", Toast.LENGTH_LONG).show();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sj_addjiagegoods);
        stock = findViewById(R.id.stock);
        sizef = findViewById(R.id.sizef);
        sizes = findViewById(R.id.sizes);
        sizet = findViewById(R.id.sizet);
        gname = findViewById(R.id.gname);
        colorf = findViewById(R.id.colorf);
        colort = findViewById(R.id.colort);
        colors= findViewById(R.id.colors);
        price = findViewById(R.id.price);
        shangjia= findViewById(R.id.shangjia);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(sj_addjifengoods.this,sj_mygoods.class);
                startActivity(intent);
            }
        });

        MyApplication myApplication = (MyApplication) getApplication();
        final String sphone = myApplication.getSphone();

        shangjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> map = new HashMap<>();
                map.put("gname", gname.getText().toString());
                map.put("stock", stock.getText().toString());
                map.put("colorf", colorf.getText().toString());
                map.put("colort", colort.getText().toString());
                map.put("colors", colors.getText().toString());
                map.put("sizef", sizef.getText().toString());
                map.put("sizes", sizes.getText().toString());
                map.put("sizet", sizet.getText().toString());
                map.put("price", price.getText().toString());
                map.put("gkind", "f");
                map.put("picture", "picture");
                map.put("sphone", sphone);

                OkHttpUtils.getInstance().postDataAsyn(APIUtil.HOST_NAME + "/addgoods", map, new OkHttpUtils.MyNetCall() {
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