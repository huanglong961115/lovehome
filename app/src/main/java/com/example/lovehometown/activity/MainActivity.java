package com.example.lovehometown.activity;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.lovehometown.adapter.Viewadapter;
import com.example.lovehometown.customview.CustomViewPager;
import com.example.lovehometown.fragment.HomeFragment;
import com.example.lovehometown.fragment.MyFragment;
import com.example.lovehometown.fragment.PublishFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;
@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    /**
     *爱家乡导航文本框
     */
    @ViewInject(R.id.hometext)
    TextView homeText;
    /**
     *发布导航文本框
     */
    @ViewInject(R.id.publishtext)
    TextView publishText;
    /**
     * 我的页导航文本框
     */
    @ViewInject(R.id.mytext)
    TextView myText;

    /**
     * 爱家乡导航图片
     */
    @ViewInject(R.id.homeimg)
    ImageView homeImg;
    /**
     * 发布导航图片
     */
    @ViewInject(R.id.publishimg)
    ImageView publishImg;
    /**
     * 我的导航图片
     */
    @ViewInject(R.id.myimg)
    ImageView myImg;
    /**
     * viewpager
     */
    @ViewInject(R.id.view)
    CustomViewPager viewpager;
    /**
     * 碎片list,用来给viewpager添加适配器
     */
    List<Fragment> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //调用初始化适配数据方法
        initfragmentlistdata();
        //调用初始化viewpager数据
        initviewpagerdata();

    }
   @Event(value={R.id.rehome,R.id.remy,R.id.republish})
    private void onPageChange(View view) {

        int id = view.getId();
        // 点击事件的时候，所有文本颜色为未选中颜色

        switch (id) {
            // 首页
            case R.id.rehome:
                homeSelected();
                viewpager.setCurrentItem(0);

                break;
            //搜索
            case R.id.republish:
                publishSelecteddata();
                viewpager.setCurrentItem(1);

                break;
            //分类
            case R.id.remy:
                mySelected();
                viewpager.setCurrentItem(2);

                break;

            default:
                break;
        }
    }

    /**
     * 初始化list数据
     */
    public void initfragmentlistdata(){
        list=new ArrayList<Fragment>();
        /**
         * 首页
         */
       HomeFragment homeView=new HomeFragment();
        list.add(homeView);
        /**
         * 发布
         */
       PublishFragment publishView=new PublishFragment();
        list.add(publishView);
        /**
         * 我的
         */
       MyFragment myView=new MyFragment();
        list.add(myView);

    }
    /**
     * 在发生改变时全部置为没有选择的状态数据
     */
    public void noSelected(){
        homeText.setTextColor(Color.parseColor("#cccccc"));
        publishText.setTextColor(Color.parseColor("#cccccc"));
        myText.setTextColor(Color.parseColor("#cccccc"));


       homeImg.setImageResource(R.drawable.home);
       publishImg.setImageResource(R.drawable.publish);
        myImg.setImageResource(R.drawable.wode);

    }
    /**
     * 选中首页
     */
    public void homeSelected(){
        noSelected();
       homeImg.setImageResource(R.drawable.home_press);
       homeText.setTextColor(Color.parseColor("#50C476"));

    }
    /**
     * 选中发布
     */
    public void publishSelecteddata(){
        noSelected();
        publishImg.setImageResource(R.drawable.publish_press);
        publishText.setTextColor(Color.parseColor("#50C476"));

    }
    /**
     * 选中我的
     */
    public void mySelected(){
        noSelected();
        myImg.setImageResource(R.drawable.wode_press);
        myText.setTextColor(Color.parseColor("#50C476"));

    }

    /**
     * 初始化viewpager的数据
     */
    public void initviewpagerdata() {
        Viewadapter adapter = new Viewadapter(getSupportFragmentManager(), list);
        viewpager.setAdapter(adapter);
        viewpager.setScrollble(false);

    }
}
