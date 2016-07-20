package com.example.lovehometown.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lovehometown.R;
import com.example.lovehometown.constant.Constants;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_user_info)
public class UserInfoActivity extends BaseActivity {
    @ViewInject(R.id.title)
    TextView title;
    @ViewInject(R.id.leftView)
    ImageView backImg;
    @ViewInject(R.id.userNameInfo)
    RelativeLayout userNameInfo;
    @ViewInject(R.id.userphoneInfo)
    RelativeLayout userPhoneInfo;
    @ViewInject(R.id.userAddressInfo)
    RelativeLayout userAddressInfo;
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
        title.setText("用户资料");
        backImg.setVisibility(View.VISIBLE);

    }
    @Event(value={R.id.userNameInfo,R.id.userphoneInfo,R.id.userAddressInfo})
    private void updateInfo(View view){
        switch (view.getId()){
            case R.id.userNameInfo:
                updateInfo(UpdateInfoActivity.class,Constants.USERNMAE);
                break;
            case R.id.userphoneInfo:
                updateInfo(UpdateInfoActivity.class,Constants.USER_PHONE);
                break;
            case R.id.userAddressInfo:
                updateInfo(UpdateInfoActivity.class,Constants.USER_ADDRESS);
                break;
        }
    }

    /**
     * 页面跳转
     * @param clazz 跳转的类
     * @param attr 传递过去的参数
     */
    public void updateInfo(Class clazz,String attr){
        Bundle bundle=new Bundle();
        bundle.putString(Constants.INFO_ATTR,attr);
        Intent intent=new Intent();
        intent.putExtras(bundle);
        intent.setClass(UserInfoActivity.this, clazz);
        startActivity(intent);
        overridePendingTransition(R.anim.right_in,R.anim.right_out);
    }
    @Event(R.id.leftView)
    private void back(View view){
        UserInfoActivity.this.finish();
        //退出动画效果
        overridePendingTransition(R.anim.left_in,R.anim.left_out);
    }

    @Override
    public void onBackPressed() {
        UserInfoActivity.this.finish();
        //退出动画效果
        overridePendingTransition(R.anim.left_in,R.anim.left_out);
    }
}
