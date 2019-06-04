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

public class sj_guanli extends AppCompatActivity {
    private EditText password,sname2;
    private TextView sname1;
    private Button change;
    private ImageView back;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String response = (String) msg.obj;
            Gson gson = new Gson();
            com.example.jifenshop.bean.ResBean rm = gson.fromJson(response, ResBean.class);
            if (rm.getStatus() == 1) {

                MyApplication myApplication = (MyApplication) getApplication();
                myApplication.setSname(sname2.getText().toString());
                myApplication.setSpassword(password.getText().toString());

                Toast.makeText(sj_guanli.this, "修改成功", Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setClass(sj_guanli.this, sj_order.class);
                startActivity(intent);
                finish();
            } else
                Toast.makeText(sj_guanli.this, "修改失败", Toast.LENGTH_LONG).show();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sj_guanli);

        MyApplication myApplication = (MyApplication) getApplication();
        final String spassword = myApplication.getSpassword();
        final String sphone = myApplication.getSphone();
        final String sname = myApplication.getSname();

        password = findViewById(R.id.password);
        back = findViewById(R.id.back);
        sname1 = findViewById(R.id.sname1);
        sname2 = findViewById(R.id.sname2);
        change = findViewById(R.id.change);

        sname1.setText(sname);
        sname2.setText(sname);
        password.setText(spassword);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(sj_guanli.this,sj_mygoods.class);
                startActivity(intent);
            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> map = new HashMap<>();
                Log.i("sphone2222",sphone);
                Log.i("sname",sname1.getText().toString());
                Log.i("password",password.getText().toString());
                map.put("sphone",sphone);
                map.put("sname", sname2.getText().toString());
                map.put("spassword", password.getText().toString());
                OkHttpUtils.getInstance().postDataAsyn(APIUtil.HOST_NAME + "/schange",map,new OkHttpUtils.MyNetCall(){
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
