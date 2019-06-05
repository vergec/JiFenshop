package com.example.jifenshop.user.adapter.mine;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.jifenshop.R;
import com.example.jifenshop.user.entity.UserCouponVO;

import java.util.List;

public class UserCouponAdapter extends BaseQuickAdapter<UserCouponVO, BaseViewHolder> {
    public UserCouponAdapter(int layoutResId, @Nullable List<UserCouponVO> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserCouponVO item) {
        helper.setText(R.id.coupon_name, item.getGname())
                .setText(R.id.coupon_price, String.valueOf(item.getPrice()))
                .setText(R.id.coupon_num,String.format("×%s", item.getUgnumber()));
    }
}
