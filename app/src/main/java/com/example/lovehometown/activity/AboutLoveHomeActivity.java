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
import com.example.lovehometown.constant.Constants;
import com.example.lovehometown.customview.CustomProgressDialog;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * 关于
 */
@ContentView(R.layout.activity_about_love_home)
public class AboutLoveHomeActivity extends BaseActivity {
    @ViewInject(R.id.about_love_home_webView)
    WebView aboutLoveHomeWebView;
    @ViewInject(R.id.title)
    TextView title;
    @ViewInject(R.id.leftView)
    ImageView backImg;
    //加载动画效果
    CustomProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    /**
     * 初始化视图
     */
    public void initView(){
        title.setVisibility(View.VISIBLE);
        title.setText("关于爱家乡");
        backImg.setVisibility(View.VISIBLE);
        //加载地址
        aboutLoveHomeWebView.loadUrl(Constants.ABOUT_LOVE_HOME_URL);
        //加载webView页面的监听
        aboutLoveHomeWebView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                view.loadUrl(url);
                return true;

            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                dialog=new CustomProgressDialog(AboutLoveHomeActivity.this,"加载中...",R.drawable.load_anim);
                dialog.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                dialog.dismiss();
            }
        });

        WebSettings webSettings = aboutLoveHomeWebView.getSettings();
        //设置支持javascript
        webSettings.setJavaScriptEnabled(true);
        //设置无缓存
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);

    }
    @Event(R.id.leftView)
    private void back(View view){
        AboutLoveHomeActivity.this.finish();
        //退出动画效果
        overridePendingTransition(R.anim.left_in,R.anim.left_out);
    }

    @Override
    public void onBackPressed() {
        AboutLoveHomeActivity.this.finish();
        //退出动画效果
        overridePendingTransition(R.anim.left_in,R.anim.left_out);
    }
}
