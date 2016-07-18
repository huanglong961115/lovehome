package com.example.lovehometown.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lovehometown.R;
import com.example.lovehometown.constant.Constants;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_my_publish)
public class MyPublishActivity extends BaseActivity {
    @ViewInject(R.id.title)
    TextView title;
    @ViewInject(R.id.leftView)
    ImageView backImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        title.setVisibility(View.VISIBLE);
        backImg.setVisibility(View.VISIBLE);
        Bundle bundle=getIntent().getBundleExtra(Constants.TYPE);
        String type=bundle.getString(Constants.MY_TYPE);
        if(type.equals(Constants.MY_PUBLISH)){
            title.setText("我的发布");
        }else if(type.equals(Constants.MY_COLLECT)){
            title.setText("我的收藏");
        }else if(type.equals(Constants.MY_DRAFT)){
            title.setText("我的草稿");
        }
    }
    @Event(R.id.leftView)
    private void back(View view){
        MyPublishActivity.this.finish();
        //退出动画效果
        overridePendingTransition(R.anim.left_in,R.anim.left_out);
    }

    @Override
    public void onBackPressed() {
        MyPublishActivity.this.finish();
        //退出动画效果
        overridePendingTransition(R.anim.left_in,R.anim.left_out);
    }
}
