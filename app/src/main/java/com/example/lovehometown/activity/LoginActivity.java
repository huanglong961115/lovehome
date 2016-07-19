package com.example.lovehometown.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lovehometown.R;
import com.example.lovehometown.util.T;

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
    EditText userName;
    /*密码*/
    @ViewInject(R.id.password)
    EditText password;
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
    @Event(value = {R.id.loginBtn,R.id.register,R.id.forwordPassword}, type= View.OnClickListener.class)
    private void change(View v){
       switch (v.getId()){
           case R.id.register:
               startActivity(new Intent(this,RegisterActivity.class));
               this.finish();
               break;
           case R.id.forwordPassword:
               startActivity(new Intent(this,ForgetPasswordctivity.class));
               this.finish();
               break;
       }
    }
    private void initView() {
        /*设置可见*/
        img.setVisibility(View.VISIBLE);
        title.setVisibility(View.VISIBLE);
        title.setText("登录");
        String username=userName.getText().toString();
        String pass=password.getText().toString();
        //判断用户名和密码是否为空
        //用户名的正则判断
        String reg="^\\s*$";
        if(username.matches(reg)){
            T.showShort(this,"用户名不能为空");
            return;
        }
        if(pass==null||pass.trim().equals("")){
            T.showShort(this,"密码不能为空");
            return;
        }

    }
    @Event(R.id.leftView)
    private void back(View view){
        LoginActivity.this.finish();
    }
}
