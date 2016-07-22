package com.example.lovehometown.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;

import com.example.lovehometown.R;
import com.example.lovehometown.adapter.GoodsAdapter;
import com.example.lovehometown.adapter.MenuBigAdapter;
import com.example.lovehometown.adapter.MenuSmallAdapter;
import com.example.lovehometown.callback.LoveHomeCallBack;
import com.example.lovehometown.model.BusinessList;
import com.example.lovehometown.model.GoodBigType;
import com.example.lovehometown.model.PublishList;
import com.example.lovehometown.service.HttpService;
import com.example.lovehometown.util.T;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ContentView(R.layout.activity_shop_list)
public class ShopListActivity extends BaseActivity {
    @ViewInject(R.id.layout_shop)
     private LinearLayout layOutShop;
    @ViewInject(R.id.title)
    TextView title;
    @ViewInject(R.id.leftView)
    ImageView back;
    @ViewInject(R.id.rightView2)
    ImageView search;
    @ViewInject(R.id.search_input)
    EditText searchView;
    @ViewInject(R.id.good_listview)
    PullToRefreshListView listView;
    List<BusinessList.PublistBean> data;
    MenuSmallAdapter smallAdapter;
    GoodsAdapter adapter;
    @ViewInject(R.id.shaop_type)
    TextView shopType;
    String shopName="";
    //根据什么查询 0 默认 1 子类 2 商家查询
    int what=0;
    //默认type
    int type=1;
    String childType="";
    int pageSize=1;//当前显示条数
    int page=1;//当前页
    boolean ispushup=false;//是否上拉
        List<GoodBigType> data2;
        List<PublishList.PublistBean> publistBeen;
    ListView lv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }
    @Event(R.id.layout_shop)
    private void showMenu(View view){
      showPopupWindow();
    }
    public void initView(){
        Bundle bundle=getIntent().getExtras();
        String name=bundle.getString("typename");
        type=bundle.getInt("type");
        what=0;
        title.setVisibility(View.VISIBLE);
        title.setText(name);
        shopType.setText(name);
        back.setVisibility(View.VISIBLE);
        search.setVisibility(View.VISIBLE);
        data=new ArrayList<BusinessList.PublistBean>();
        adapter=new GoodsAdapter(data,this);
        listView.setAdapter(adapter);

        initData();
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                ispushup=true;
                page=1;
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String label = df.format(new Date());
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);//设置最后一次更新时间
                refreshView.getLoadingLayoutProxy().setPullLabel("下拉可刷新");//开始刷新显示的文字，

                refreshView.getLoadingLayoutProxy().setRefreshingLabel("正在刷新...");//请求数据过程中显示的文本提示
                refreshView.getLoadingLayoutProxy().setReleaseLabel("松开可加载...");//数据刷新完的文本提示
                initData();
                //getdata();
                //page++;
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                ispushup=false;
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String label = df.format(new Date());
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);//设置最后一次更新时间
                refreshView.getLoadingLayoutProxy().setPullLabel("上拉可加载");//开始刷新显示的文字，

                refreshView.getLoadingLayoutProxy().setRefreshingLabel("正在加载...");//请求数据过程中显示的文本提示
                refreshView.getLoadingLayoutProxy().setReleaseLabel("松开加载更多");//数据刷新完的文本提示
                //HttpService.getInstance().getquestionlist();
                ++page;
                initData();
                //getdata();

            }
        });

    }
    public void initData(){
        if(what==0) {
            HttpService.getHttpService().getBusinessList(type, page, pageSize, callBack);
        }else if(what==1){
            HttpService.getHttpService().getChildBusinessList(childType,page,pageSize,callBack);
        }else if(what==2){
            HttpService.getHttpService().selcetBusinessList(shopName,page,pageSize,callBack);
        }
    }
    //访问网络的方法
    LoveHomeCallBack<String> callBack=new LoveHomeCallBack<String>() {
        @Override
        public void onSuccess(String result) {
            // T.showShort(ShopListActivity.this,result);
            BusinessList businessList= JSON.parseObject(result,BusinessList.class);
            if(businessList.getResults().getCode()!=1){
                T.showShort(ShopListActivity.this,businessList.getResults().getMsg());
                return;
            }else {
                //T.showShort(ShopListActivity.this,result);
                if(businessList.getPublist()==null||businessList.getPublist().size()==0){
                    T.showShort(ShopListActivity.this,"没有新数据，将不会显示新的数据");
                    //return;
                }else{
                    List<BusinessList.PublistBean> list=businessList.getPublist();
                    if(ispushup) {
                        data.clear();
                    }
                    data.addAll(list);
                    adapter.notifyDataSetChanged();
                }
            }
            //完成刷新
            listView.onRefreshComplete();
        }

        @Override
        public void onError(Throwable ex, boolean isOnCallback) {
            T.showShort(ShopListActivity.this,"访问网络失败");
            //完成刷新
            listView.onRefreshComplete();
        }
    };
    @Event(value = R.id.good_listview,type = AdapterView.OnItemClickListener.class)
    private void detail(AdapterView<?> parent, View view, int position, long id){
        Intent intent=new Intent();
        Bundle bundle=new Bundle();
        String json= JSON.toJSONString(data.get(position-1));
        bundle.putString("jsonData",json);
        intent.putExtras(bundle);
        intent.setClass(ShopListActivity.this, DetailsActivity.class);
        startActivity(intent);
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
    @Event(R.id.leftView)
    private void back(View view){
        ShopListActivity.this.finish();
        //退出动画效果
        //overridePendingTransition(R.anim.left_in,R.anim.left_out);
    }
    //查询方法
    @Event(R.id.rightView2)
    private void search(View view){
        title.setVisibility(View.GONE);
       searchView.setVisibility(View.VISIBLE);
        shopName=searchView.getText().toString().trim();
        if(!shopName.equals("")){
            what=2;
            initData();
        }
    }
    @Override
    public void onBackPressed() {
        ShopListActivity.this.finish();
        //退出动画效果
       // overridePendingTransition(R.anim.left_in,R.anim.left_out);
    }
    public void showPopupWindow(){
        View v= LayoutInflater.from(this).inflate(R.layout.popwindow_shoplist, null);
        Log.e("TAG","showPopupWindow");
        ListView lv1 = (ListView) v.findViewById(R.id.dialog_list);
        lv2 = (ListView) v.findViewById(R.id.dialog_list2);

        data2 = new ArrayList<>();
        publistBeen=new ArrayList<>();
        data2.add(new GoodBigType(1,"美食",R.drawable.meishi));
        data2.add(new GoodBigType(2,"娱乐",R.drawable.yule));
        data2.add(new GoodBigType(3,"房产",R.drawable.fangchan));
        data2.add(new GoodBigType(4,"车",R.drawable.che));
        data2.add(new GoodBigType(5,"婚庆",R.drawable.hunqing));
        data2.add(new GoodBigType(6,"装修",R.drawable.zhuangxiu));
        data2.add(new GoodBigType(7,"教育",R.drawable.jiaoyu));
        data2.add(new GoodBigType(8,"工作",R.drawable.gongzuo));
        data2.add(new GoodBigType(9,"百货",R.drawable.baihuo));
        data2.add(new GoodBigType(10,"跳蚤",R.drawable.tiaozhao));
        data2.add(new GoodBigType(11,"商务",R.drawable.shangwu));
        data2.add(new GoodBigType(12,"便民",R.drawable.bianmin));
        data2.add(new GoodBigType(13,"老乡会",R.drawable.laoxianghui));
        data2.add(new GoodBigType(14,"其他",R.drawable.qita));


        lv1.setAdapter(new MenuBigAdapter(ShopListActivity.this,data2));
        smallAdapter=new MenuSmallAdapter(ShopListActivity.this,publistBeen);
        lv2.setAdapter(smallAdapter);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //T.showShort(ShopListActivity.this,"哈哈哈");
                initMenuData(data2.get(position).getId());
                //Log.e("TAG","position"+position+"--"+maps.get(data2.get(position)).toString());
                //lv2.setAdapter(new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_expandable_list_item_1,maps.get(data.get(position))));
            }
        });
        PopupWindow p=new PopupWindow(v, LinearLayout.LayoutParams.MATCH_PARENT,  LinearLayout.LayoutParams.WRAP_CONTENT,true);
        final PopupWindow _p=p;
        p.setFocusable(true);
        p.setOutsideTouchable(true);
        v.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int popupWidth = v.getMeasuredWidth();
        int popupHeight = v.getMeasuredHeight();
        // p.setBackgroundDrawable();
        p.setBackgroundDrawable(new BitmapDrawable());
