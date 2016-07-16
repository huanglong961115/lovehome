package com.example.lovehometown.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lovehometown.constant.Constants;
import com.example.lovehometown.util.SPUtils;

public class AppLuancherAtivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //判断是否第一次加载应用，第一次出现引导页

     boolean isFirst= (boolean) SPUtils.get(this, Constants.IS_FIRST,true);
        if(isFirst){
            startActivity(new Intent(this,GuideActivity.class));
            SPUtils.put(this,Constants.IS_FIRST,false);
        }else{
            //不是第一次启动
             startActivity(new Intent(this,StartActivity.class));
        }
        AppLuancherAtivity.this.finish();
    }
}
