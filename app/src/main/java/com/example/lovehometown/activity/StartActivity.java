package com.example.lovehometown.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lovehometown.R;

import org.xutils.view.annotation.ContentView;

@ContentView(R.layout.activity_start)
public class StartActivity extends BaseActivity {
   ViewPager imgViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_start);
    }
}
