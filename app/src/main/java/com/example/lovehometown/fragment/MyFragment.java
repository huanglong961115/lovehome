package com.example.lovehometown.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lovehometown.R;
import com.example.lovehometown.activity.AboutLoveHomeActivity;
import com.example.lovehometown.activity.MyPublishActivity;
import com.example.lovehometown.activity.MymeaasgeActivity;
import com.example.lovehometown.activity.PlatformStatementActivity;
import com.example.lovehometown.activity.UpdatePassWordPctivity;
import com.example.lovehometown.constant.Constants;
import com.example.lovehometown.customview.CustomDialog;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by Administrator on 2016/7/16.
 */
@ContentView(R.layout.my_layout)
public class MyFragment extends BaseFragment {
    @ViewInject(R.id.platformStatement)
  RelativeLayout platformStatement;
    @ViewInject(R.id.aboutLoveHome)
    RelativeLayout aboutLoveHome;
    @ViewInject(R.id.updatePassword)
    RelativeLayout updatePassword;
    @ViewInject(R.id.mypublish)
    RelativeLayout myPublish;
    @ViewInject(R.id.myDraft)
    RelativeLayout myDraft;
    @ViewInject(R.id.mycollect)
    RelativeLayout myCollect;
    @ViewInject(R.id.mymessage)
    RelativeLayout myMessage;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    @Event(value = {R.id.platformStatement,R.id.aboutLoveHome,R.id.updatePassword,R.id.mymessage,R.id.mypublish,R.id.myDraft,R.id.mycollect})
    private void click(View view){
        int id=view.getId();
        switch (id){
            //我的发布
            case R.id.mypublish:
              myChange(MyPublishActivity.class,Constants.MY_PUBLISH);
                break;
            //我的草稿
            case R.id.myDraft:
                myChange(MyPublishActivity.class,Constants.MY_DRAFT);
                break;
            //我的收藏
            case R.id.mycollect:
                myChange(MyPublishActivity.class,Constants.MY_COLLECT);
                break;
            //我的消息
            case R.id.mymessage:
                myChange(MymeaasgeActivity.class,Constants.MY_MESSAGE);
                break;
            //平台声明
            case  R.id.platformStatement:
                startActivity(new Intent(getActivity(), PlatformStatementActivity.class));
                break;
            //关于爱家乡
            case R.id.aboutLoveHome:
                startActivity(new Intent(getActivity(), AboutLoveHomeActivity.class));
                break;
            //修改密码
            case R.id.updatePassword:
                startActivity(new Intent(getActivity(), UpdatePassWordPctivity.class));
                break;

        }
    }

    /**
     * 页面跳转
     * @param clazz 跳转的类
     * @param type 传递过去的参数
     */
    public void myChange(Class clazz,String type){
        Bundle bundle=new Bundle();
        bundle.putString(Constants.MY_TYPE,type);
        Intent intent=new Intent();
        intent.putExtra(Constants.TYPE,bundle);
        intent.setClass(getActivity(), clazz);
        startActivity(intent);
    }
}
