package com.example.lovehometown.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lovehometown.R;
import com.example.lovehometown.model.GoodBigType;
import com.example.lovehometown.util.L;
import com.example.lovehometown.util.T;

import org.w3c.dom.Text;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/7/17.
 */

/**
 * 图片gridview的适配器
 */
public class ImgGridViewAdapter extends BaseAdapter{
    /**
     * 传入的数据
     */
    private List<GoodBigType> list;
    /**
     * 上下文
     */
    private Context context;
    /**
     * 鹏胀器
     */
    private LayoutInflater inflater;

    public ImgGridViewAdapter(Context context, List<GoodBigType> list) {
        this.context = context;
        this.list = list;
        this.inflater =LayoutInflater.from(context);
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView=inflater.inflate(R.layout.img_gridview_item,null);
            x.view().inject(viewHolder,convertView);
            convertView.setTag(viewHolder);
        }else{
            L.e(list.toString());
            viewHolder= (ViewHolder) convertView.getTag();
        }

        viewHolder.imgView.setImageResource(list.get(position).getImgUrl());
        viewHolder.textView.setText(list.get(position).getName());
        return convertView;
    }
    class ViewHolder{
        @ViewInject(R.id.gridView_img)
        ImageView imgView;
        @ViewInject(R.id.gridView_text)
        TextView textView;
    }
}
