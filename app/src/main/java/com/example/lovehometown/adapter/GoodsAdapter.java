package com.example.lovehometown.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lovehometown.R;

import org.xutils.view.annotation.ViewInject;

/**
 * Created by Administrator on 2016/7/17.
 */
public class GoodsAdapter extends BaseAdapter{
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
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
}
