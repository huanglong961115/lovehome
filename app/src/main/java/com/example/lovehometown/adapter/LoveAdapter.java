package com.example.lovehometown.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.example.lovehometown.R;
import com.example.lovehometown.constant.Constants;
import com.example.lovehometown.customview.CustomDialog;
import com.example.lovehometown.service.DBService;
import com.example.lovehometown.util.DataCleanManager;
import com.example.lovehometown.util.T;
import com.example.lovehometown.vo.Love;

import org.xutils.ex.DbException;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class LoveAdapter extends BaseAdapter {

    private List<Love> objects = new ArrayList<Love>();

    private Context context;
    private LayoutInflater layoutInflater;

    public LoveAdapter(Context context,List<Love> objects) {
        this.context = context;
        this.objects=objects;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Love getItem(int position) {
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
        initializeViews((Love)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(final Love object, ViewHolder holder) {
        //TODO implement
        holder.businessMycollection.setText(object.getBusinessName());
        holder.moneyMycollection.setText(object.getBusinessPrice());
        holder.timeMycollection.setText(object.getBusinessStarttime()+"-"+object.getBusinessEndtime());
        holder.addressMycollection.setText(object.getBusinessAddress());
        holder.typeMycollection.setText(object.getType());
        holder.shareMycollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jsonData= JSON.toJSONString(object);
                showShare(jsonData);
            }
        });
        /*删除*/
        holder.deleteMycollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CustomDialog.Builder builder=new CustomDialog.Builder(context,R.layout.dialog_person);
                builder.setMessage("确定删除该条信息？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            DBService.getInstance().delete(object.getLoveId());
                            objects.remove(object);
                            LoveAdapter.this.notifyDataSetChanged();
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
    private void showShare(String jsonData) {
        ShareSDK.initSDK(context);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("分享");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl(Constants.DETAILS_URL+"?jsondata="+jsonData);
        // text是分享文本，所有平台都需要这个字段
        oks.setText("爱家乡");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(Constants.DETAILS_URL+"?jsondata="+jsonData);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("爱家乡");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(context.getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(Constants.DETAILS_URL+"?jsondata="+jsonData);

// 启动分享GUI
        oks.show(context);
    }
    protected class ViewHolder {
        private ImageView imgMycollection;
        private TextView businessMycollection;
        private TextView moneyMycollection;
        private TextView addressMycollection;
        private TextView typeMycollection;
        private TextView timeMycollection;
        private RelativeLayout shareMycollection;
        private RelativeLayout deleteMycollection;

        public ViewHolder(View view) {
            imgMycollection = (ImageView) view.findViewById(R.id.img_mycollection);
            businessMycollection = (TextView) view.findViewById(R.id.business_mycollection);
            moneyMycollection = (TextView) view.findViewById(R.id.money_mycollection);
            addressMycollection = (TextView) view.findViewById(R.id.address_mycollection);
            typeMycollection = (TextView) view.findViewById(R.id.type_mycollection);
            timeMycollection = (TextView) view.findViewById(R.id.time_mycollection);
            shareMycollection = (RelativeLayout) view.findViewById(R.id.share_mycollection);
            deleteMycollection = (RelativeLayout) view.findViewById(R.id.delete_mycollection);
        }
    }
}
