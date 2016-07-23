package com.example.lovehometown.activity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.lovehometown.R;
import com.example.lovehometown.constant.Constants;
import com.example.lovehometown.customview.PublishDialog;
import com.example.lovehometown.model.UserInfo;
import com.example.lovehometown.util.CameraUtils;
import com.example.lovehometown.util.L;
import com.example.lovehometown.util.SPUtils;
import com.example.lovehometown.util.T;
import com.example.lovehometown.util.Uri2url;
import com.meg7.widget.CircleImageView;

import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.io.IOException;

@ContentView(R.layout.activity_user_info)
public class UserInfoActivity extends BaseActivity {
    @ViewInject(R.id.title)
    TextView title;
    @ViewInject(R.id.leftView)
    ImageView backImg;
    @ViewInject(R.id.userNameInfo)
    RelativeLayout userNameInfo;
    @ViewInject(R.id.userphoneInfo)
    RelativeLayout userPhoneInfo;
    @ViewInject(R.id.userAddressInfo)
    RelativeLayout userAddressInfo;
    @ViewInject(R.id.name_user_info)
    TextView userName;
    @ViewInject(R.id.address_user_info)
    TextView userAddress;
    @ViewInject(R.id.phone_user_info)
    TextView userContast;
    UserInfo.UserBean userBean;
    @ViewInject(R.id.userinfo_img)
    CircleImageView imgUserinfo;

    private static final int SELECT_PICTURE = 1;
    private static final int SELECT_CAMER = 2;
    String path = "";
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
        title.setText("用户资料");
        backImg.setVisibility(View.VISIBLE);
        //获得用户资料
        String userInfo= (String) SPUtils.get(UserInfoActivity.this,Constants.USER_INFO,"");
        if(userInfo.equals("")){

        }else{
            //解析
            userBean= JSON.parseObject(userInfo, UserInfo.UserBean.class);
            userName.setText(userBean.getUsername());
            userAddress.setText(userBean.getUserAddress());
            userContast.setText(userBean.getUserContast());
            ImageOptions imageOptions = new ImageOptions.Builder()

                    // 如果ImageView的大小不是定义为wrap_content, 不要crop.
                    .setCrop(true) // 很多时候设置了合适的scaleType也不需要它.
                    // 加载中或错误图片的ScaleType
                    //.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
                    .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                    .setLoadingDrawableId(R.drawable.defualt)
                    .setFailureDrawableId(R.drawable.defualt)
                    .build();
            x.image().bind(imgUserinfo,userBean.getHeadImg(),imageOptions);
            //x.image().

        }

    }
    @Event(value={R.id.userNameInfo,R.id.userphoneInfo,R.id.userAddressInfo,R.id.userinfo_img})
    private void updateInfo(View view){
        switch (view.getId()){
            case R.id.userNameInfo:
                updateInfo(UpdateInfoActivity.class,Constants.USERNMAE,userBean.getUsername());
                break;
            case R.id.userphoneInfo:
                updateInfo(UpdateInfoActivity.class,Constants.USER_PHONE,userBean.getUserContast());
                break;
            case R.id.userAddressInfo:
                updateInfo(UpdateInfoActivity.class,Constants.USER_ADDRESS,userBean.getUserAddress());
                break;
            case R.id.userinfo_img:
                PublishDialog.Builder builder = new PublishDialog.Builder(UserInfoActivity.this);
                View contentView = LayoutInflater.from(UserInfoActivity.this).inflate(R.layout.dialog_camera, null);
                contentView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT));
                Button takephoto = (Button) contentView.findViewById(R.id.takephoto);
                Button choosephoto = (Button) contentView.findViewById(R.id.choosephoto);
                Button cancle=(Button)contentView.findViewById(R.id.canale_camera);
                builder.setContentView(contentView);
                final PublishDialog dialog2 = builder.create(R.style.dialogStyle);
                takephoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toGetCameraImage();
                        dialog2.dismiss();
                    }
                });
                choosephoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        toGetLocalImage();
                        dialog2.dismiss();
                    }
                });
                cancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog2.dismiss();
                    }
                });
                Window window = dialog2.getWindow();
                dialog2.show();
                WindowManager windowManager = getWindowManager();
                Display display = windowManager.getDefaultDisplay();
                WindowManager.LayoutParams lp = dialog2.getWindow().getAttributes();
                lp.width = (int) (display.getWidth()); //设置宽度
                dialog2.getWindow().setAttributes(lp);
                //设置window显示的位置
                window.setGravity(Gravity.BOTTOM);
                break;
        }
    }

    /**
     * 页面跳转
     * @param clazz 跳转的类
     * @param attr 传递过去的参数
     */
    public void updateInfo(Class clazz,String attr,String content){
        Bundle bundle=new Bundle();
        bundle.putString(Constants.INFO_ATTR,attr);
        bundle.putString("content",content);
        Intent intent=new Intent();
        intent.putExtras(bundle);
        intent.setClass(UserInfoActivity.this, clazz);
        startActivity(intent);
        overridePendingTransition(R.anim.right_in,R.anim.right_out);
    }
    @Event(R.id.leftView)
    private void back(View view){
        UserInfoActivity.this.finish();
        //退出动画效果
        overridePendingTransition(R.anim.left_in,R.anim.left_out);
    }

    @Override
    public void onBackPressed() {
        UserInfoActivity.this.finish();
        //退出动画效果
        overridePendingTransition(R.anim.left_in,R.anim.left_out);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //重新初始化
        initView();
    }




    /**
     * 回调
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                //从相册选择
                case SELECT_PICTURE:
                    Bitmap bitmap = null;
                    ContentResolver resolver = getContentResolver();
                    Uri uri = data.getData();

                    if (uri != null) {
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                            imgUserinfo.setImageBitmap(bitmap);
                            userBean.setHeadImg(Uri2url.getRealFilePath(this,uri));
                            String datas=JSON.toJSONString(userBean);
                            SPUtils.put(UserInfoActivity.this,Constants.USER_INFO,datas);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                    break;
                //拍照添加图片
                case SELECT_CAMER:
                    Bitmap bm1 = CameraUtils.getxtsldraw(UserInfoActivity.this, out.getAbsolutePath());
                    path = CameraUtils.creatfile(UserInfoActivity.this, bm1, "usermodify");
                    if (null != bm1 && !"".equals(bm1)) {
                        imgUserinfo.setImageBitmap(bm1);
                        //T.showShort(UserInfoActivity.this,out.getAbsolutePath());
                        userBean.setHeadImg(out.getAbsolutePath());
                        String datas=JSON.toJSONString(userBean);
                        SPUtils.put(UserInfoActivity.this,Constants.USER_INFO,datas);
                    }
                    break;
                default:
                    break;
            }

        }
    }


    /**
     * 选择本地图片
     */
    public void toGetLocalImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, SELECT_PICTURE);

    }

    /**
     * 照相选择图片
     */
    public void toGetCameraImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE, null);
        String photoname = "a.jpg";
        out = new File(getSDPath(), photoname);
        Uri uri = Uri.fromFile(out);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, SELECT_CAMER);
        // finish();
    }

    /**
     * 获取sd卡路径
     *
     * @return
     */
    private File getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
        if (sdCardExist) {
            // 这里可以修改为你的路径
            sdDir = new File(Environment.getExternalStorageDirectory()
                    + "/DCIM/Camera");

        }
        return sdDir;
    }
    File out;

}
