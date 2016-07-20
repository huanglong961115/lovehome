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
import com.example.lovehometown.javascriptinterface.DetailsJavaScript;
import com.example.lovehometown.util.T;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_old_hometown)
public class OldHometownActivity extends AppCompatActivity {
    @ViewInject(R.id.web_oldhometown)
    WebView webView;
    /*标题栏*/
    @ViewInject(R.id.leftView)
    private ImageView img;
    @ViewInject(R.id.title)
    private TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }
    private void initView(){
        img.setVisibility(View.VISIBLE);
        title.setVisibility(View.VISIBLE);
        title.setText("老乡会公告");
        //webview
        webView.loadUrl(Constants.OLD_HOMETOWN_URL);
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                view.loadUrl(url);
                return true;
            }

        });
        WebSettings webSettings = webView.getSettings();
        //设置支持javascript
        webSettings.setJavaScriptEnabled(true);
        //设置无缓存
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
    }
}