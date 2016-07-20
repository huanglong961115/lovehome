package com.example.lovehometown.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lovehometown.R;
import com.example.lovehometown.constant.Constants;

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

    @Override
    public void onBackPressed() {
        UpdateInfoActivity.this.finish();
        //退出动画效果
        overridePendingTransition(R.anim.left_in,R.anim.left_out);
    }
}
