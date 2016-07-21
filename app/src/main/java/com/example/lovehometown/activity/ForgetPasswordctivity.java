package com.example.lovehometown.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.lovehometown.R;
import com.example.lovehometown.callback.LoveHomeCallBack;
import com.example.lovehometown.constant.Constants;
import com.example.lovehometown.customview.CustomDialog;
import com.example.lovehometown.model.ForgetPassword;
import com.example.lovehometown.model.SMS;
import com.example.lovehometown.service.HttpService;
import com.example.lovehometown.util.L;
import com.example.lovehometown.util.T;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ContentView(R.layout.activity_forget_passwordctivity)
public class ForgetPasswordctivity extends BaseActivity {
    @ViewInject(R.id.title)
    TextView title;
    @ViewInject(R.id.leftView)
    ImageView backImg;
    @ViewInject(R.id.btn_forget_password_get_password)
    Button getPassWord;
    //验证码按钮
    @ViewInject(R.id.btn_forget_send_code)
    TextView sendCode;
    //手机号码输入框
    @ViewInject(R.id.edt_forget_phone)
    EditText phone;
    @ViewInject(R.id.edt_forget_phone_code)
    EditText code;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                int time= (int) msg.obj;
                sendCode.setText(time+"秒后重新发送");
            }else if(msg.what==0){
                sendCode.setText("重新获取验证码");
                sendCode.setEnabled(true);
                sendCode.setTextColor(Color.parseColor("#ffffff"));
            }
        }
    };
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
        title.setText("忘记密码");
        backImg.setVisibility(View.VISIBLE);


    }
    @Event(R.id.leftView)
    private void back(View view){
        ForgetPasswordctivity.this.finish();
        //退出动画效果
        overridePendingTransition(R.anim.left_in,R.anim.left_out);
    }

    @Override
    public void onBackPressed() {
        ForgetPasswordctivity.this.finish();
        //退出动画效果
        overridePendingTransition(R.anim.left_in,R.anim.left_out);
    }
    @Event(R.id.btn_forget_send_code)
    private void sendCode(View view){
        String phoneNumber=phone.getText().toString();
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");

        Matcher m = p.matcher(phoneNumber);
        if(!m.matches()){
            T.showShort(ForgetPasswordctivity.this,"手机号码不合法,请重新输入");
            return;
        }
        sendCode.setText("59秒后重新发送");
        sendCode.setTextColor(Color.parseColor("#808080"));
        sendCode.setEnabled(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=59;i>=0;i--){
                    try {
                        Thread.sleep(1000);
                        Message message=new Message();
                        message.what=1;
                        message.obj=i;
                        handler.sendMessage(message);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Message message=new Message();
                message.what=0;

                handler.sendMessage(message);
            }
        }).start();
        //发送验证码
        HttpService.getHttpService().sendCode(phoneNumber, new LoveHomeCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                L.e("TAG",result);
                SMS sms= JSON.parseObject(result, SMS.class);
                if(sms.getCode()==0){
                    T.showShort(ForgetPasswordctivity.this,sms.getMsg());
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                T.showShort(ForgetPasswordctivity.this,"网路连接失败,发送失败");
            }
        });
    }

    //点击获取登录密码
    @Event(R.id.btn_forget_password_get_password)
    private void getPass(View view){
        //获取密码
        //手机号码是否合法
        String phoneNumber=phone.getText().toString();
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");

        Matcher m = p.matcher(phoneNumber);
        if(!m.matches()){
            T.showShort(ForgetPasswordctivity.this,"手机号码不合法,请重新输入");
            return;
        }
        //验证码是否为空
        final String reg="^\\s*$";
        String sendCode=code.getText().toString();
        if(sendCode.matches(reg)){
            T.showShort(ForgetPasswordctivity.this,"请输入验证码");
            return;
        }
        HttpService.getHttpService().forgetPassword(phoneNumber, sendCode, new LoveHomeCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                ForgetPassword forgetPassword=JSON.parseObject(result,ForgetPassword.class);
                T.showShort(ForgetPasswordctivity.this,forgetPassword.getResults().getMsg());
               if(forgetPassword.getResults().getCode()!=1){
                    T.showShort(ForgetPasswordctivity.this,forgetPassword.getResults().getMsg());
                    return;
                }else{
                    T.showShort(ForgetPasswordctivity.this,forgetPassword.getResults().getMsg());
                    CustomDialog.Builder builder=new CustomDialog.Builder(ForgetPasswordctivity.this,R.layout.dialog);
                    builder.setMessage("你的密码是："+forgetPassword.getUser().getPassword());
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //获取传过来的值
                            Bundle bundle=getIntent().getExtras();
                            String what=bundle.getString("what");
                            if("update".equals(what)){
                                Intent intent=new Intent(Constants.UPDATE_FORGET_ACTION);
                                sendBroadcast(intent);
                            }else{
                                Intent intent=new Intent(Constants.FORGET_ACTION);
                                sendBroadcast(intent);
                            }
                            dialog.dismiss();
                        }
                    });
                    CustomDialog dialog=builder.create();
                    dialog.show();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                T.showShort(ForgetPasswordctivity.this,"网路连接失败,获取密码失败");
            }
        });

     //发送广播
        //Intent intent=new Intent(Constants.FORGET_ACTION);
        //sendBroadcast(intent);
    }
}
