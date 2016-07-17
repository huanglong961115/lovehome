package com.example.lovehometown.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.lovehometown.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by Administrator on 2016/7/16.
 */
@ContentView(R.layout.home_layout)
public class HomeFragment extends BaseFragment{
    View headView;
    @ViewInject(R.id.indexListView)
    ListView indexView;
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //得到headView
       // headView=LayoutInflater.from(getActivity()).inflate(R.layout.index_list_head,null);
    }

    /**
     * 设置ViewPager
     */
    public void setViewPager(){

    };

    /**
     * 添加gridView
     */
    public void addGridView(){

    }
}
