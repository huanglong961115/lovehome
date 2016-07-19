package com.example.lovehometown.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.lovehometown.R;
import com.example.lovehometown.constant.Constants;
import com.example.lovehometown.customview.CustomDialog;
import com.example.lovehometown.javascriptinterface.DetailsJavaScript;
import com.example.lovehometown.model.ShopInfo;
import com.example.lovehometown.util.T;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_details)
public class DetailsActivity extends BaseActivity {
    @ViewInject(R.id.details_webview)
    WebView webview;
    @ViewInject(R.id.call)
    LinearLayout call;
    /*标题栏*/
    @ViewInject(R.id.leftView)
    private ImageView img;
    @ViewInject(R.id.title)
    private TextView title;
    @ViewInject(R.id.rightView2)
    ImageView loveView;
    @ViewInject(R.id.rightView1)
    ImageView shareView;
    //传过来的数据
    String jsonData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }
    private void initView(){
        img.setVisibility(View.VISIBLE);
        title.setVisibility(View.VISIBLE);
        title.setText("详情");
        loveView.setVisibility(View.VISIBLE);
        shareView.setVisibility(View.VISIBLE);
        shareView.setImageResource(R.drawable.fenxiang);
        loveView.setImageResource(R.drawable.love);
        Bundle bundle=getIntent().getExtras();
         jsonData=bundle.getString("jsonData");
        T.showShort(this,jsonData);
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
        //和js进行交互
        webview.addJavascriptInterface(new DetailsJavaScript(DetailsActivity.this,jsonData),"android");
    }
    @Event(R.id.call)
    private void call(View view){
        CustomDialog.Builder dialog = new CustomDialog.Builder(DetailsActivity.this,R.layout.dialog);
        ShopInfo shopInfo= JSON.parseObject(jsonData,ShopInfo.class);
        final ShopInfo _shopInfo=shopInfo;
        dialog.setMessage("确定拨打:" + _shopInfo.getPhone());
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //调用系统拨打电话
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + _shopInfo.getPhone()));
                //权限检查
                //sdk23之后

                if (ActivityCompat.checkSelfPermission(DetailsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
                dialog.dismiss();
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.create().show();
    }
}
