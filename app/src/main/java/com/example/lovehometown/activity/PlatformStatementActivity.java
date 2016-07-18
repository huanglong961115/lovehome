package com.example.lovehometown.activity;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lovehometown.R;
import com.example.lovehometown.adapter.PictureViewAdapter;
import com.example.lovehometown.constant.Constants;
import com.example.lovehometown.customview.CustomDialog;
import com.example.lovehometown.customview.CustomProgressDialog;
import com.example.lovehometown.customview.CustomViewPager;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * 平台声明
 */
@ContentView(R.layout.activity_platform_statement)
public class PlatformStatementActivity extends BaseActivity {
    @ViewInject(R.id.PlatformStatement_webView)
    WebView platformStatementWebView;
    @ViewInject(R.id.title)
    TextView title;
    @ViewInject(R.id.leftView)
    ImageView  backImg;
    //加载动画效果
    CustomProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }
   public void initView(){
       title.setVisibility(View.VISIBLE);
       title.setText("平台声明");
       backImg.setVisibility(View.VISIBLE);
       //加载地址
       platformStatementWebView.loadUrl(Constants.PLATFORM_STATEMENT_URL);
       platformStatementWebView.setWebViewClient(new WebViewClient(){

           @Override
           public boolean shouldOverrideUrlLoading(WebView view, String url) {
               // TODO Auto-generated method stub
               view.loadUrl(url);
               return true;
           }

       });
       //加载webView页面的监听
     platformStatementWebView.setWebViewClient(new WebViewClient(){
         //开始加载
         @Override
         public void onPageStarted(WebView view, String url, Bitmap favicon) {
             super.onPageStarted(view, url, favicon);
             dialog=new CustomProgressDialog(PlatformStatementActivity.this,"加载中...",R.drawable.load_anim);
             dialog.show();
         }
       //加载完毕
         @Override
         public void onPageFinished(WebView view, String url) {
             super.onPageFinished(view, url);
             dialog.dismiss();
         }
     });
       WebSettings webSettings = platformStatementWebView.getSettings();
       //设置支持javascript
       webSettings.setJavaScriptEnabled(true);
       //设置无缓存
       webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
   }
    @Event(R.id.leftView)
    private void back(View view){
        PlatformStatementActivity.this.finish();
        //退出动画效果
        overridePendingTransition(R.anim.left_in,R.anim.left_out);
    }

    @Override
    public void onBackPressed() {
        PlatformStatementActivity.this.finish();
        //退出动画效果
        overridePendingTransition(R.anim.left_in,R.anim.left_out);
    }
}
