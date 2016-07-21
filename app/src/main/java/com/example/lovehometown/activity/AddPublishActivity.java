package com.example.lovehometown.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lovehometown.R;
import com.example.lovehometown.adapter.PhotoGridViewAdapter;
import com.example.lovehometown.customview.PublishDialog;
import com.example.lovehometown.util.L;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ContentView(R.layout.activity_add_publish)
public class AddPublishActivity extends BaseActivity {
    /*标题栏*/
    @ViewInject(R.id.leftView)
    private ImageView img;
    @ViewInject(R.id.title)
    private TextView title;
    @ViewInject(R.id.camera_addpublish)
    private ImageView camera;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
   // private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        initView();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initView() {
        /*设置可见*/
        img.setVisibility(View.VISIBLE);
        title.setVisibility(View.VISIBLE);
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        String type = bundle.getString("type");
        title.setText(name + "-" + type);
    }

    @Event(R.id.camera_addpublish)
    private void click(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.camera_addpublish:
                PublishDialog.Builder builder = new PublishDialog.Builder(AddPublishActivity.this);
                View contentView = LayoutInflater.from(AddPublishActivity.this).inflate(R.layout.dialog_camera, null);
                contentView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT));
                Button takephoto = (Button) contentView.findViewById(R.id.takephoto);
                Button choosephoto = (Button) contentView.findViewById(R.id.choosephoto);
                builder.setContentView(contentView);
                PublishDialog dialog2 = builder.create(R.style.dialogStyle);
                takephoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                choosephoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                Window window=dialog2.getWindow();
                dialog2.show();
                WindowManager windowManager = getWindowManager();
                Display display = windowManager.getDefaultDisplay();
                WindowManager.LayoutParams lp = dialog2.getWindow().getAttributes();
                lp.width = (int)(display.getWidth()); //设置宽度
                dialog2.getWindow().setAttributes(lp);
                //设置window显示的位置
                window.setGravity(Gravity.BOTTOM);
                break;
            default:
                break;
        }
    }
    @Event(R.id.leftView)
    private void back(View view){
        AddPublishActivity.this.finish();
        //退出动画效果
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AddPublishActivity.this.finish();
    }


}
