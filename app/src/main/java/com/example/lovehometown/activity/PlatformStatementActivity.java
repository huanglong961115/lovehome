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
import com.example.lovehometown.adapter.PictureViewAdapter;
import com.example.lovehometown.constant.Constants;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_platform_statement)
public class PlatformStatementActivity extends BaseActivity {
    @ViewInject(R.id.PlatformStatement_webView)
    WebView platformStatementWebView;
    @ViewInject(R.id.title)
    TextView title;
    @ViewInject(R.id.leftView)
    ImageView  backImg;
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

       WebSettings webSettings = platformStatementWebView.getSettings();
       //设置支持javascript
       webSettings.setJavaScriptEnabled(true);
       //设置无缓存
       webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
   }
    @Event(R.id.leftView)
    private void back(View view){
        PlatformStatementActivity.this.finish();
    }
}
