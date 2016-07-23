package com.example.lovehometown.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.lovehometown.R;
import com.example.lovehometown.adapter.LoveAdapter;
import com.example.lovehometown.adapter.PublishAdapter;
import com.example.lovehometown.constant.Constants;
import com.example.lovehometown.model.UserInfo;
import com.example.lovehometown.service.DBService;
import com.example.lovehometown.util.L;
import com.example.lovehometown.util.SPUtils;
import com.example.lovehometown.util.T;
import com.example.lovehometown.vo.Love;
import com.example.lovehometown.vo.Publish;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_my_publish)
public class MyPublishActivity extends BaseActivity {
    @ViewInject(R.id.title)
    TextView title;
    @ViewInject(R.id.leftView)
    ImageView backImg;
    @ViewInject(R.id.listview_release)
    PullToRefreshListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        title.setVisibility(View.VISIBLE);
        backImg.setVisibility(View.VISIBLE);
        Bundle bundle=getIntent().getExtras();
        String type=bundle.getString(Constants.MY_TYPE);
        if(type.equals(Constants.MY_PUBLISH)){
            title.setText("我的发布");
            String userInfo= (String) SPUtils.get(MyPublishActivity.this,Constants.USER_INFO,"");
            if (userInfo.equals("")){
            }else{
                UserInfo.UserBean userBean = JSON.parseObject(userInfo, UserInfo.UserBean.class);
                List<Publish> publishs=new ArrayList<>();
                PublishAdapter publishAdapter=new PublishAdapter(MyPublishActivity.this,publishs);
                listView.setAdapter(publishAdapter);
                try {
                    publishs.clear();
                    if(DBService.getInstance().selectPublish(userBean.getPhoneNuber(),1)!=null&&DBService.getInstance().selectPublish(userBean.getPhoneNuber(),1).size()>0){
                        publishs.addAll(DBService.getInstance().selectPublish(userBean.getPhoneNuber(),1));
                    }

                    publishAdapter.notifyDataSetChanged();
                } catch (DbException e) {
                    e.printStackTrace();
                }
            }

        }else if(type.equals(Constants.MY_COLLECT)){
            title.setText("我的收藏");
            String userInfo= (String) SPUtils.get(MyPublishActivity.this,Constants.USER_INFO,"");
            if (userInfo.equals("")){
            }else {
                UserInfo.UserBean userBean = JSON.parseObject(userInfo, UserInfo.UserBean.class);
                List<Love> list=new ArrayList<>();
                LoveAdapter loveAdapter = new LoveAdapter(MyPublishActivity.this,list);
                listView.setAdapter(loveAdapter);
                try {
                    list.clear();
                    list.addAll(DBService.getInstance().selectLove(userBean.getPhoneNuber()));
                    loveAdapter.notifyDataSetChanged();
                    final List<Love> _list=list;
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent=new Intent();
                            Bundle bundle=new Bundle();
                            String json= JSON.toJSONString(_list.get(position-1));
                            bundle.putString("jsonData",json);
                            intent.putExtras(bundle);
                            intent.setClass(MyPublishActivity.this, DetailsActivity.class);
                            startActivity(intent);
                        }
                    });
                } catch (DbException e) {
                    e.printStackTrace();
                }
            }
        }else if(type.equals(Constants.MY_DRAFT)){
            title.setText("我的草稿");
            String userInfo= (String) SPUtils.get(MyPublishActivity.this,Constants.USER_INFO,"");
            if (userInfo.equals("")){
            }else{
                UserInfo.UserBean userBean = JSON.parseObject(userInfo, UserInfo.UserBean.class);
                List<Publish> publishs=new ArrayList<>();
                PublishAdapter publishAdapter=new PublishAdapter(MyPublishActivity.this,publishs);
                listView.setAdapter(publishAdapter);
                try {
                    publishs.clear();
                    if(DBService.getInstance().selectPublish(userBean.getPhoneNuber(),0)!=null&&DBService.getInstance().selectPublish(userBean.getPhoneNuber(),0).size()>0){
                        publishs.addAll(DBService.getInstance().selectPublish(userBean.getPhoneNuber(),0));
                    }
                    publishAdapter.notifyDataSetChanged();
                } catch (DbException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @Event(R.id.leftView)
    private void back(View view){
        MyPublishActivity.this.finish();
        //退出动画效果
        overridePendingTransition(R.anim.left_in,R.anim.left_out);
    }

    @Override
    public void onBackPressed() {
        MyPublishActivity.this.finish();
        //退出动画效果
        overridePendingTransition(R.anim.left_in,R.anim.left_out);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initView();
    }
}
