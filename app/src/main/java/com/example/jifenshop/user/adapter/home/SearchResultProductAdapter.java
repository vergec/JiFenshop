package com.example.jifenshop.user.adapter.home;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.jifenshop.R;
import com.example.jifenshop.user.entity.SearchResultVO;

import java.util.List;

public class SearchResultProductAdapter extends BaseQuickAdapter<SearchResultVO, BaseViewHolder> {
    public SearchResultProductAdapter(int layoutResId, @Nullable List<SearchResultVO> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchResultVO item) {
        helper.setText(R.id.item_search_result_name,item.getGname())
                .setText(R.id.item_search_result_price,String.format("￥%s",item.getPrice().toString()));
    }
}
