package com.example.lovehometown.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lovehometown.R;
import com.example.lovehometown.constant.Constants;
import com.example.lovehometown.model.BusinessList;
import com.example.lovehometown.model.PublishList;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/7/22.
 */
public class MenuSmallAdapter extends BaseAdapter{
    Context context;
    LayoutInflater inflater;
    List<PublishList.PublistBean> publistBeen;

    public MenuSmallAdapter( Context context, List<PublishList.PublistBean> publistBeen) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.publistBeen = publistBeen;
    }

    @Override
    public int getCount() {
        return publistBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return publistBeen.get(position);
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
            convertView=inflater.inflate(R.layout.poup_list_item,null);
            x.view().inject(viewHolder,convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(publistBeen.get(position).getReleaseList().getPublishName());
        return convertView;
    }
    public class ViewHolder{
        @ViewInject(R.id.type_dialog_name)
        TextView textView;
    }
}
