package com.example.lovehometown.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.lovehometown.R;
import com.example.lovehometown.callback.LoveHomeCallBack;
import com.example.lovehometown.constant.Constants;
import com.example.lovehometown.model.RegisterInfo;
import com.example.lovehometown.model.SMS;
import com.example.lovehometown.service.HttpService;
import com.example.lovehometown.util.L;
import com.example.lovehometown.util.T;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*注册*/
@ContentView(R.layout.activity_register)
public class RegisterActivity extends BaseActivity {
    /*标题栏*/
    @ViewInject(R.id.leftView)
    private ImageView img;
    @ViewInject(R.id.title)
    private TextView title;
    @ViewInject(R.id.btn_agreement_context2)
    private TextView btn_agreement;
    //验证码按钮
    @ViewInject(R.id.btn_register_send_code)
    TextView sendCode;
    //手机号码输入框
    @ViewInject(R.id.edt_register_phone)
    EditText phone;
    @ViewInject(R.id.btn_register)
    Button registerBtn;
    @ViewInject(R.id.edt_register_phone_code)
    EditText code;
    @ViewInject(R.id.edt_register_password)
    EditText password;
    @ViewInject(R.id.edt_register_ensure_password)
    EditText confirmPass;
    @ViewInject(R.id.check_agreement)
    CheckBox agreement;
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

    private void initView() {
        /*设置可见*/
        img.setVisibility(View.VISIBLE);
        title.setVisibility(View.VISIBLE);
        title.setText("用户注册");

        //hhh1
    }
    @Event(R.id.btn_register_send_code)
    private void sendCode(View view){
        String phoneNumber=phone.getText().toString();
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");

       Matcher m = p.matcher(phoneNumber);
        if(!m.matches()){
            T.showShort(RegisterActivity.this,"手机号码不合法,请重新输入");
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
                    T.showShort(RegisterActivity.this,sms.getMsg());
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                T.showShort(RegisterActivity.this,"网路连接失败,发送失败");
            }
        });
    }
    //注册
    @Event(R.id.btn_register)
    private void register(View view){
        //手机号码是否合法
        String phoneNumber=phone.getText().toString();
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");

        Matcher m = p.matcher(phoneNumber);
        if(!m.matches()){
            T.showShort(RegisterActivity.this,"手机号码不合法,请重新输入");
            return;
        }
        //验证码是否为空
        final String reg="^\\s*$";
        String sendCode=code.getText().toString();
        if(sendCode.matches(reg)){
            T.showShort(RegisterActivity.this,"请输入验证码");
            return;
        }
        String pass=password.getText().toString();
        //是否是6位密码
        if(pass.trim().length()!=6){
            T.showShort(RegisterActivity.this,"密码不符合要求");
            return;
        }
        //确认密码是否和密码相同
        String surePass=confirmPass.getText().toString();
        if(!pass.equals(surePass)){
            T.showShort(RegisterActivity.this,"2次输入的密码不一致");
            return;
        }

        //是否同意服务条款
       if(!agreement.isChecked()) {
           T.showShort(RegisterActivity.this,"请同意服务条款");
           return;
       }
        HttpService.getHttpService().register(phoneNumber, sendCode, pass, new LoveHomeCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                RegisterInfo registerInfo=JSON.parseObject(result,RegisterInfo.class);
                if(registerInfo.getResults().getCode()!=1){
                    T.showShort(RegisterActivity.this,registerInfo.getResults().getMsg());
                    return;
                }
                //register
                //发送广播
               T.showShort(RegisterActivity.this,"注册成功,进入登录界面");
                Intent intent=new Intent(Constants.REGISTER_ACTION);
                sendBroadcast(intent);
               Bundle bundle = new Bundle();
                bundle.putString(Constants.NAME, "my");
                Intent intent2 = new Intent();
                intent2.putExtras(bundle);
                intent2.setClass(RegisterActivity.this, LoginActivity.class);
                startActivity(intent2);
                //startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                //结束掉当前Activity

                RegisterActivity.this.finish();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                   T.showShort(RegisterActivity.this,"网络连接失败,请检查网络设置");
            }
        });
    }
    @Event (value={R.id.leftView,R.id.btn_agreement_context2})
    private void back(View view){
        int id=view.getId();
        switch (id) {
            case R.id.leftView:
                RegisterActivity.this.finish();
                break;
            //服务协议跳转页面
            case R.id.btn_agreement_context2:
                Intent intent = new Intent(this, PlatformStatementActivity.class);
                intent.putExtra("service", Constants.SERVICE);
                startActivity(intent);
                this.overridePendingTransition(R.anim.right_in, R.anim.right_out);
                break;
        }
    }

}
