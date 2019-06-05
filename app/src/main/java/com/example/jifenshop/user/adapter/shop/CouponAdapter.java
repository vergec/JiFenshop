package com.example.jifenshop.user.adapter.shop;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.jifenshop.R;
import com.example.jifenshop.activity.APIUtil;
import com.example.jifenshop.user.entity.CouponVO;
import com.example.jifenshop.user.util.HttpUtils;
import com.example.jifenshop.user.util.JsonParser;

import java.io.IOException;
import java.util.List;

import okhttp3.Response;

import static com.example.jifenshop.activity.APIUtil.ERROR;
import static com.example.jifenshop.activity.APIUtil.SUCCESS;
import static com.example.jifenshop.activity.APIUtil.UNSUCCESS;

public class CouponAdapter extends BaseQuickAdapter<CouponVO, BaseViewHolder> {

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SUCCESS:
                    Toast.makeText(mContext, "兑换成功", Toast.LENGTH_SHORT).show();
                    break;
                case UNSUCCESS:
                    Toast.makeText(mContext, "兑换失败", Toast.LENGTH_SHORT).show();
                    break;
                case ERROR:
                    Toast.makeText(mContext, "兑换失败", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    public CouponAdapter(int layoutResId, @Nullable List<CouponVO> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final CouponVO item) {
        helper.setText(R.id.coupon_name, item.getGname())
                .setText(R.id.coupon_price, item.getPrice().toString());

        final Button btn = helper.getView(R.id.coupon_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences userInfo = mContext.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                final String uphone = userInfo.getString("uphone", "1");
                final String gname = item.getGname();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Response json = HttpUtils.doGet(APIUtil.HOST_NAME + "user_buyfgoods?gname=" + gname + "&uphone=" + uphone);
                            String str = json.body().string();
                            Log.i(TAG, "run: " + str);
                            boolean mData = JsonParser.isExchangeGoods(str);
                            Log.i(TAG, "run: " + mData);
                            if (mData)
                                mHandler.obtainMessage(SUCCESS, true).sendToTarget();
                            else
                                mHandler.obtainMessage(UNSUCCESS, false).sendToTarget();
                        } catch (IOException e) {
                            mHandler.obtainMessage(ERROR).sendToTarget();
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }
}
