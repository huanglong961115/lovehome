package com.example.lovehometown.activity;

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
        aboutLoveHomeWebView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                view.loadUrl(url);
                return true;
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
    }
}
