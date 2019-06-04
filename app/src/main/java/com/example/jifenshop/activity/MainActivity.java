package com.example.jifenshop.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.widget.RadioGroup;

import com.example.jifenshop.R;
import com.example.jifenshop.fragment.HomeFragment;
import com.example.jifenshop.fragment.MineFragment;
import com.example.jifenshop.fragment.ShopFragment;

public class MainActivity extends FragmentActivity {

    private RadioGroup rg_main;
    private Fragment shopFragment;
    private Fragment homeFragment;
    private Fragment mineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.endbottom);
        initView();
        initData();
        initListener();

        int id = getIntent().getIntExtra("id",0);
        if( id == 1) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_main,new MineFragment())
                    .addToBackStack(null)
                    .commit();
        }
    }

    private void initListener(){
        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Fragment fragment = null;
                switch (checkedId){
                    case R.id.home:
                        fragment = homeFragment;
                        break;
                    case R.id.shop:
                        fragment = shopFragment;
                        break;
                    case R.id.mine:
                        fragment = mineFragment;
                        break;
                    default:
                        break;
                }
                switchFragment(fragment);
            }
        });
        //默认选择home界面
        rg_main.check(R.id.rbhome);
    }

    private void switchFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fl_main,fragment).commit();
    }

    private void initData(){
        shopFragment = new ShopFragment();
        homeFragment = new HomeFragment();
        mineFragment = new MineFragment();
    }

    private void initView(){
        rg_main = (RadioGroup)findViewById(R.id.rg_main);
    }
}
