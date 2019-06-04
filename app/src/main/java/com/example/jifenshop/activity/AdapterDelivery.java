package com.example.jifenshop.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jifenshop.R;
import com.example.jifenshop.bean.NewgoodsBean;
import com.example.jifenshop.bean.addGoodsBean;
import com.example.jifenshop.util.OkHttpUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class AdapterDelivery extends BaseAdapter {
    private List<NewgoodsBean> deliveryRecordList;
    private LayoutInflater layoutInflater;
    private Context context;
    private TextView name,bianhao,jiage,yanse,kucun,neirong_name;
    private Button delete;
    private ListView goodschange;

    private Handler handler= new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String response = (String)msg.obj;
            Gson gson = new Gson();
            addGoodsBean bean = new Gson().fromJson(response,addGoodsBean.class);
            if(bean.getStatus()==1) {
                Toast.makeText(context, "下架成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(context,sj_mygoods.class);
                context.startActivity(intent);
            }
            else{
                Toast.makeText(context,"下架失败",Toast.LENGTH_SHORT).show();
            }
        }
    };
    public AdapterDelivery(Context context, List<NewgoodsBean> deliveryRecords){
        layoutInflater = LayoutInflater.from(context);
        this.deliveryRecordList = deliveryRecords;
        this.context = context;
    }
    @Override
    public int getCount() {
        return deliveryRecordList.size();
    }

    @Override
    public Object getItem(int position) {
        return deliveryRecordList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.sj_shanpin_neirong, null);
        final NewgoodsBean newgoodsBean= deliveryRecordList.get(position);
        name = view.findViewById(R.id.goods_name);
        bianhao = view.findViewById(R.id.goods_gid);
        jiage = view.findViewById(R.id.goods_jiage);
        yanse = view.findViewById(R.id.goods_color);
        kucun = view.findViewById(R.id.goods_kucun);
        neirong_name = view.findViewById(R.id.neirong_name);

        name.setText(newgoodsBean.getGname().toString());
        bianhao.setText(String.valueOf(newgoodsBean.getGid()));
        yanse.setText(newgoodsBean.getColorf());
        kucun.setText(String.valueOf(newgoodsBean.getStock()));
        jiage.setText(String.valueOf(newgoodsBean.getPrice()));
        delete = view.findViewById(R.id.delete);

        Log.i("aaa",newgoodsBean.getGkind());
        if(newgoodsBean.getGkind().equals("f")){
            neirong_name.setText("积分:");
        }
        else if(newgoodsBean.getGkind().equals("sw"))
        {
            neirong_name.setText("价格:");
        }


//        goodschange = view.findViewById(R.id.goodschange);
//        goodschange.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, sj_shanpin_xiugai_jiage.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("goods_name",String.valueOf(name));
//                bundle.putString("goods_jiage",String.valueOf(jiage));
//                bundle.putString("goods_color",String.valueOf(yanse));
//                bundle.putString("goods_kucun",String.valueOf(kucun));
//                intent.putExtra("Message", bundle);
//                context.startActivity(intent);
//            }
//        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> map = new HashMap<>();
                map.put("gid",String.valueOf(newgoodsBean.getGid()));
                OkHttpUtils.getInstance().postDataAsyn(APIUtil.HOST_NAME + "/deletegoods",map,new OkHttpUtils.MyNetCall(){
                    @Override
                    public void success(Call call, Response response) throws IOException {
                        Message m = new Message();
                        m.obj = response.body().string();
                        handler.sendMessage(m);
                    }
                    @Override
                    public void failed(Call call, IOException e) {

                    }
                });
            }
        });
        return  view;
    }
}
