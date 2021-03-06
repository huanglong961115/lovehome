package com.example.lovehometown.adapter;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lovehometown.R;
import com.example.lovehometown.customview.CustomDialog;
import com.example.lovehometown.model.BusinessList;
import com.example.lovehometown.model.ShopInfo;

import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/7/17.
 */
public class GoodsAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater;
    List<BusinessList.PublistBean> list;
    ImageOptions imageOptions;
    public GoodsAdapter(List<BusinessList.PublistBean> list, Context context) {
        this.list = list;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.imageOptions = new ImageOptions.Builder()

                // 如果ImageView的大小不是定义为wrap_content, 不要crop.
                .setCrop(true) // 很多时候设置了合适的scaleType也不需要它.
                // 加载中或错误图片的ScaleType
                //.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setLoadingDrawableId(R.drawable.defualt)
                .setFailureDrawableId(R.drawable.defualt)
                .build();
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
        ViewHolder viewHolder = null;
        if (viewHolder == null) {
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.goods_list_item, null);
            x.view().inject(viewHolder, convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        initData(position, viewHolder);
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
        @ViewInject(R.id.waimai_image)
        private ImageView waimai_image;
    }

    public void initData(int position, ViewHolder viewHolder) {
        BusinessList.PublistBean shopInfo = list.get(position);
        final BusinessList.PublistBean _shopInfo = shopInfo;
        //设置名字
        viewHolder.name.setText(shopInfo.getBusinessName());
        //价格
        viewHolder.price.setText(shopInfo.getBusinessPrice());
        //地址
        viewHolder.address.setText(shopInfo.getBusinessAddress());
        viewHolder.columnTypeName.setText(shopInfo.getXiangxifenlei());
        x.image().bind(viewHolder.shopImage,shopInfo.getPublishImg(),imageOptions);
        //支持外卖
        if(shopInfo.getIstakeaway()==1){
            viewHolder.waimai_image.setVisibility(View.VISIBLE);
        }
        viewHolder.time.setText(shopInfo.getBusinessStarttime()+"-"+shopInfo.getBusinessEndtime());
        viewHolder.listPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //弹出拨打电话确认框
                CustomDialog.Builder dialog = new CustomDialog.Builder(context,R.layout.dialog);
                dialog.setMessage("确定拨打:" + _shopInfo.getBusinessPhone());
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //调用系统拨打电话
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" +_shopInfo.getBusinessPhone()));
                        //权限检查
                        //sdk23之后

                        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
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
