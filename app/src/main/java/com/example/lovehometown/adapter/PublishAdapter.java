package com.example.lovehometown.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.lovehometown.R;
import com.example.lovehometown.activity.AddPublishActivity;
import com.example.lovehometown.activity.StartActivity;
import com.example.lovehometown.customview.CustomDialog;
import com.example.lovehometown.service.DBService;
import com.example.lovehometown.vo.Publish;

import org.json.JSONObject;
import org.xutils.ex.DbException;
import org.xutils.image.ImageOptions;
import org.xutils.x;

public class PublishAdapter extends BaseAdapter {

    private List<Publish> objects = new ArrayList<Publish>();

    private Context context;
    private LayoutInflater layoutInflater;

    public PublishAdapter(Context context,List<Publish> objects) {
        this.context = context;
        this.objects=objects;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Publish getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_mycollections, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((Publish) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(final Publish object, ViewHolder holder) {

        String imgs = object.getPublishImg();
       List<String> imgstr= JSON.parseArray(imgs,String.class);
        //TODO implement
        holder.businessMycollection.setText(object.getBusinessName());
        holder.addressMycollection.setText(object.getBusinessAddress());
        holder.editimgMycollection.setImageResource(R.drawable.img_release_item_modify);
        holder.editMycollection.setText("编辑");
        holder.typeMycollection.setText(object.getXiangxifenlei());
        holder.moneyMycollection.setText(object.getBusinessPrice()+"/"+object.getBusinessMement());
        holder.timeMycollection.setText(object.getBusinessStarttime()+"-"+object.getBusinessEndtime());
        ImageOptions imageOptions = new ImageOptions.Builder()

                // 如果ImageView的大小不是定义为wrap_content, 不要crop.
                .setCrop(true) // 很多时候设置了合适的scaleType也不需要它.
                // 加载中或错误图片的ScaleType
                //.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setLoadingDrawableId(R.drawable.defualt)
                .setFailureDrawableId(R.drawable.defualt)
                .build();
        if(imgstr.size()>0) {
            x.image().bind(holder.imgMycollection, imgstr.get(0), imageOptions);
        }
        /*编辑*/
        holder.shareMycollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                bundle.putInt("msg",1);
                bundle.putParcelable("publish",object);
                intent.putExtras(bundle);
                intent.setClass(context, AddPublishActivity.class);
                context.startActivity(intent);
            }
        });
        holder.deleteMycollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CustomDialog.Builder builder=new CustomDialog.Builder(context,R.layout.dialog_person);
                builder.setMessage("确定删除该条信息？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            DBService.getInstance().deletePublish(object.getLoveId());
                            objects.remove(object);
                            PublishAdapter.this.notifyDataSetChanged();
                        } catch (DbException e) {
                            e.printStackTrace();
                        }
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                CustomDialog dialog=builder.create();
                dialog.show();

            }
        });
    }

    protected class ViewHolder {
        private ImageView imgMycollection;
    private TextView businessMycollection;
    private TextView moneyMycollection;
    private TextView addressMycollection;
    private TextView typeMycollection;
    private TextView timeMycollection;
    private RelativeLayout shareMycollection;
    private ImageView editimgMycollection;
    private TextView editMycollection;
    private RelativeLayout deleteMycollection;

        public ViewHolder(View view) {
            imgMycollection = (ImageView) view.findViewById(R.id.img_mycollection);
            businessMycollection = (TextView) view.findViewById(R.id.business_mycollection);
            moneyMycollection = (TextView) view.findViewById(R.id.money_mycollection);
            addressMycollection = (TextView) view.findViewById(R.id.address_mycollection);
            typeMycollection = (TextView) view.findViewById(R.id.type_mycollection);
            timeMycollection = (TextView) view.findViewById(R.id.time_mycollection);
            shareMycollection = (RelativeLayout) view.findViewById(R.id.share_mycollection);
            editimgMycollection = (ImageView) view.findViewById(R.id.editimg_mycollection);
            editMycollection = (TextView) view.findViewById(R.id.edit_mycollection);
            deleteMycollection = (RelativeLayout) view.findViewById(R.id.delete_mycollection);
        }
    }
}
