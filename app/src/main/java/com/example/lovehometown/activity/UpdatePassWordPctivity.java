package com.example.lovehometown.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lovehometown.R;
import com.example.lovehometown.constant.Constants;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_update_pass_word_pctivity)
public class UpdatePassWordPctivity extends BaseActivity {
    @ViewInject(R.id.title)
    TextView title;
    @ViewInject(R.id.leftView)
    ImageView backImg;
    @ViewInject(R.id.btn_update_forget_password)
    Button forgetPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }
    /**
     * 初始化视图
     */
    public void initView(){
        title.setVisibility(View.VISIBLE);
        title.setText("修改密码");
        backImg.setVisibility(View.VISIBLE);

    }
    @Event(R.id.leftView)
    private void back(View view){
        UpdatePassWordPctivity.this.finish();
    }
    @Event(R.id.btn_update_forget_password)
    private void forgetPassword(View view){
        startActivity(new Intent(this,ForgetPasswordctivity.class));
    }
}
