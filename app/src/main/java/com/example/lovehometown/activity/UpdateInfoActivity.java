package com.example.lovehometown.activity;

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
import com.example.lovehometown.model.UpdateInfo;
import com.example.lovehometown.model.UserInfo;
import com.example.lovehometown.service.HttpService;
import com.example.lovehometown.util.SPUtils;
import com.example.lovehometown.util.T;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_update_info)
public class UpdateInfoActivity extends BaseActivity {
    @ViewInject(R.id.txt_tips)
   TextView tips;
    @ViewInject(R.id.edt_data)
    EditText info;
    @ViewInject(R.id.title)
    TextView title;
    @ViewInject(R.id.leftView)
    ImageView backImg;
    @ViewInject(R.id.btn_ensure_edt)
    Button updateButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      initView();
    }

    private void initView() {
        title.setVisibility(View.VISIBLE);
        backImg.setVisibility(View.VISIBLE);
        Bundle bundle=getIntent().getExtras();
        String attr=bundle.getString(Constants.INFO_ATTR);
        String content=bundle.getString("content");
        info.setText(content);
        //修改用户名称
        if(attr.equals(Constants.USERNMAE)){
            title.setText("修改用户名称");
            tips.setText("用户名称：");
            info.setHint("请填写要修改的用户名称");

        }else if(attr.equals(Constants.USER_PHONE)){

                title.setText("修改联系方式");
                tips.setText("联系方式：");
                info.setHint("请填写要修改的联系方式");

        }else if(attr.equals(Constants.USER_ADDRESS)){
            title.setText("修改联系地址");
            tips.setText("联系地址：");
            info.setHint("请填写要修改的联系地址");
        }
    }
    @Event(R.id.leftView)
    private void back(View view){
        UpdateInfoActivity.this.finish();
        //退出动画效果
        overridePendingTransition(R.anim.left_in,R.anim.left_out);
    }
   //
    @Event(R.id.btn_ensure_edt)
    private void updateInfo(View view){
        //首先判断是是否为空
        String type=info.getText().toString();

        if(type==null||"".equals(type.trim())){
            T.showShort(UpdateInfoActivity.this,"修改信息不能为空");
            return;
        }
        final String _type=type;
        Bundle bundle=getIntent().getExtras();
        String attr=bundle.getString(Constants.INFO_ATTR);
        String url="";
        String key="";
        String userInfo= (String) SPUtils.get(UpdateInfoActivity.this,Constants.USER_INFO,"");
        if (userInfo.equals("")){

        }else {
            UserInfo.UserBean userBean= JSON.parseObject(userInfo, UserInfo.UserBean.class);
            String phone=userBean.getPhoneNuber();
            //修改用户名称
            if (attr.equals(Constants.USERNMAE)) {
                url = Constants.UPDATE_USERNAME;
                key = "userName";

            } else if (attr.equals(Constants.USER_PHONE)) {
                url = Constants.UPDATE_USER_CONSTANT;
                key = "userContast";


            } else if (attr.equals(Constants.USER_ADDRESS)) {
                url = Constants.UPDATE_USER_ADDRESS;
                key = "userAddress";
            }
            HttpService.getHttpService().updateInfo(url, key, phone, type, new LoveHomeCallBack<String>() {
                @Override
                public void onSuccess(String result) {
                    //
                    UpdateInfo updateInfo=JSON.parseObject(result,UpdateInfo.class);
                    if(updateInfo.getResults().getCode()!=1){
                        T.showShort(UpdateInfoActivity.this,updateInfo.getResults().getMsg());
                        return;
                    }else{
                        Bundle bundle=getIntent().getExtras();
                        String attr=bundle.getString(Constants.INFO_ATTR);
                        String userInfo= (String) SPUtils.get(UpdateInfoActivity.this,Constants.USER_INFO,"");
                         UserInfo.UserBean userBean= JSON.parseObject(userInfo, UserInfo.UserBean.class);
                        if (attr.equals(Constants.USERNMAE)) {
                           userBean.setUsername(_type);
                        } else if (attr.equals(Constants.USER_PHONE)) {
                            userBean.setUserContast(_type);

                        } else if (attr.equals(Constants.USER_ADDRESS)) {
                            userBean.setUserAddress(_type);
                        }
                        String json=JSON.toJSONString(userBean);
                        SPUtils.put(UpdateInfoActivity.this,Constants.USER_INFO,json);
                        UpdateInfoActivity.this.finish();
                    }
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    T.showShort(UpdateInfoActivity.this,"网络连接失败");
                }
            });
        }
    }
    @Override
    public void onBackPressed() {
        UpdateInfoActivity.this.finish();
        //退出动画效果
        overridePendingTransition(R.anim.left_in,R.anim.left_out);
    }
}
