package com.example.jifenshop.user.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.example.jifenshop.R;
import com.example.jifenshop.user.activity.home.SearchResultActivity;
import com.example.jifenshop.user.base.BaseFragment;

import butterknife.BindView;

import static com.example.jifenshop.activity.APIUtil.TAG;

public class HomeFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.home_clothes)
    LinearLayout clothesLL;
    @BindView(R.id.home_shoes)
    LinearLayout shoesLL;
    @BindView(R.id.home_earphone)
    LinearLayout earphoneLL;
    @BindView(R.id.home_pants)
    LinearLayout pentsLL;
    @BindView(R.id.home_snacks)
    LinearLayout snacksLL;
    @BindView(R.id.home_watch)
    LinearLayout watchLL;
    @BindView(R.id.home_search)
    SearchView searchView;


    @Override
    protected void init(View view) {
        initView(view);
    }

    private void initView(View view) {
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.i(TAG, "onQueryTextSubmit: query start");
                onSearchStart(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        shoesLL.setOnClickListener(this);
        clothesLL.setOnClickListener(this);
        earphoneLL.setOnClickListener(this);
        pentsLL.setOnClickListener(this);
        watchLL.setOnClickListener(this);
        snacksLL.setOnClickListener(this);
    }

    private void onSearchStart(final String str) {
        Intent intent = new Intent(getActivity(), SearchResultActivity.class);
        intent.putExtra("gname", str);
        startActivity(intent);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_clothes:
                onSearchStart("衣物");
                break;
            case R.id.home_earphone:
                onSearchStart("耳机");
                break;
            case R.id.home_pants:
                onSearchStart("裤子");
                break;
            case R.id.home_shoes:
                onSearchStart("鞋子");
                break;
            case R.id.home_snacks:
                onSearchStart("零食");
                break;
            case R.id.home_watch:
                onSearchStart("手表");
                break;
        }
    }
}
