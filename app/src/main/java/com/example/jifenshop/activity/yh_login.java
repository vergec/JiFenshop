package com.example.jifenshop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jifenshop.R;
import com.example.jifenshop.bean.ResBean;
import com.example.jifenshop.util.OkHttpUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

public class yh_login extends AppCompatActivity {
    private Button btn_denglu;
    private Button btn_zhuce;
    private EditText uphone;
    private EditText upassword;
    private TextView s_login;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String response = (String) msg.obj;
            Gson gson = new Gson();
            com.example.jifenshop.bean.ResBean rm = gson.fromJson(response, ResBean.class);
            if (rm.getStatus() == 1) {
                Toast.makeText(yh_login.this, "登录成功", Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setClass(yh_login.this, zy_mainpage.class);
                startActivity(intent);
                finish();
            } else
                Toast.makeText(yh_login.this, "账号或密码错误", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yh_login);
        btn_denglu = findViewById(R.id.btn_denglu);
        btn_zhuce = findViewById(R.id.btn_zhuce);
        uphone = findViewById(R.id.uphone);
        s_login = findViewById(R.id.s_login);
        upassword = findViewById(R.id.upassword);

        btn_zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(yh_login.this, yh_regist.class);
                startActivity(intent);
            }
        });
        s_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(yh_login.this, sj_login.class);
                startActivity(intent);
            }
        });
        btn_denglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Uname = uphone.getText().toString();
                String Password = upassword.getText().toString();
                if (Uname.equals("")) {
                    Toast.makeText(yh_login.this, "账号不能为空", Toast.LENGTH_LONG).show();
                    //Toast.makeText(MainActivity.this, Password, Toast.LENGTH_LONG).show();
                } else if (Uname.length() != 11) {
                    Toast.makeText(yh_login.this, "手机号应为11位", Toast.LENGTH_LONG).show();
                } else if (Password.equals("")) {
                    Toast.makeText(yh_login.this, "密码不能为空", Toast.LENGTH_LONG).show();
                } else {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("uphone", Uname);
                    map.put("upassword", Password);
                    OkHttpUtils.getInstance().postDataAsyn(APIUtil.HOST_NAME + "/login",map,new OkHttpUtils.MyNetCall(){
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
            }
        });
    }
}