package com.example.lovehometown.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.lovehometown.R;

import java.util.List;

/**
 * Created by Administrator on 2016/7/20.
 */
public class PhotoGridViewAdapter extends BaseAdapter{
    Context context;
    LayoutInflater inflater;
    List<Bitmap> date;
    public PhotoGridViewAdapter(Context context,List<Bitmap> date){
        this.context=context;
        inflater=LayoutInflater.from(context);
        this.date=date;
    }
    @Override
    public int getCount() {
        return date.size();
    }

    @Override
    public Object getItem(int position) {
        return date.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null){
            holder=new ViewHolder();
            convertView=inflater.inflate(R.layout.grid_photo,parent,false);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.img= (ImageView) convertView.findViewById(R.id.img_grid);
        holder.img.setImageBitmap(date.get(position));
        return convertView;
    }
    public class ViewHolder{
        ImageView img;
    }
}
