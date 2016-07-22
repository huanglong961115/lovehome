package com.example.lovehometown.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;

import com.example.lovehometown.R;
import com.example.lovehometown.adapter.GoodsAdapter;
import com.example.lovehometown.callback.LoveHomeCallBack;
import com.example.lovehometown.model.BusinessList;
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
import java.util.List;

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
    GoodsAdapter adapter;
    int pageSize=1;//当前显示条数
    int page=1;//当前页
    boolean ispushup=false;//是否上拉
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }
    @Event(R.id.layout_shop)
    private void showMenu(View view){
      T.showShort(ShopListActivity.this,"hhhh");
    }
    public void initView(){
        Bundle bundle=getIntent().getExtras();
        String name=bundle.getString("typename");
        int type=bundle.getInt("type");
        final int _type=type;
        title.setVisibility(View.VISIBLE);
        title.setText(name);
        back.setVisibility(View.VISIBLE);
        search.setVisibility(View.VISIBLE);
        data=new ArrayList<BusinessList.PublistBean>();
        adapter=new GoodsAdapter(data,this);
        listView.setAdapter(adapter);

        initData(type);
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
                initData(_type);
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
                initData(_type);
                //getdata();

            }
        });

    }
    public void initData(int type){
        HttpService.getHttpService().getBusinessList(type, page, pageSize, "", new LoveHomeCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                T.showShort(ShopListActivity.this,result);
                BusinessList businessList= JSON.parseObject(result,BusinessList.class);
                if(businessList.getResults().getCode()!=1){
                    T.showShort(ShopListActivity.this,businessList.getResults().getMsg());
                    return;
                }else {
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
            }
        });
    }
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

    @Override
    public void onBackPressed() {
        ShopListActivity.this.finish();
        //退出动画效果
       // overridePendingTransition(R.anim.left_in,R.anim.left_out);
    }
}
