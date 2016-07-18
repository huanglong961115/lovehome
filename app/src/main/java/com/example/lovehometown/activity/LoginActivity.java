package com.example.lovehometown.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.lovehometown.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity{
    /*用户名*/
    @ViewInject(R.id.userName)
    TextView userName;
    /*密码*/
    @ViewInject(R.id.password)
    TextView password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();

    }
    @Event(value = {R.id.loginBtn,R.id.register,R.id.forwordPassword}, type= View.OnClickListener.class)
    private void change(View v){

    }
    private void initView() {

    }
}
