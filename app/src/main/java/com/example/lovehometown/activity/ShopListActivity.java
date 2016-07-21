package com.example.lovehometown.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.LinearLayout;

import com.example.lovehometown.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_shop_list)
public class ShopListActivity extends BaseActivity {
    @ViewInject(R.id.layout_shop)
     private LinearLayout allshop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }
    public void initView(){

    }
    @Event(R.id.layout_shop)
    private void click(View view){
        int id=view.getId();
        switch (id){
            case R.id.layout_shop:
                break;
            default:
                break;
        }
    }
}
