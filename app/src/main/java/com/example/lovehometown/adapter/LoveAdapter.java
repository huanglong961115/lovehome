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

import com.example.lovehometown.R;
import com.example.lovehometown.customview.CustomDialog;
import com.example.lovehometown.service.DBService;
import com.example.lovehometown.util.DataCleanManager;
import com.example.lovehometown.util.T;
import com.example.lovehometown.vo.Love;

import org.xutils.ex.DbException;

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
