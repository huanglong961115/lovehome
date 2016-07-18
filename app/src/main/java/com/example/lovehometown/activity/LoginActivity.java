package com.example.lovehometown.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lovehometown.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
/*登录页面*/
@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity{
    @ViewInject(R.id.titlebar_login)
    View titleBar;
    /*用户名*/
    @ViewInject(R.id.userName)
    TextView userName;
    /*密码*/
    @ViewInject(R.id.password)
    TextView password;

    private TextView title;
    private ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();

    }
    @Event(value = {R.id.loginBtn,R.id.register,R.id.forwordPassword}, type= View.OnClickListener.class)
    private void change(View v){

    }
    private void initView() {
        img= (ImageView) titleBar.findViewById(R.id.leftView);
        title= (TextView) titleBar.findViewById(R.id.title);
        /*设置可见*/
        img.setVisibility(View.VISIBLE);
        title.setVisibility(View.VISIBLE);
        title.setText("登录");

    }
}
