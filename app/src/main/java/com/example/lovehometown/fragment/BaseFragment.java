package com.example.lovehometown.fragment;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xutils.x;

/**
 * Created by Administrator on 2016/7/16.
 */
public class BaseFragment extends Fragment{
    private boolean isInject=false;//判断是否注入

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        isInject=true;
        //注入
        return x.view().inject(this,inflater,container);
    }

    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(!isInject) {
            x.view().inject(this,this.getView());
        }
    }
}
