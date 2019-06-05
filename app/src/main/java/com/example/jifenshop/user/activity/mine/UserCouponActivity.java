package com.example.jifenshop.user.activity.mine;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.jifenshop.R;
import com.example.jifenshop.activity.APIUtil;
import com.example.jifenshop.user.adapter.mine.UserCouponAdapter;
import com.example.jifenshop.user.base.BaseActivity;
import com.example.jifenshop.user.entity.UserCouponVO;
import com.example.jifenshop.user.util.HttpUtils;
import com.example.jifenshop.user.util.JsonParser;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;

import static com.example.jifenshop.activity.APIUtil.ERROR;
import static com.example.jifenshop.activity.APIUtil.LOAD_ALL;
import static com.example.jifenshop.activity.APIUtil.TAG;

public class UserCouponActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.ticket_rv)
    RecyclerView ticketRv;
    @BindView(R.id.common_toolbar_left)
    ImageView back;
    @Override
    public int getLayoutRes() {
        return R.layout.activity_ticket;
    }
    @Override
    protected void init() {
        initData();
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case LOAD_ALL:
                    initPage((List<UserCouponVO>) msg.obj);
                    break;
                case ERROR:
                    toast("获取用户信息出错");
                    break;
            }
        }
    };

    private void initPage(List<UserCouponVO> obj) {
        initRecycleView(obj);
    }

    private void initRecycleView(List<UserCouponVO> data) {
        UserCouponAdapter adapter = new UserCouponAdapter(R.layout.item_user_coupon, data);
        ticketRv.setLayoutManager(new LinearLayoutManager(this));
        ticketRv.setAdapter(adapter);
        back.setOnClickListener(this);
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SharedPreferences userInfo = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                    String uphone = userInfo.getString("uphone","1");
                    String json = HttpUtils.getStringFromServer(APIUtil.HOST_NAME + "user_cxk?uphone="+uphone);
                    List<UserCouponVO> mData = JsonParser.getUserCoupon(json);
                    Log.i(TAG, "run: " + mData.size());
                    mHandler.obtainMessage(LOAD_ALL, mData).sendToTarget();
                } catch (IOException e) {
                    mHandler.obtainMessage(ERROR).sendToTarget();
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.common_toolbar_left:
                finish();
                break;
        }
    }
}
