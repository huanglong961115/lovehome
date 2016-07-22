package com.example.lovehometown.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.lovehometown.R;
import com.example.lovehometown.activity.DetailsActivity;
import com.example.lovehometown.activity.LoginActivity;
import com.example.lovehometown.activity.MymeaasgeActivity;
import com.example.lovehometown.activity.ShopListActivity;
import com.example.lovehometown.adapter.GoodsAdapter;
import com.example.lovehometown.adapter.ImgGridViewAdapter;
import com.example.lovehometown.adapter.PictureViewAdapter;
import com.example.lovehometown.callback.LoveHomeCallBack;
import com.example.lovehometown.common.Login;
import com.example.lovehometown.constant.Constants;
import com.example.lovehometown.customview.CustomGridView;
import com.example.lovehometown.customview.CustomListView;
import com.example.lovehometown.customview.CustomProgressDialog;
import com.example.lovehometown.model.BusinessList;
import com.example.lovehometown.model.GoodBigType;
import com.example.lovehometown.model.ShopInfo;
import com.example.lovehometown.service.HttpService;
import com.example.lovehometown.util.L;
import com.example.lovehometown.util.T;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
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
    @ViewInject(R.id.webView)
    WebView webView;
    @ViewInject(R.id.serach_text)
    TextView serach;
    @ViewInject(R.id.message)
    ImageView message;
    //加载动画效果
    CustomProgressDialog dialog;
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //得到headView
        setHeadView();
        initView();
        //数据初始化
        initData();
    }

    /**
     * 设置ViewPager
     */
    public void setHeadView(){
        //加载地址
        webView.loadUrl(Constants.IMG_WEB_URL);
        //加载webView页面的监听
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                dialog=new CustomProgressDialog(getActivity(),"加载中...",R.drawable.load_anim);
                //dialog.set
                dialog.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                dialog.dismiss();
            }
        });

        WebSettings webSettings = webView.getSettings();
        //设置支持javascript
        webSettings.setJavaScriptEnabled(true);
        //设置无缓存
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        //dialog.dismiss();
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
    public CustomGridView getGridView( List<GoodBigType> list){
        final List<GoodBigType> _list=list;
        ImgGridViewAdapter adapter1=new ImgGridViewAdapter(getActivity(),list);
        CustomGridView gridView1=new CustomGridView(getActivity());
        gridView1.setNumColumns(4);
        gridView1.setSelector(R.color.transparent);
        gridView1.setVerticalSpacing(20);
        gridView1.setPadding(0,20,0,0);
        gridView1.setAdapter(adapter1);
        //点击事件进行页面跳转
        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //传递参数过去
                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                bundle.putString("typename",_list.get(position).getName());
                bundle.putInt("type",_list.get(position).getId());
                intent.putExtras(bundle);
                intent.setClass(getActivity(),ShopListActivity.class);
                startActivity(intent);
            }
        });
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
    List<BusinessList.PublistBean> list=new ArrayList<BusinessList.PublistBean>();
    public void initView(){
        //dialog.dismiss();

        //int id, int shopImage, String name, String price, String time, String phone, String info, String linkMan, String address, String type
       // list.add(new ShopInfo(1,R.drawable.che,"龙哥大酒店","50元/人","10:00-12:00","15116346673","欢迎光临","黄先生","长沙市望城区","酒店"));
        adapter=new GoodsAdapter(list,getActivity());
        indexView.setAdapter(adapter);

    }
    @Event(value = R.id.indexListView,type = AdapterView.OnItemClickListener.class)
    private void detail(AdapterView<?> parent, View view, int position, long id){
        Intent intent=new Intent();
        Bundle bundle=new Bundle();
        String json= JSON.toJSONString(list.get(position));
        bundle.putString("jsonData",json);
        intent.putExtras(bundle);
        intent.setClass(getActivity(), DetailsActivity.class);
      startActivity(intent);
    }
    @Event(R.id.serach_text)
    private void serach(View view){
        startActivity(new Intent(getActivity(), ShopListActivity.class));
    }
    @Event(R.id.message)
    private void message(View view){

        boolean isLogin= Login.getInstance().isLogin(getActivity());
        if(isLogin){
            startActivity(new Intent(getActivity(), MymeaasgeActivity.class));
        }else{
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }
    }
    public void initData(){
        HttpService.getHttpService().getBusinessList(1, 1, 5, new LoveHomeCallBack<String>() {
            @Override
            public void onSuccess(String result) {
               // T.showShort(getActivity(),result);
                BusinessList businessList=JSON.parseObject(result,BusinessList.class);
                if(businessList.getResults().getCode()!=1){
                    T.showShort(getActivity(),businessList.getResults().getMsg());
                }else {
                    list.clear();
                    list.addAll(businessList.getPublist());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                  T.showShort(getActivity(),"访问网络失败");
            }
        });
    }
}