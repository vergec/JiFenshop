package com.example.jifenshop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jifenshop.R;
import com.example.jifenshop.bean.ResBean;
import com.example.jifenshop.util.OkHttpUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

public class sj_regist extends AppCompatActivity {
    private Button sbtn_zhuce;
    private EditText sphone;
    private EditText sname;
    private EditText spassword;
    private ImageView back;
    private Handler handler= new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String response = (String) msg.obj;
            ResBean bean = new Gson().fromJson(response,ResBean.class);
            Log.i(TAG,response);
            if (bean.getStatus()==1) {
                Toast.makeText(sj_regist.this, "注册成功", Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setClass(sj_regist.this,sj_login.class);
                startActivity(intent);
                finish();
            } else
                Toast.makeText(sj_regist.this, "用户名已存在", Toast.LENGTH_LONG).show();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sj_regist);
        sphone = findViewById(R.id.sphone);
        sname = findViewById(R.id.sname);
        spassword = findViewById(R.id.spassword);
        sbtn_zhuce = findViewById(R.id.sbtn_zhuce);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(sj_regist.this,sj_login.class);
                startActivity(intent);
            }
        });
        sbtn_zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Uname = sphone.getText().toString();
                String Password = spassword.getText().toString();
                if (Uname.equals("")) {
                    Toast.makeText(sj_regist.this, "账号不能为空", Toast.LENGTH_LONG).show();
                    //Toast.makeText(MainActivity.this, Password, Toast.LENGTH_LONG).show();
                } else if (Uname.length() != 11) {
                    Toast.makeText(sj_regist.this, "手机号应为11位", Toast.LENGTH_LONG).show();
                } else if (Password.equals("")) {
                    Toast.makeText(sj_regist.this, "密码不能为空", Toast.LENGTH_LONG).show();
                } else {
                HashMap<String, String> map = new HashMap<>();
                map.put("sphone",sphone.getText().toString());
                map.put("sname",sname.getText().toString());
                map.put("spassword",spassword.getText().toString());
                OkHttpUtils.getInstance().postDataAsyn(APIUtil.HOST_NAME + "/store_registered",map,new OkHttpUtils.MyNetCall(){
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
        };
    });
    }
}
