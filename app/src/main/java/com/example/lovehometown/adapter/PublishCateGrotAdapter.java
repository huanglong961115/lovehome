package com.example.lovehometown.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lovehometown.R;
import com.example.lovehometown.model.PublishList;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/7/19.
 */
public class PublishCateGrotAdapter extends BaseAdapter{
    Context context;
    List<PublishList.PublistBean> list;
    LayoutInflater inflater;

    public PublishCateGrotAdapter(Context context, List<PublishList.PublistBean> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
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
            convertView=inflater.inflate(R.layout.publish_dilog_list_item,null);
            x.view().inject(viewHolder, convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(list.get(position).getReleaseList().getPublishName());
        return convertView;
    }
    class ViewHolder{
        @ViewInject(R.id.text_publish_dialog)
        TextView textView;
    }
}
