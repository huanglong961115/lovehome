package com.example.lovehometown.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.lovehometown.R;
import com.example.lovehometown.callback.LoveHomeCallBack;
import com.example.lovehometown.common.Login;
import com.example.lovehometown.constant.Constants;
import com.example.lovehometown.customview.CustomProgressDialog;
import com.example.lovehometown.model.UserInfo;
import com.example.lovehometown.service.HttpService;
import com.example.lovehometown.util.L;
import com.example.lovehometown.util.SPUtils;
import com.example.lovehometown.util.T;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;
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
    @ViewInject(R.id.qqLogin)
    ImageView qqLogin;
    Tencent mTencent;
    IUiListener loginListener = new BaseUiListener() {
        @Override
        protected void doComplete(JSONObject values) {

           // Log.e("TAG",values.toString());
            //获取用户信息
            try {
                int ret=values.getInt("ret");
                if(ret!=0){
                    T.showShort(LoginActivity.this,"获取用户信息失败,请重新登录");
                    return;
                }else{
                    //获得昵称
                    String nickName=values.getString("nickname");
                    String imgUrl=values.getString("figureurl_qq_2");
                    UserInfo.UserBean userBean=new UserInfo.UserBean();
                    userBean.setHeadImg(imgUrl);
                    userBean.setUsername(nickName);
                    String data=JSON.toJSONString(userBean);
                    saveInfo(data);
                    loginSuccess();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTencent = Tencent.createInstance(Constants.APP_ID, this.getApplicationContext());
        initView();

    }
    @Event(value = {R.id.register,R.id.forwordPassword}, type= View.OnClickListener.class)
    private void change(View v){
       switch (v.getId()){
           case R.id.register:
               startActivity(new Intent(this,RegisterActivity.class));

               //this.finish();
               break;
           case R.id.forwordPassword:
               Intent intent=new Intent();
               Bundle bundle=new Bundle();
               bundle.putString("what","login");
               intent.setClass(this,ForgetPasswordctivity.class);
               intent.putExtras(bundle);
               startActivity(intent);
               //this.finish();
               break;

       }

    }
    //qq登录
    @Event(R.id.qqLogin)
    private void qqLogin(View view){
      mTencent.login(LoginActivity.this,"all",new BaseUiListener());
    }
    //登录
    @Event(R.id.loginBtn)
    private void login(View view){
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
        //弹出登录进度对话框
        dialog=new CustomProgressDialog(LoginActivity.this,"登录中",R.drawable.load_anim);
        //显示
        dialog.show();
        //进行登录
        HttpService.getHttpService().login(username, pass, new LoveHomeCallBack<String>() {
                @Override
                public void onSuccess(String result) {
                  // T.showShort(LoginActivity.this,result);
                    //解析从网络拿到的数据
                UserInfo userInfo= JSON.parseObject(result,UserInfo.class);
               //判断是否为-1 登录失败
                if(userInfo.getResults().getCode()==-1){
                    dialog.dismiss();
                    T.showShort(LoginActivity.this,"登录失败,用户名或者密码错误");
                    return;
                }else{
                    dialog.dismiss();
                    //
                    UserInfo.UserBean user= userInfo.getUser();
                    //转换为json字符串,存入share
                    String data=JSON.toJSONString(user);
                    T.showShort(LoginActivity.this,data);
                    saveInfo(data);
                    loginSuccess();

                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                dialog.dismiss();
                T.showShort(LoginActivity.this,"网络连接失败,请检查网络设置");
            }
        });
    }
    //登录对话框
    CustomProgressDialog dialog;
    private void initView() {
        /*设置可见*/
        img.setVisibility(View.VISIBLE);
        title.setVisibility(View.VISIBLE);
        title.setText("登录");
        //广播注册
       LoginBroadCast reciver=new LoginBroadCast();
     IntentFilter intentFilter = new IntentFilter();
     intentFilter.addAction(Constants.FORGET_ACTION);
     intentFilter.addAction(Constants.REGISTER_ACTION);
     registerReceiver(reciver,intentFilter);

    }
    @Event(R.id.leftView)
    private void back(View view){
        LoginActivity.this.finish();
    }
    //登录成功后进行页面跳转的
    public void loginSuccess(){
      //获取传过来的bundle，判断跳转到哪个页面
        Bundle bundle=getIntent().getExtras();
        //获取传过来的具体值
        String name=bundle.getString(Constants.NAME);
        //我的消息界面
        if(Constants.MY_MESSAGE.equals(name)){
          Intent intent=new Intent();
          Bundle bundle1=new Bundle();
          bundle1.putString(Constants.MY_TYPE,Constants.MY_MESSAGE);
          intent.setClass(LoginActivity.this,MymeaasgeActivity.class);
          intent.putExtras(bundle1);
          startActivity(intent);
          LoginActivity.this.finish();
        }
        //我的收藏
        else if(Constants.MY_COLLECT.equals(name)){
            Intent intent=new Intent();
            Bundle bundle1=new Bundle();
            bundle1.putString(Constants.MY_TYPE,Constants.MY_COLLECT);
            intent.setClass(LoginActivity.this,MyPublishActivity.class);
            intent.putExtras(bundle1);
            startActivity(intent);
            LoginActivity.this.finish();
        }
        //我的发布
        else if(Constants.MY_PUBLISH.equals(name)){
            Intent intent=new Intent();
            Bundle bundle1=new Bundle();
            bundle1.putString(Constants.MY_TYPE,Constants.MY_PUBLISH);
            intent.setClass(LoginActivity.this,MyPublishActivity.class);
            intent.putExtras(bundle1);
            startActivity(intent);
            LoginActivity.this.finish();
        }
        //我的草稿
        else if(Constants.MY_DRAFT.equals(name)){
            Intent intent=new Intent();
            Bundle bundle1=new Bundle();
            bundle1.putString(Constants.MY_TYPE,Constants.MY_DRAFT);
            intent.setClass(LoginActivity.this,MyPublishActivity.class);
            intent.putExtras(bundle1);
            startActivity(intent);
            LoginActivity.this.finish();
        }
        //修改密码
        else if(Constants.UPDATE_PASS.equals(name)){
            startActivity(new Intent(LoginActivity.this,UpdatePassWordPctivity.class));
            LoginActivity.this.finish();
        }
        //收藏
        else if(Constants.Love.equals(name)){
            LoginActivity.this.finish();
        }
        //发布界面
        else if(Constants.PUBLISH.equals(name)){
            LoginActivity.this.finish();
        }else if("my".equals(name)){
            LoginActivity.this.finish();
        }

    }
    //进行数据的保存
    public void saveInfo(String data){
        //保存是否登录
        SPUtils.put(LoginActivity.this,Constants.IS_LOGIN,true);
        //保存用户信息
        SPUtils.put(LoginActivity.this,Constants.USER_INFO,data);
    }
    private class BaseUiListener implements IUiListener {
        @Override
        public void onComplete(Object response) {
            doComplete((JSONObject) response);
        }
        protected void doComplete(JSONObject values) {
            //Log.e("TAG",values.toString());
            try {
                String openID = values.getString("openid");
                String accessToken = values.getString("access_token");
                String expires = values.getString("expires_in");
                mTencent.setOpenId(openID);
                mTencent.setAccessToken(accessToken, expires);
                //Log.e("TAG",mTencent.getOpenId());
                //获取信息
                com.tencent.connect.UserInfo userInfo=new com.tencent.connect.UserInfo(LoginActivity.this,mTencent.getQQToken());
                userInfo.getUserInfo(loginListener);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onError(UiError e) {

        }
        @Override
        public void onCancel() {

        }
    }
    @Override
   protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("tag", "-->onActivityResult " + requestCode  + " resultCode=" + resultCode);
        if (requestCode == com.tencent.connect.common.Constants.REQUEST_LOGIN ||
                requestCode ==com.tencent.connect.common.Constants.REQUEST_APPBAR) {
            Tencent.onActivityResultData(requestCode,resultCode,data,new BaseUiListener());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
   //广播接收者
    private class LoginBroadCast extends BroadcastReceiver{

       @Override
       public void onReceive(Context context, Intent intent) {
           if(intent.getAction().equals(Constants.FORGET_ACTION)){
               LoginActivity.this.finish();
           }else if(intent.getAction().equals(Constants.REGISTER_ACTION)){
               L.e("TAG","接收广播");
               LoginActivity.this.finish();
           }
       }
   }
}
