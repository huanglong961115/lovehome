package com.example.lovehometown.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.lovehometown.R;
import com.example.lovehometown.constant.Constants;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_details)
public class DetailsActivity extends BaseActivity {
    @ViewInject(R.id.details_webview)
    WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webview.loadUrl(Constants.DETAILS_URL);
        webview.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                view.loadUrl(url);
                return true;
            }

        });

        WebSettings webSettings = webview.getSettings();
        //设置支持javascript
        webSettings.setJavaScriptEnabled(true);
        //设置无缓存
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
    }

}
