package com.example.jifenshop.user.adapter.shop;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.jifenshop.R;
import com.example.jifenshop.user.entity.SimplePointsProductInfoVO;

import java.util.List;

import static com.example.jifenshop.activity.APIUtil.ERROR;
import static com.example.jifenshop.activity.APIUtil.SUCCESS;
import static com.example.jifenshop.activity.APIUtil.UNSUCCESS;

public class SimpleShopPointGoodsAdapter extends BaseQuickAdapter<SimplePointsProductInfoVO, BaseViewHolder> {
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case SUCCESS:
                    Toast.makeText(mContext,"兑换成功",Toast.LENGTH_SHORT).show();
                    break;
                case UNSUCCESS:
                    Toast.makeText(mContext,"兑换失败",Toast.LENGTH_SHORT).show();
                    break;
                case ERROR:
                    Toast.makeText(mContext,"兑换失败",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    public SimpleShopPointGoodsAdapter(int layoutResId, @Nullable List<SimplePointsProductInfoVO> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final SimplePointsProductInfoVO item) {
        helper.setText(R.id.shop_goods_name, item.getGname())
                .setText(R.id.shop_goods_price, item.getPrice().toString())
                .setText(R.id.shop_goods_total_person, String.format("%s人已经购买", item.getSellnum()));
        Log.i(TAG, "convert: ");
        final Button btn = helper.getView(R.id.shop_goods_buy_btn);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPreferences userInfo = mContext.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
//                final String uphone = userInfo.getString("uphone", "1");
//                final String gname = item.getGname();
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            Response json = HttpUtils.doGet(APIUtil.HOST_NAME + "user_buyfgoods?gname=" + gname + "&uphone=" + uphone);
//                            String str = json.body().string();
//                            Log.i(TAG, "run: "+str);
//                            boolean mData = JsonParser.isExchangeGoods(str);
//                            Log.i(TAG, "run: " + mData);
//                            if (mData)
//                                mHandler.obtainMessage(SUCCESS, true).sendToTarget();
//                            else
//                                mHandler.obtainMessage(UNSUCCESS, false).sendToTarget();
//                        } catch (IOException e) {
//                            mHandler.obtainMessage(ERROR).sendToTarget();
//                            e.printStackTrace();
//                        }
//                    }
//                }).start();
//            }
//        });
    }
}
