package com.example.jifenshop.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jifenshop.R;
import com.example.jifenshop.bean.ResBean;
import com.example.jifenshop.user.UserMainActivity;
import com.example.jifenshop.util.OkHttpUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

public class yh_regist extends Activity {
    private Button btn_zhuce2;
    private EditText uphone;
    private EditText uname;
    private EditText usex;
    private EditText uage;
    private EditText ucard;
    private EditText upassword;
    private ImageView back;
    @SuppressLint("HandlerLeak")
    private Handler handler= new Handler(){
        @Override
        public void handleMessage(Message msg) {
           super.handleMessage(msg);
            String response = (String) msg.obj;
            ResBean bean = new Gson().fromJson(response,ResBean.class);
            Log.i(TAG,response);
            if (bean.getStatus()==1) {
                Toast.makeText(yh_regist.this, "注册成功", Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setClass(yh_regist.this,UserMainActivity.class);
                startActivity(intent);
                finish();
            } else
                Toast.makeText(yh_regist.this, "用户名已存在", Toast.LENGTH_LONG).show();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yh_regist);
        uphone = findViewById(R.id.uphone2);
        uname = findViewById(R.id.uname);
        usex = findViewById(R.id.usex);
        uage = findViewById(R.id.uage);
        ucard = findViewById(R.id.ucard);
        upassword = findViewById(R.id.upassword2);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(yh_regist.this, UserMainActivity.class);
                startActivity(intent);
            }
        });
        btn_zhuce2 = findViewById(R.id.btn_zhuce2);
        btn_zhuce2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> map = new HashMap<>();
                map.put("uphone",uphone.getText().toString());
                map.put("uname",uname.getText().toString());
                map.put("sex",usex.getText().toString());
                map.put("age",uage.getText().toString());
                map.put("card",ucard.getText().toString());
                map.put("upassword",upassword.getText().toString());
                OkHttpUtils.getInstance().postDataAsyn(APIUtil.HOST_NAME + "/registered",map,new OkHttpUtils.MyNetCall(){
                    @Override
                    public void success(Call call, Response response) throws IOException {
                        Log.i(APIUtil.TAG,"send request");
                        Message m = new Message();
                        m.obj = response.body().string();
                        handler.sendMessage(m);
                    }

                    @Override
                    public void failed(Call call, IOException e) {
                        Log.i(APIUtil.TAG,"send request fail");
                        e.printStackTrace();
                    }
                });
            }
        });
    }
}
