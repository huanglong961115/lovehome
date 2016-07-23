package com.example.lovehometown.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.lovehometown.R;
import com.example.lovehometown.adapter.PictureViewAdapter;
import com.example.lovehometown.util.T;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_guide)
public class GuideActivity extends BaseActivity {
  @ViewInject(R.id.guidView)
  ViewPager pictureViewPager;
    List<View> list=new ArrayList<View>();
    int imgs[]={R.drawable.guide_one,R.drawable.guide_two,R.drawable.guide_three};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         initView();
    }
    public void initView(){
        for(int i=0;i<imgs.length;i++){
            ImageView img=new ImageView(this);
            img.setImageResource(imgs[i]);
            list.add(img);
        }
        PictureViewAdapter adapter=new PictureViewAdapter(list);

        pictureViewPager.setAdapter(adapter);
        pictureViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                 if(position==2){
                     list.get(position).setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                             //T.showShort(GuideActivity.this,"点击了我");
                             startActivity(new Intent(GuideActivity.this,StartActivity.class));
                             GuideActivity.this.finish();
                         }
                     });
                 }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
