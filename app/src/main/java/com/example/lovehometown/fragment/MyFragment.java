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
import com.example.lovehometown.activity.PlatformStatementActivity;

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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    @Event(value = {R.id.platformStatement,R.id.aboutLoveHome})
    private void click(View view){
        int id=view.getId();
        switch (id){
            case  R.id.platformStatement:
                startActivity(new Intent(getActivity(), PlatformStatementActivity.class));
                break;
            case R.id.aboutLoveHome:
                startActivity(new Intent(getActivity(), AboutLoveHomeActivity.class));
                break;
        }
    }
}
