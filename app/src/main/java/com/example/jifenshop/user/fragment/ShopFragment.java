package com.example.jifenshop.user.fragment;

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
import android.widget.TextView;

import com.example.jifenshop.R;
import com.example.jifenshop.activity.APIUtil;
import com.example.jifenshop.user.activity.shop.TicketActivity;
import com.example.jifenshop.user.adapter.shop.SimpleShopPointGoodsAdapter;
import com.example.jifenshop.user.base.BaseFragment;
import com.example.jifenshop.user.entity.PointsShopVO;
import com.example.jifenshop.user.entity.SimplePointsProductInfoVO;
import com.example.jifenshop.user.util.HttpUtils;
import com.example.jifenshop.user.util.JsonParser;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;

import static com.example.jifenshop.activity.APIUtil.ERROR;
import static com.example.jifenshop.activity.APIUtil.LOAD_ALL;
import static com.example.jifenshop.activity.APIUtil.TAG;

public class ShopFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.shop_point_goods_rv)
    RecyclerView productRv;
    @BindView(R.id.points_ticket)
    ImageView ticket;
    @BindView(R.id.points_num)
    TextView pointNumTv;

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
                    initPage((PointsShopVO) msg.obj);
                    break;
                case ERROR:
                    toast("获取用户信息出错");
                    break;
            }
        }
    };

    private void initPage(PointsShopVO obj) {
        Log.i(TAG, "initShopFragmentPage: " + obj.getPoint().toString());
        pointNumTv.setText(String.valueOf(obj.getPoint().intValue()));
        initRecycleView(obj.getData());
        ticket.setOnClickListener(this);
    }

    private void initRecycleView(List<SimplePointsProductInfoVO> data) {
        SimpleShopPointGoodsAdapter adapter = new SimpleShopPointGoodsAdapter(R.layout.item_shop_goods, data);
        productRv.setLayoutManager(new LinearLayoutManager(getContext()));
        productRv.setNestedScrollingEnabled(false);
        productRv.setAdapter(adapter);
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SharedPreferences userInfo = Objects.requireNonNull(getActivity()).getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                    String uphone = userInfo.getString("uphone","1");
                    String json = HttpUtils.getStringFromServer(APIUtil.HOST_NAME + "user_getgoods_sw?uphone="+uphone);
                    PointsShopVO mData = JsonParser.getIntegrationShopInfo(json);
                    Log.i(TAG, "run: " + mData.getData().size());
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
        return R.layout.fragment_shop;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shop_goods_buy_btn:
                break;
            case R.id.points_ticket:
                startActivity(TicketActivity.class);
                break;
        }
    }
}
