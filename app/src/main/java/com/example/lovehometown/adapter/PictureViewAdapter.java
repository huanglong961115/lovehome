package com.example.lovehometown.adapter;


import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Administrator on 2016/7/16.
 */

/**
 * viewPager的适配器设置
 */
public class PictureViewAdapter extends PagerAdapter{
    List<View> imgList;
    @Override
    public int getCount() {
        return imgList.size();
    }

    public PictureViewAdapter(List<View> imgList) {
        this.imgList = imgList;
    }

    @Override

    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view=imgList.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imgList.get(position));
    }
}
