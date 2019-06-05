package com.example.jifenshop.user;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MenuItem;

import com.example.jifenshop.R;
import com.example.jifenshop.activity.APIUtil;
import com.example.jifenshop.user.base.BaseActivity;
import com.example.jifenshop.user.fragment.HomeFragment;
import com.example.jifenshop.user.fragment.ShopFragment;
import com.example.jifenshop.user.fragment.MineFragment;

public class UserMainActivity extends BaseActivity {
    private Fragment mLastFragment;
    Fragment[] fragments = new Fragment[3];

    @Override
    public int getLayoutRes() {
        return R.layout.activity_user_main;
    }

    @Override
    protected void init() {
        initFragment();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void initFragment() {
        fragments[0] = new HomeFragment();
        fragments[1] = new ShopFragment();
        fragments[2] = new MineFragment();
        setFragment(fragments[0]);
    }

    private void setFragment(Fragment fragment) {
        String tag = fragment.getClass().getSimpleName();
        Fragment result = getSupportFragmentManager().findFragmentByTag(tag);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (mLastFragment != null) {
            transaction.hide(mLastFragment);
        }
        if (result == null) {
            transaction.add(R.id.main, fragment, tag).commit();
        } else {
            transaction.show(result).commit();
        }
        mLastFragment = fragment;
    }

    /**
     * 底部导航的监听事件
     */
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.tabbar_home:
                    Log.i(APIUtil.TAG, "onNavigationItemSelected: home");
                    setFragment(fragments[0]);
                    break;
                case R.id.tabbar_shop:
                    Log.i(APIUtil.TAG, "onNavigationItemSelected: shop");
                    setFragment(fragments[1]);
                    break;
                case R.id.tabbar_mine:
                    Log.i(APIUtil.TAG, "onNavigationItemSelected: mine");
                    setFragment(fragments[2]);
                    break;
            }
            return true;
        }
    };
}
