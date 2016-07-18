package com.example.lovehometown.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lovehometown.R;
import com.example.lovehometown.customview.CustomDialog;
import com.example.lovehometown.model.ShopInfo;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/7/17.
 */
public class GoodsAdapter extends BaseAdapter{
    Context context;
    LayoutInflater layoutInflater;
    List<ShopInfo> list;

    public GoodsAdapter(List<ShopInfo> list, Context context) {
        this.list = list;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder viewHolder=null;
        if(viewHolder==null){
            viewHolder=new ViewHolder();
            convertView=layoutInflater.inflate(R.layout.goods_list_item,null);
            x.view().inject(viewHolder,convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        initData(position,viewHolder);
        return convertView;
    }
    class ViewHolder {
        @ViewInject(R.id.shop_image)
        private ImageView shopImage;
        @ViewInject(R.id.name)
        private TextView name;
        @ViewInject(R.id.v_image)
        private ImageView vImage;
        @ViewInject(R.id.waimai_image)
        private ImageView waimaiImage;
        @ViewInject(R.id.price_yuan)
        private TextView priceYuan;
        @ViewInject(R.id.price)
        private TextView price;
        @ViewInject(R.id.list_phone)
        private ImageView listPhone;
        @ViewInject(R.id.Address)
        private TextView address;
        @ViewInject(R.id.ColumnTypeName)
        private TextView columnTypeName;
        @ViewInject(R.id.time)
        private TextView time;
    }
    public void initData(int position,ViewHolder viewHolder){
        ShopInfo shopInfo=list.get(position);
       final  ShopInfo _shopInfo=shopInfo;
        //设置名字
        viewHolder.name.setText(shopInfo.getName());
        //价格
        viewHolder.price.setText(shopInfo.getPrice());
        //地址
        viewHolder.address.setText(shopInfo.getAddress());
        viewHolder.columnTypeName.setText(shopInfo.getType());
        viewHolder.time.setText(shopInfo.getTime());
        viewHolder.listPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog.Builder dialog=new CustomDialog.Builder(context);
                dialog.setMessage("确定拨打"+_shopInfo.getPhone());
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+_shopInfo.getPhone()));
                        context.startActivity(intent);
                        dialog.dismiss();
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                    }
                });
                dialog.create().show();
            }
        });
    }
}
