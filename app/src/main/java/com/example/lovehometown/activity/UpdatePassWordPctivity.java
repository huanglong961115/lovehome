package com.example.lovehometown.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.lovehometown.R;
import com.example.lovehometown.callback.LoveHomeCallBack;
import com.example.lovehometown.constant.Constants;
import com.example.lovehometown.model.UpdateInfo;
import com.example.lovehometown.model.UserInfo;
import com.example.lovehometown.service.HttpService;
import com.example.lovehometown.util.L;
import com.example.lovehometown.util.SPUtils;
import com.example.lovehometown.util.T;

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
    @ViewInject(R.id.edt_update_old_password)
    EditText oldPass;
    @ViewInject(R.id.edt_update_ensure_password1)
    EditText newPass;
    @ViewInject(R.id.edt_update_ensure_password2)
    EditText confirmPass;
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
        //广播注册
        UpdateBroadCast reciver=new UpdateBroadCast();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.UPDATE_FORGET_ACTION);

        registerReceiver(reciver,intentFilter);

    }
    @Event(R.id.leftView)
    private void back(View view){
        UpdatePassWordPctivity.this.finish();
        //退出动画效果
        overridePendingTransition(R.anim.left_in,R.anim.left_out);
    }

    @Override
    public void onBackPressed() {
        UpdatePassWordPctivity.this.finish();
        //退出动画效果
        overridePendingTransition(R.anim.left_in,R.anim.left_out);
    }

    @Event(R.id.btn_update_forget_password)
    private void forgetPassword(View view){
        Intent intent=new Intent();
        Bundle bundle=new Bundle();
        bundle.putString("what","update");
        intent.setClass(this,ForgetPasswordctivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        //startActivity(new Intent(this,ForgetPasswordctivity.class));
        overridePendingTransition(R.anim.right_in,R.anim.right_out);
    }
    @Event(R.id.btn_update_password)
    private void updatePassWord(View view){
        //
        String oldPasswrod=oldPass.getText().toString();
        //新密码
        String newPassword=newPass.getText().toString();
        //确认新密码
        String confirmPassword=confirmPass.getText().toString();
        if(oldPasswrod.trim().length()!=6){
            T.showShort(UpdatePassWordPctivity.this,"旧密码格式输入不对");
            return;
        }else if(newPassword.trim().length()!=6) {
            T.showShort(UpdatePassWordPctivity.this, "新密码格式输入不对");
            return;
        }else if(!newPassword.equals(confirmPassword)){
            T.showShort(UpdatePassWordPctivity.this, "2次确认密码不一致");
            return;
        }
        //获取用户号码
        String userInfo= (String) SPUtils.get(UpdatePassWordPctivity.this,Constants.USER_INFO,"");
        if(userInfo.equals("")){

        }else {
            UserInfo.UserBean userBean= JSON.parseObject(userInfo, UserInfo.UserBean.class);
            HttpService.getHttpService().updatePassword(userBean.getPhoneNuber(), oldPasswrod, newPassword, new LoveHomeCallBack<String>() {
                @Override
                public void onSuccess(String result) {
                    T.showShort(UpdatePassWordPctivity.this,result);
                    UpdateInfo updateInfo=JSON.parseObject(result,UpdateInfo.class);
                    if(updateInfo.getResults().getCode()!=1){
                        T.showShort(UpdatePassWordPctivity.this,updateInfo.getResults().getMsg());
                    }else{
                        T.showShort(UpdatePassWordPctivity.this,updateInfo.getResults().getMsg());
                        SPUtils.remove(UpdatePassWordPctivity.this,Constants.IS_LOGIN);
                        SPUtils.remove(UpdatePassWordPctivity.this,Constants.USER_INFO);
                        Intent intent=new Intent(Constants.REGISTER_ACTION);
                        sendBroadcast(intent);
                        Bundle bundle = new Bundle();
                        bundle.putString(Constants.NAME, "my");
                        Intent intent2 = new Intent();
                        intent2.putExtras(bundle);
                        intent2.setClass(UpdatePassWordPctivity.this, LoginActivity.class);
                        startActivity(intent2);
                        UpdatePassWordPctivity.this.finish();
                    }
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                       T.showShort(UpdatePassWordPctivity.this,"网络连接失败,检查网络设置");
                }
            });
        }
    }
    //广播接收者
    private class UpdateBroadCast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(Constants.UPDATE_FORGET_ACTION)){
               UpdatePassWordPctivity.this.finish();
            }
        }
    }
}