//     p.getBackground().setAlpha(100);
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        //TextView b= (TextView) findViewById(R.id.v);
        // v.getLocationOnScreen(location);
        p.showAsDropDown(layOutShop);
        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                T.showShort(ShopListActivity.this,publistBeen.get(position).getReleaseList().getPublishName());
                what=1;
                childType=publistBeen.get(position).getReleaseList().getPublishTypename();
                title.setVisibility(View.VISIBLE);
                searchView.setVisibility(View.GONE);
                title.setText(publistBeen.get(position).getReleaseList().getPublishName());
                shopType.setText(publistBeen.get(position).getReleaseList().getPublishName());
                initData();
                _p.dismiss();
                //Toast.makeText(MainActivity.this,position+"",Toast.LENGTH_SHORT).show();
            }
        });

        // p.showAtLocation(v,Gravity.NO_GRAVITY, (location[0] + v.getWidth() / 2) - popupWidth / 2, location[1] - popupHeight);
    }
    //
    public void initMenuData(int type){
      HttpService.getHttpService().getPublishList(type, new LoveHomeCallBack<String>() {
          @Override
          public void onSuccess(String result) {
              //T.showShort(ShopListActivity.this,result+"cccc");
              PublishList publishList=JSON.parseObject(result,PublishList.class);
              if(publishList.getResults().getCode()!=1){
                  T.showShort(ShopListActivity.this,"获取详细分类失败");
                  return;
              }
              publistBeen.clear();
              publistBeen.addAll(publishList.getPublist());
              smallAdapter.notifyDataSetChanged();
          }

          @Override
          public void onError(Throwable ex, boolean isOnCallback) {
              T.showShort(ShopListActivity.this,"网络连接失败,获取详细分类失败");
          }
      });
    }
}
