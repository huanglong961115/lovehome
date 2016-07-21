package com.example.lovehometown.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.lovehometown.R;
import com.example.lovehometown.activity.AboutLoveHomeActivity;
import com.example.lovehometown.activity.LoginActivity;
import com.example.lovehometown.activity.MyPublishActivity;
import com.example.lovehometown.activity.MymeaasgeActivity;
import com.example.lovehometown.activity.PlatformStatementActivity;
import com.example.lovehometown.activity.UpdatePassWordPctivity;
import com.example.lovehometown.activity.UserInfoActivity;
import com.example.lovehometown.common.Login;
import com.example.lovehometown.constant.Constants;
import com.example.lovehometown.customview.CustomDialog;
import com.example.lovehometown.model.UserInfo;
import com.example.lovehometown.util.DataCleanManager;
import com.example.lovehometown.util.SPUtils;
import com.example.lovehometown.util.T;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by Administrator on 2016/7/16.
 */
@ContentView(R.layout.my_layout)
public class MyFragment extends BaseFragment {
    @ViewInject(R.id.platformStatement)
  RelativeLayout platformStatement;
    @ViewInject(R.id.aboutLoveHome)
    RelativeLayout aboutLoveHome;
    @ViewInject(R.id.updatePassword)
    RelativeLayout updatePassword;
    @ViewInject(R.id.mypublish)
    RelativeLayout myPublish;
    @ViewInject(R.id.myDraft)
    RelativeLayout myDraft;
    @ViewInject(R.id.mycollect)
    RelativeLayout myCollect;
    @ViewInject(R.id.mymessage)
    RelativeLayout myMessage;
    @ViewInject(R.id.logout)
    Button logout;
    @ViewInject(R.id.lgout_layout)
    RelativeLayout logoutLayout;
    @ViewInject(R.id.personal_img)
    ImageView personImg;
    @ViewInject(R.id.username_my)
    TextView personUserName;
    @ViewInject(R.id.phone_my)
    TextView personPhone;
    @ViewInject(R.id.address_my)
    TextView personAddress;
    @ViewInject(R.id.size)
    TextView size;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {

        //获取用户信息
        String userInfo= (String) SPUtils.get(getActivity(),Constants.USER_INFO,"");
        if(userInfo.equals("")){

        }else{
            //设置圆角图片
            ImageOptions imageOptions=new ImageOptions.Builder()
                    //.setSize(DensityUtil.dip2px(120), DensityUtil.dip2px(120))
                     .setRadius(DensityUtil.dip2px(50))
                    // 如果ImageView的大小不是定义为wrap_content, 不要crop.
                    //.setCrop(true) // 很多时候设置了合适的scaleType也不需要它.
                    // 加载中或错误图片的ScaleType
                    //.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
                    //.setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                    .setLoadingDrawableId(R.drawable.defualt)
                    .setFailureDrawableId(R.drawable.defualt)
                    .build();
            UserInfo.UserBean userBean=JSON.parseObject(userInfo,UserInfo.UserBean.class);
            //T.showShort(getActivity(),userBean.getHeadImg());
            x.image().bind(personImg,userBean.getHeadImg(),imageOptions);
            personAddress.setText(userBean.getUserAddress());
            personPhone.setText(userBean.getPhoneNuber());
            personUserName.setText(userBean.getUsername());
        }
        //判断是否登录
        boolean isLogin=Login.getInstance().isLogin(getActivity());
        if(isLogin){
            logoutLayout.setVisibility(View.VISIBLE);
        }else{
            personImg.setImageResource(R.drawable.defualt);
            personUserName.setText("");
            personAddress.setText("");
            personPhone.setText("请点击登录");
            //隐藏
            logoutLayout.setVisibility(View.GONE);
        }
        try {
            size.setText(DataCleanManager.getTotalCacheSize(getActivity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Event(value = {R.id.platformStatement,R.id.aboutLoveHome,R.id.updatePassword,R.id.mymessage,R.id.mypublish,R.id.myDraft,R.id.mycollect,R.id.head_my,R.id.cleancache,R.id.logout})
    private void click(View view){
        int id=view.getId();
        boolean isLogin=Login.getInstance().isLogin(getActivity());
        switch (id){
            case R.id.head_my:
                //我的资料

                if(isLogin) {
                    startActivity(new Intent(getActivity(), UserInfoActivity.class));
                }else{
                    //查看我的资料
                    Bundle bundle = new Bundle();
                    bundle.putString(Constants.NAME, "my");
                    Intent intent2 = new Intent();
                    intent2.putExtras(bundle);
                    intent2.setClass(getActivity(), LoginActivity.class);
                    startActivity(intent2);
                }
                getActivity().overridePendingTransition(R.anim.right_in,R.anim.right_out);
                break;
            //我的发布
            case R.id.mypublish:
              myChange(MyPublishActivity.class,Constants.MY_PUBLISH);
                break;
            //我的草稿
            case R.id.myDraft:
                myChange(MyPublishActivity.class,Constants.MY_DRAFT);
                break;
            //我的收藏
            case R.id.mycollect:
                myChange(MyPublishActivity.class,Constants.MY_COLLECT);
                break;
            //我的消息
            case R.id.mymessage:
                myChange(MymeaasgeActivity.class,Constants.MY_MESSAGE);
                break;
            //平台声明
            case  R.id.platformStatement:
                Intent intent=new Intent(getActivity(), PlatformStatementActivity.class);
                intent.putExtra("service",Constants.PLATFORM_STATEMENT);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.right_in,R.anim.right_out);
                break;
            //关于爱家乡
            case R.id.aboutLoveHome:
                startActivity(new Intent(getActivity(), AboutLoveHomeActivity.class));
                getActivity().overridePendingTransition(R.anim.right_in,R.anim.right_out);
                break;
            //修改密码
            case R.id.updatePassword:
                //判断是否登录
              if(isLogin) {
                  startActivity(new Intent(getActivity(), UpdatePassWordPctivity.class));
                  getActivity().overridePendingTransition(R.anim.right_in, R.anim.right_out);
              }else{
                  Bundle bundle = new Bundle();
                  bundle.putString(Constants.NAME, Constants.UPDATE_PASS);
                  Intent intent2 = new Intent();
                  intent2.putExtras(bundle);
                  intent2.setClass(getActivity(), LoginActivity.class);
                  startActivity(intent2);
              }
                break;
            //清除缓存
            case R.id.cleancache:
                final CustomDialog.Builder builder=new CustomDialog.Builder(getActivity(),R.layout.dialog_person);
                builder.setMessage("确定清除缓存？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                             //清除缓存操作
                        //删除当前应用下所在文件的文件夹
                        //清除应用的内部缓存
                       DataCleanManager.cleanExternalCache(getActivity());
                        DataCleanManager.cleanInternalCache(getActivity());
                        size.setText("");
                        T.showShort(getActivity(),"清除成功");
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                CustomDialog dialog=builder.create();
                dialog.show();
                break;
            //退出登录
            case R.id.logout:
                CustomDialog.Builder builder2=new CustomDialog.Builder(getActivity(),R.layout.dialog_person);
                builder2.setMessage("确定退出登录？");
                builder2.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SPUtils.remove(getActivity(),Constants.USER_INFO);
                        SPUtils.remove(getActivity(),Constants.IS_LOGIN);
                        Intent intent=new Intent();
                        Bundle bundle=new Bundle();
                        bundle.putString(Constants.NAME,"my");
                        intent.putExtras(bundle);
                        intent.setClass(getActivity(),LoginActivity.class);
                        startActivity(intent);
                         dialog.dismiss();
                    }
                });
                builder2.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                CustomDialog exitDialog=builder2.create();
                exitDialog.show();
                break;

        }
    }

    /**
     * 页面跳转
     * @param clazz 跳转的类
     * @param type 传递过去的参数
     */
    public void myChange(Class clazz,String type){
        //判断是否登录
        boolean isLogin= Login.getInstance().isLogin(getActivity());
        if(isLogin) {
            Bundle bundle = new Bundle();
            bundle.putString(Constants.MY_TYPE, type);
            Intent intent = new Intent();
            intent.putExtras(bundle);
            intent.setClass(getActivity(), clazz);
            startActivity(intent);
            getActivity().overridePendingTransition(R.anim.right_in, R.anim.right_out);
        }else{
            Bundle bundle = new Bundle();
            bundle.putString(Constants.NAME, type);
            Intent intent = new Intent();
            intent.putExtras(bundle);
            intent.setClass(getActivity(), LoginActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
       // T.showShort(getActivity(),"哈哈哈哈");
    }
}
