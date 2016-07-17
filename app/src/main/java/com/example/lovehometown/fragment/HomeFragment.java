package com.example.lovehometown.fragment;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.lovehometown.R;
import com.example.lovehometown.adapter.GoodsAdapter;
import com.example.lovehometown.adapter.ImgGridViewAdapter;
import com.example.lovehometown.adapter.PictureViewAdapter;
import com.example.lovehometown.customview.CustomGridView;
import com.example.lovehometown.customview.CustomListView;
import com.example.lovehometown.model.GoodBigType;
import com.example.lovehometown.util.T;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/16.
 */
@ContentView(R.layout.home_layout)
public class HomeFragment extends BaseFragment{

    @ViewInject(R.id.indexListView)
    CustomListView indexView;
    GoodsAdapter adapter;
    @ViewInject(R.id.headViewPager)
    ViewPager viewPager;
    @ViewInject(R.id.index1)
   ImageView indexImg1;
    @ViewInject(R.id.index2)
   ImageView indexImg2;
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //得到headView
        setHeadView();
        initView();
    }

    /**
     * 设置ViewPager
     */
    public void setHeadView(){
        List<View> list=addGridView();
        PictureViewAdapter viewAdapter=new PictureViewAdapter(list);
        //T.showShort(getActivity(),list.size()+"hhh");

        viewPager.setAdapter(viewAdapter);
        //设置滑动页面的监听
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //滑动到第一页
                 if(position==0){
                     indexImg1.setImageResource(R.drawable.green_point);
                     indexImg2.setImageResource(R.drawable.hen_point);
                 }else{
                     indexImg2.setImageResource(R.drawable.green_point);
                     indexImg1.setImageResource(R.drawable.hen_point);
                 }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 添加gridView
     */
    public List<View> addGridView(){
        List<View> viewList=new ArrayList<View>();
        //第一页

        viewList.add(getGridView(initFirstData()));
        //第二页

        viewList.add(getGridView(initSecondData()));
        return viewList;
    }
    public CustomGridView getGridView(List<GoodBigType> list){
        ImgGridViewAdapter adapter1=new ImgGridViewAdapter(getActivity(),list);
        CustomGridView gridView1=new CustomGridView(getActivity());
        gridView1.setNumColumns(4);
        gridView1.setSelector(R.color.transparent);
        gridView1.setVerticalSpacing(20);
        gridView1.setPadding(0,20,0,0);
        gridView1.setAdapter(adapter1);
        return  gridView1;
    }
    //初始化化第一个页面的数据
    public List<GoodBigType> initFirstData(){
        List<GoodBigType> list=new ArrayList<GoodBigType>();
        list.add(new GoodBigType(1,"美食",R.drawable.meishi));
        list.add(new GoodBigType(2,"娱乐",R.drawable.yule));
        list.add(new GoodBigType(3,"房产",R.drawable.fangchan));
        list.add(new GoodBigType(4,"车",R.drawable.che));
        list.add(new GoodBigType(5,"婚庆",R.drawable.hunqing));
        list.add(new GoodBigType(6,"装修",R.drawable.zhuangxiu));
        list.add(new GoodBigType(7,"教育",R.drawable.jiaoyu));
        list.add(new GoodBigType(8,"工作",R.drawable.gongzuo));
        return  list;
    }
    public List<GoodBigType> initSecondData(){
        List<GoodBigType> list=new ArrayList<GoodBigType>();
        list.add(new GoodBigType(9,"百货",R.drawable.baihuo));
        list.add(new GoodBigType(10,"跳蚤",R.drawable.tiaozhao));
        list.add(new GoodBigType(11,"商务",R.drawable.shangwu));
        list.add(new GoodBigType(12,"便民",R.drawable.bianmin));
        list.add(new GoodBigType(13,"老乡会",R.drawable.laoxianghui));
        list.add(new GoodBigType(14,"其他",R.drawable.qita));
        return  list;
    }
    public void initView(){
        adapter=new GoodsAdapter();
        indexView.setAdapter(adapter);
    }
}
