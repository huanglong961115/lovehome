package com.example.lovehometown.customview;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.lovehometown.R;

/**
 * Created by Administrator on 2016/7/19.
 */
public class PublishDialog  extends Dialog{
    static LayoutInflater inflater;
    public PublishDialog(Context context) {
        super(context);
        this.inflater=LayoutInflater.from(context);
    }

    public PublishDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.inflater=LayoutInflater.from(context);
    }

    protected PublishDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.inflater=LayoutInflater.from(context);
    }
    public static class DialogBuilder{
        Context context;
        View contentView;//内容布局


        public View getContentView() {
            return contentView;
        }

        public void setContentView(View contentView) {
            this.contentView = contentView;
        }

        public PublishDialog onCreate(){
            final PublishDialog dialog=new PublishDialog(context,R.style.Daliog);
            View view=PublishDialog.inflater.inflate(R.layout.publish_dialog,null);

            if(contentView!=null){
                View layout=view.findViewById(R.id.infoView);
                ((LinearLayout) layout).removeAllViews();

                ((LinearLayout) layout).addView(contentView,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }
            dialog.setContentView(view);

            return dialog;
        }
    }
}
