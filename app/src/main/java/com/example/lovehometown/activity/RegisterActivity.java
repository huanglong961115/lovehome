package com.example.lovehometown.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lovehometown.R;
import com.example.lovehometown.constant.Constants;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

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
    }
    @Event (value={R.id.leftView,R.id.btn_agreement_context2})
    private void back(View view){
        int id=view.getId();
        switch (id){
            case  R.id.leftView:
                RegisterActivity.this.finish();
                break;
            case R.id.btn_agreement_context2:
                Intent intent=new Intent(this, PlatformStatementActivity.class);
                intent.putExtra("service", Constants.SERVICE);
                startActivity(intent);
                this.overridePendingTransition(R.anim.right_in,R.anim.right_out);
                break;
        }
    }
}
