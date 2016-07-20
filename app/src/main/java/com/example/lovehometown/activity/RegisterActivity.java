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

/*注册*/
@ContentView(R.layout.activity_register)
public class RegisterActivity extends BaseActivity {
    /*标题栏*/
    @ViewInject(R.id.leftView)
    private ImageView img;
    @ViewInject(R.id.title)
    private TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        /*设置可见*/
        img.setVisibility(View.VISIBLE);
        title.setVisibility(View.VISIBLE);
        title.setText("用户注册");

        //hhh1
    }
    @Event(R.id.leftView)
    private void back(View view){
        RegisterActivity.this.finish();
    }
}
