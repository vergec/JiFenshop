package com.example.jifenshop.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.jifenshop.R;
import com.example.jifenshop.bean.NewdingdanBean;

import java.util.List;

public class BdapterDelivery extends BaseAdapter {
    private List<NewdingdanBean> deliveryRecordList;
    private LayoutInflater layoutInflater;
    private Context context;
    private TextView okind,gname,price,number,all;
    private Button delete;

//    private Handler handler= new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            String response = (String)msg.obj;
//            Gson gson = new Gson();
//            addGoodsBean bean = new Gson().fromJson(response,addGoodsBean.class);
//            if(bean.getStatus()==1) {
//                Toast.makeText(context, "下架成功", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent();
//                intent.setClass(context,sj_mygoods.class);
//                context.startActivity(intent);
//            }
//            else{
//                Toast.makeText(context,"下架失败",Toast.LENGTH_SHORT).show();
//            }
//        }
//    };
    public BdapterDelivery(Context context, List<NewdingdanBean> deliveryRecords){
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
        View view = layoutInflater.inflate(R.layout.sj_weiqueding, null);
        final NewdingdanBean newdingdanBean= deliveryRecordList.get(position);

        okind = view.findViewById(R.id.okind);
        gname = view.findViewById(R.id.gname);
        price = view.findViewById(R.id.price);
        number = view.findViewById(R.id.number);
        all = view.findViewById(R.id.all);

        okind.setText(newdingdanBean.getOkind().toString());
        gname.setText(newdingdanBean.getOkind().toString());
        price.setText('￥'+String.valueOf(newdingdanBean.getPrice()));
        number.setText(String.valueOf(newdingdanBean.getNumber()));
        all.setText(String.valueOf(newdingdanBean.getPrice()*newdingdanBean.getNumber()));
//        delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                HashMap<String, String> map = new HashMap<>();
//                map.put("gid",String.valueOf(newgoodsBean.getGid()));
//                OkHttpUtils.getInstance().postDataAsyn(APIUtil.HOST_NAME + "/deletegoods",map,new OkHttpUtils.MyNetCall(){
//                    @Override
//                    public void success(Call call, Response response) throws IOException {
//                        Message m = new Message();
//                        m.obj = response.body().string();
//                        handler.sendMessage(m);
//                    }
//                    @Override
//                    public void failed(Call call, IOException e) {
//
//                    }
//                });
//            }
//        });
        return  view;
    }
}
