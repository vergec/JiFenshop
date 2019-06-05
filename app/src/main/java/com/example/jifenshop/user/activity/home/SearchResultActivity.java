package com.example.jifenshop.user.activity.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.jifenshop.R;
import com.example.jifenshop.activity.APIUtil;
import com.example.jifenshop.user.adapter.home.SearchResultProductAdapter;
import com.example.jifenshop.user.base.BaseActivity;
import com.example.jifenshop.user.entity.SearchResultVO;
import com.example.jifenshop.user.util.HttpUtils;
import com.example.jifenshop.user.util.JsonParser;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;

import static com.example.jifenshop.activity.APIUtil.ERROR;
import static com.example.jifenshop.activity.APIUtil.LOAD_ALL;
import static com.example.jifenshop.activity.APIUtil.TAG;

public class SearchResultActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.common_toolbar_left)
    ImageView back;
    @BindView(R.id.search_result_text)
    TextView queryWordTv;
    @BindView(R.id.search_bar)
    SearchView searchView;
    @BindView(R.id.search_result_rv)
    RecyclerView resultRV;
    private final int SEARCH_SUCCESSFUL = 3;
    private final int SEARCH_UNSUCCESSFUL = 4;
    Intent intent;
    String str;
    @Override
    public int getLayoutRes() {
        return R.layout.activity_search_result;
    }

    @Override
    protected void init() {
        intent = getIntent();
        str = intent.getStringExtra("gname");
        initData();
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case LOAD_ALL:
                    break;
                case SEARCH_SUCCESSFUL:
                    toast("搜索成功");
                    initPage((List<SearchResultVO>) msg.obj);
                    break;
                case SEARCH_UNSUCCESSFUL:
                    toast("搜索出错");
                    finish();
                    break;
                case ERROR:
                    toast("搜索失败");
                    finish();
                    break;
            }
        }
    };

    private void initPage(List<SearchResultVO> obj) {
        queryWordTv.setText(intent.getStringExtra("gname"));
        back.setOnClickListener(this);
        searchView.setQuery(intent.getStringExtra("gname"), false);
        searchView.clearFocus();
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
        initRecycleView(obj);
    }

    private void initRecycleView(List<SearchResultVO> obj) {
        SearchResultProductAdapter adapter = new SearchResultProductAdapter(R.layout.item_search_result,obj);
        resultRV.setLayoutManager(new LinearLayoutManager(this));
        resultRV.setAdapter(adapter);
    }

    private void onSearchStart(final String str) {
        this.str = str;
        initData();
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String json = HttpUtils.getStringFromServer(APIUtil.HOST_NAME + "user_findgoods?gname=" + str);
                    List<SearchResultVO> mData = JsonParser.getSearchResult(json);
                    Log.i(TAG, "run: " + mData.size());
                    if (mData != null)
                        mHandler.obtainMessage(SEARCH_SUCCESSFUL, mData).sendToTarget();
                    else
                        mHandler.obtainMessage(SEARCH_UNSUCCESSFUL).sendToTarget();
                } catch (IOException e) {
                    mHandler.obtainMessage(ERROR).sendToTarget();
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.common_toolbar_left:
                finish();
                break;
        }
    }
}
