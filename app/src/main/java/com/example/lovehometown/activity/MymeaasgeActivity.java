package com.example.lovehometown.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lovehometown.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_mymeaasge)
public class MymeaasgeActivity extends BaseActivity {
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
           title.setText("我的消息");
           backImg.setVisibility(View.VISIBLE);
      }

    /**
     * 返回键的监听
     * @param view
     */
    @Event(R.id.leftView)
    private void back(View view){
        MymeaasgeActivity.this.finish();
        //退出动画效果
        overridePendingTransition(R.anim.left_in,R.anim.left_out);
    }

    @Override
    public void onBackPressed() {
        MymeaasgeActivity.this.finish();
        //退出动画效果
        overridePendingTransition(R.anim.left_in,R.anim.left_out);
    }
}
