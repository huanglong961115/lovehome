package com.example.lovehometown.fragment;

import android.content.Intent;
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
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.lovehometown.R;
import com.example.lovehometown.activity.AddPublishActivity;
import com.example.lovehometown.adapter.ImgGridViewAdapter;
import com.example.lovehometown.adapter.PictureViewAdapter;
import com.example.lovehometown.adapter.PublishCateGrotAdapter;
import com.example.lovehometown.callback.LoveHomeCallBack;
import com.example.lovehometown.customview.CustomDialog;
import com.example.lovehometown.customview.PublishDialog;
import com.example.lovehometown.model.GoodBigType;
import com.example.lovehometown.model.PublishList;
import com.example.lovehometown.service.HttpService;
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
    List<PublishList.NamesBean> publishList=new ArrayList<PublishList.NamesBean>();
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
    PublishCateGrotAdapter publishCateGrotAdapter;
    PublishDialog dialog;
    public void initView(){

        title.setVisibility(View.VISIBLE);
        title.setText("选择发布类型");

       List<GoodBigType> list=initData();
        final List<GoodBigType> typeList=list;
        ImgGridViewAdapter adapter=new ImgGridViewAdapter(getActivity(),list);
        publishGridView.setAdapter(adapter);
        /**
         * 点击事件
         */
         //子项点击获取分类
        publishGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //final List<String> _list=list;
            @Override
            public void onItemClick(AdapterView<?> parent, View view,  int position, long id) {
                //
                 String name=typeList.get(position).getName();
                final String _name=name;
               //得到dialog内容的布局
                View view1=LayoutInflater.from(getActivity()).inflate(R.layout.publish_dialog_list,null);
                //得到listView
                ListView lv= (ListView) view1.findViewById(R.id.dialog_list);

                //新建一个图片，用来取消选择
                ImageView imageView=new ImageView(getActivity());
                //设置图片
                imageView.setImageResource(R.drawable.error);
                //设置图片的距离
                imageView.setPadding(0,20,0,20);
               final  ImageView _imageView=imageView;
                lv.addFooterView(imageView);
                //给listview设置适配器
                publishCateGrotAdapter=new PublishCateGrotAdapter(getActivity(),publishList);
                lv.setAdapter(publishCateGrotAdapter);
                final  View _view1=view1;
                //网络获取数据，显示分类
                HttpService.getHttpService().getPublishList(new LoveHomeCallBack<String>() {
                    //访问网络成功
                    @Override
                    public void onSuccess(String result) {
                        //清除原来的数据
                       publishList.clear();
                        //把拿到的json数据转换为一个list
                        PublishList publish=   JSON.parseObject(result,PublishList.class);
                        //添加到list里面
                        publishList.addAll(publish.getNames());
                        //更显适配器
                        publishCateGrotAdapter.notifyDataSetChanged();
                        //dialog的选择
                        PublishDialog.Builder builder=new PublishDialog.Builder(getActivity());
                        //设置内容
                        builder.setContentView(_view1);
                        //创建
                         dialog=builder.create();
                        //final  PublishDialog _dialog=dialog;
                        //设置dialog的显示位置
                        Window window = dialog.getWindow();
                        dialog.show();
                        //取消按钮的选择
                        _imageView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        //设置window显示的位置
                        window.setGravity(Gravity.BOTTOM);
                 //选择子项
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                          T.showShort(getActivity(),"连接网络失败,请检查你的网络设置");
                    }
                },position);
                //选择某一项
               lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                   @Override
                   public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                       T.showShort(getActivity(),publishList.get(position).toString());
                      //进入发布页面
                       Intent intent=new Intent();
                       Bundle bundle=new Bundle();
                       bundle.putString("name",_name);
                       bundle.putString("type",publishList.get(position).getName());
                       intent.putExtras(bundle);
                       intent.setClass(getActivity(), AddPublishActivity.class);
                       startActivity(intent);
                       dialog.dismiss();
                   }
               });
            }
        });

    }


}
