package com.example.jifenshop.user.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.jifenshop.R;
import com.example.jifenshop.activity.APIUtil;
import com.example.jifenshop.user.activity.mine.UserCouponActivity;
import com.example.jifenshop.user.base.BaseFragment;
import com.example.jifenshop.user.entity.UserSimpleInfoVO;
import com.example.jifenshop.user.util.HttpUtils;
import com.example.jifenshop.user.util.JsonParser;

import java.io.IOException;
import java.util.Objects;

import butterknife.BindView;

import static com.example.jifenshop.activity.APIUtil.ERROR;
import static com.example.jifenshop.activity.APIUtil.LOAD_ALL;
import static com.example.jifenshop.activity.APIUtil.TAG;

public class MineFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.user_name)
    TextView nameTv;
    @BindView(R.id.user_phone)
    TextView phoneTv;
    @BindView(R.id.user_coupon_num)
    TextView couponNumTv;
    @BindView(R.id.user_integral_num)
    TextView integralNumTv;
    @BindView(R.id.user_not_paying_order)
    LinearLayoutCompat payingLL;
    @BindView(R.id.user_wait_receive_order)
    LinearLayoutCompat receiveLL;
    @BindView(R.id.user_wait_send_order)
    LinearLayoutCompat sendLL;
    @BindView(R.id.user_wait_evaluation_order)
    LinearLayoutCompat evaluationLL;
    @BindView(R.id.user_coupon_ll)
    LinearLayoutCompat coupon_ll;
    @Override
    protected void init(View view) {
        initData();
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case LOAD_ALL:
                    initPage((UserSimpleInfoVO) msg.obj);
                    break;
                case ERROR:
                    toast("获取用户信息出错");
                    break;
            }
        }
    };

    private void initPage(UserSimpleInfoVO obj) {
        nameTv.setText(obj.getUname());
        phoneTv.setText(obj.getUphone());
        couponNumTv.setText(String.valueOf(obj.getFnum().intValue()));
        integralNumTv.setText(String.valueOf(obj.getPoint().intValue()));
        coupon_ll.setOnClickListener(this);
        payingLL.setOnClickListener(this);
        sendLL.setOnClickListener(this);
        receiveLL.setOnClickListener(this);
        evaluationLL.setOnClickListener(this);
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SharedPreferences userInfo = Objects.requireNonNull(getActivity()).getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                    String uphone = userInfo.getString("uphone", "1");
                    String json = HttpUtils.getStringFromServer(APIUtil.HOST_NAME + "user_self?uphone=" + uphone);
                    UserSimpleInfoVO mData = JsonParser.getSimpleUserInfo(json);
                    Log.i(TAG, "run: " + mData);
                    mHandler.obtainMessage(LOAD_ALL, mData).sendToTarget();
                } catch (IOException e) {
                    mHandler.obtainMessage(ERROR).sendToTarget();
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_mine;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_coupon_ll:
                startActivity(UserCouponActivity.class);
                break;
            case R.id.user_not_paying_order:
                break;
            case R.id.user_wait_evaluation_order:
                break;
            case R.id.user_wait_receive_order:
                break;
            case R.id.user_wait_send_order:
                break;
        }
    }
}
