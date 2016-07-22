package com.example.lovehometown.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.lovehometown.R;
import com.example.lovehometown.common.Login;
import com.example.lovehometown.constant.Constants;
import com.example.lovehometown.customview.CustomDialog;
import com.example.lovehometown.customview.CustomProgressDialog;
import com.example.lovehometown.customview.PublishDialog;
import com.example.lovehometown.javascriptinterface.DetailsJavaScript;
import com.example.lovehometown.model.BusinessList;
import com.example.lovehometown.model.ShopInfo;
import com.example.lovehometown.model.UserInfo;
import com.example.lovehometown.service.DBService;
import com.example.lovehometown.util.L;
import com.example.lovehometown.util.SPUtils;
import com.example.lovehometown.util.T;
import com.example.lovehometown.vo.Love;

import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

@ContentView(R.layout.activity_details)
public class DetailsActivity extends BaseActivity {
    @ViewInject(R.id.details_webview)
    WebView webview;
    @ViewInject(R.id.call)
    LinearLayout call;
    @ViewInject(R.id.jubao)
    LinearLayout jubao;
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
    //加载动画效果
    CustomProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }
   //分享
    @Event(R.id.rightView1)
    private void share(View view){
        showShare();
    }
    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("分享");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://www.hniu.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://www.hniu.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://www.baidu.com");

// 启动分享GUI
        oks.show(this);
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
        //T.showShort(this,jsonData);
        webview.loadUrl(Constants.DETAILS_URL);
        webview.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                view.loadUrl(url);
                return true;
            }
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                dialog=new CustomProgressDialog(DetailsActivity.this,"加载中...",R.drawable.load_anim);
                //dialog.set
                dialog.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                dialog.dismiss();
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
    @Event(value={R.id.call,R.id.jubao})
    private void click(View view){
        int id=view.getId();
        switch (id){
            case R.id.call:
                CustomDialog.Builder dialog = new CustomDialog.Builder(DetailsActivity.this,R.layout.dialog);
                BusinessList.PublistBean shopInfo= JSON.parseObject(jsonData,BusinessList.PublistBean.class);
                final BusinessList.PublistBean _shopInfo=shopInfo;
                dialog.setMessage("确定拨打:" + _shopInfo.getBusinessPhone());
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //调用系统拨打电话
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" +_shopInfo.getBusinessPhone()));
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
                break;
            case R.id.jubao:
                PublishDialog.Builder builder = new PublishDialog.Builder(DetailsActivity.this);
                View contentView= LayoutInflater.from(DetailsActivity.this).inflate(R.layout.dialog_jubao,null);
                RadioButton qiTa= (RadioButton) contentView.findViewById(R.id.qita);
                EditText buchong= (EditText) contentView.findViewById(R.id.buchong);
                final EditText _buchong=buchong;
                qiTa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked){
                            _buchong.setVisibility(View.VISIBLE);
                        }else{
                            _buchong.setVisibility(View.GONE);
                        }
                    }
                });

                TextView confirm= (TextView) contentView.findViewById(R.id.confirm);
                TextView negaitv= (TextView) contentView.findViewById(R.id.negitvButton);

               builder.setContentView(contentView);
                PublishDialog dialog2=builder.create(R.style.dialogStyle2);
                dialog2.show();
                final PublishDialog _dialog2=dialog2;
                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       _dialog2.dismiss();
                    }
                });
                negaitv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        _dialog2.dismiss();
                    }
                });
                break;
        }
    }
    @Event(R.id.rightView2)
    private void love(View view){
        boolean isLogin=Login.getInstance().isLogin(DetailsActivity.this);
        if(isLogin){
            String userInfo= (String) SPUtils.get(DetailsActivity.this,Constants.USER_INFO,"");
            if (userInfo.equals("")){
            }else{
                UserInfo.UserBean userBean=JSON.parseObject(userInfo, UserInfo.UserBean.class);
                BusinessList.PublistBean shopInfo= JSON.parseObject(jsonData,BusinessList.PublistBean.class);
                Love love=new Love();
                love.setBusinessName(shopInfo.getBusinessName());
                love.setBusinessAddress(shopInfo.getBusinessAddress());
                love.setBusinessDetails(shopInfo.getBusinessDetails());
                love.setBusinessLinkman(shopInfo.getBusinessLinkman());
                love.setBusinessMement(shopInfo.getBusinessMement());
                love.setBusinessStarttime(shopInfo.getBusinessStarttime());
                love.setBusinessEndtime(shopInfo.getBusinessEndtime());
                love.setBusinessPhone(shopInfo.getBusinessPhone());
                love.setBusinessPrice(shopInfo.getBusinessPrice());
                love.setChildType(shopInfo.getChildType());
                love.setIstakeaway(shopInfo.getIstakeaway());
                love.setPublishImg(shopInfo.getPublishImg());
                love.setPublishorLove(1);
                love.setType(shopInfo.getType());
                love.setWorkTitle(shopInfo.getWorkTitle());
                love.setWorkSalary(shopInfo.getWorkSalary());
                love.setTakeawayStart(shopInfo.getTakeawayStart());
                love.setTakeawayEnd(shopInfo.getTakeawayEnd());
                love.setTakeawayFee(shopInfo.getTakeawayFee());
                love.setUserMobile(userBean.getPhoneNuber());
                try {
                    List<Love> l= DBService.getInstance().isLove(love.getUserMobile(),love.getBusinessName());
                    if (l!=null &&  l.size()>0){
                        Toast.makeText(this, "您已经收藏过了", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    DBService.getInstance().collect(love);
                    Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();

                } catch (DbException e) {
                    e.printStackTrace();
                }
            }

        }else{
            Intent intent=new Intent();
            Bundle bundle=new Bundle();
            bundle.putString(Constants.NAME,Constants.Love);
            intent.putExtras(bundle);
            intent.setClass(DetailsActivity.this,LoginActivity.class);
            startActivity(intent);
        }
    }
    @Event(R.id.leftView)
    private void back(View view){
        DetailsActivity.this.finish();
        //退出动画效果
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        DetailsActivity.this.finish();
    }
}
