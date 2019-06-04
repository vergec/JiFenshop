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
import android.widget.TextView;
import android.widget.Toast;

import com.example.jifenshop.R;
import com.example.jifenshop.application.MyApplication;
import com.example.jifenshop.bean.ResBean;
import com.example.jifenshop.util.OkHttpUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

public class sj_login extends AppCompatActivity {

    private Button sbtn_denglu;
    private Button sbtn_zhuce;
    private EditText sphone;
    private EditText spassword;
    private TextView g_login;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String response = (String) msg.obj;
            Gson gson = new Gson();
            com.example.jifenshop.bean.ResBean rm = gson.fromJson(response, ResBean.class);
            if (rm.getStatus() == 1) {
                Log.i("bb",rm.toString());
                MyApplication myApplication = (MyApplication) getApplication();
                myApplication.setSname(rm.getSname());//设置全局变量userId sj_name.setText(sname);
                myApplication.setSphone(sphone.getText().toString());                myApplication.setSpassword(spassword.getText().toString());

                Toast.makeText(sj_login.this, "登录成功"+response, Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setClass(sj_login.this, sj_order.class);
                startActivity(intent);
                finish();
            } else
                Toast.makeText(sj_login.this, "账号或密码错误", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sj_login);
        sbtn_denglu = findViewById(R.id.sbtn_denglu);
        sbtn_zhuce = findViewById(R.id.sbtn_zhuce);
        sphone = findViewById(R.id.sphone);
        spassword = findViewById(R.id.spassword);
        g_login = findViewById(R.id.g_login);
        g_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sj_login.this, gl_login.class);
                startActivity(intent);
            }
        });
        sbtn_zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sj_login.this, sj_regist.class);
                startActivity(intent);
            }
        });
        sbtn_denglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Uname = sphone.getText().toString();
                String Password = spassword.getText().toString();
                if (Uname.equals("")) {
                    Toast.makeText(sj_login.this, "账号不能为空", Toast.LENGTH_LONG).show();
                    //Toast.makeText(MainActivity.this, Password, Toast.LENGTH_LONG).show();
                } else if (Uname.length() != 11) {
                    Toast.makeText(sj_login.this, "手机号应为11位", Toast.LENGTH_LONG).show();
                } else if (Password.equals("")) {
                    Toast.makeText(sj_login.this, "密码不能为空", Toast.LENGTH_LONG).show();
                } else {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("sphone", Uname);
                    map.put("spassword", Password);
                    OkHttpUtils.getInstance().postDataAsyn(APIUtil.HOST_NAME + "/store_login",map,new OkHttpUtils.MyNetCall(){
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
                ;
            }
        });
    }
}
