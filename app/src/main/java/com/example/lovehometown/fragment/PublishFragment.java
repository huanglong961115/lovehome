package com.example.lovehometown.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lovehometown.R;
import com.example.lovehometown.adapter.ImgGridViewAdapter;
import com.example.lovehometown.adapter.PublishCateGrotAdapter;
import com.example.lovehometown.customview.CustomDialog;
import com.example.lovehometown.customview.PublishDialog;
import com.example.lovehometown.model.GoodBigType;
import com.example.lovehometown.util.T;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/16.
 */
@ContentView(R.layout.publish_layout)
public class PublishFragment extends BaseFragment{
    @ViewInject(R.id.publish_gridView)
   GridView publishGridView;
    @ViewInject(R.id.title)
    TextView title;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
       // T.showShort(getActivity(),"进来了");
        super.onViewCreated(view, savedInstanceState);
        initView();
    }
    public List<GoodBigType> initData(){
        List<GoodBigType> list=new ArrayList<GoodBigType>();
        list.add(new GoodBigType(1,"美食",R.drawable.meishi));
        list.add(new GoodBigType(2,"娱乐",R.drawable.yule));
        list.add(new GoodBigType(3,"房产",R.drawable.fangchan));
        list.add(new GoodBigType(4,"车",R.drawable.che));
        list.add(new GoodBigType(5,"婚庆",R.drawable.hunqing));
        list.add(new GoodBigType(6,"装修",R.drawable.zhuangxiu));
        list.add(new GoodBigType(7,"教育",R.drawable.jiaoyu));
        list.add(new GoodBigType(8,"工作",R.drawable.gongzuo));
        list.add(new GoodBigType(9,"百货",R.drawable.baihuo));
        list.add(new GoodBigType(10,"跳蚤",R.drawable.tiaozhao));
        list.add(new GoodBigType(11,"商务",R.drawable.shangwu));
        list.add(new GoodBigType(12,"便民",R.drawable.bianmin));
        list.add(new GoodBigType(13,"老乡会",R.drawable.laoxianghui));
        list.add(new GoodBigType(14,"其他",R.drawable.qita));
        return  list;
    }
    public void initView(){

        title.setVisibility(View.VISIBLE);
        title.setText("选择发布类型");

        List<GoodBigType> list=initData();
        ImgGridViewAdapter adapter=new ImgGridViewAdapter(getActivity(),list);
        publishGridView.setAdapter(adapter);
        /**
         * 点击事件
         */
        publishGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                   List<String> list=new ArrayList<String>();
                list.add("酒店");
                list.add("饭店");
                list.add("西点");
                list.add("夜宵");
                list.add("外卖");
                View view1=LayoutInflater.from(getActivity()).inflate(R.layout.publish_dialog_list,null);
                ListView lv= (ListView) view1.findViewById(R.id.dialog_list);
                PublishDialog.Builder builder=new PublishDialog.Builder(getActivity());
                ImageView imageView=new ImageView(getActivity());
                imageView.setImageResource(R.drawable.error);
                imageView.setPadding(0,20,0,20);
                lv.addFooterView(imageView);
                lv.setAdapter(new PublishCateGrotAdapter(getActivity(),list));

                builder.setContentView(view1);
                 PublishDialog dialog=builder.create();
                final  PublishDialog _dialog=dialog;
                Window window = dialog.getWindow();
                dialog.show();
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        _dialog.dismiss();
                    }
                });
                //设置window显示的位置
                window.setGravity(Gravity.BOTTOM);


            }
        });

    }


}
