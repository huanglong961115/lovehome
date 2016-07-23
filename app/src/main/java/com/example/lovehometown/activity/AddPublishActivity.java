package com.example.lovehometown.activity;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.lovehometown.R;
import com.example.lovehometown.adapter.PhotoGridViewAdapter;
import com.example.lovehometown.callback.LoveHomeCallBack;
import com.example.lovehometown.common.Login;
import com.example.lovehometown.constant.Constants;
import com.example.lovehometown.customview.CustomGridView;
import com.example.lovehometown.customview.PublishDialog;
import com.example.lovehometown.model.BusinessList;
import com.example.lovehometown.model.PublishResults;
import com.example.lovehometown.model.UserInfo;
import com.example.lovehometown.service.DBService;
import com.example.lovehometown.service.HttpService;
import com.example.lovehometown.util.CameraUtils;
import com.example.lovehometown.util.L;
import com.example.lovehometown.util.SPUtils;
import com.example.lovehometown.util.T;
import com.example.lovehometown.util.Uri2url;
import com.example.lovehometown.vo.Publish;

import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ContentView(R.layout.activity_add_publish)
public class AddPublishActivity extends BaseActivity {
    /*标题栏*/
    @ViewInject(R.id.leftView)
    private ImageView img;
    @ViewInject(R.id.title)
    private TextView title;
    @ViewInject(R.id.camera_addpublish)
    private ImageView camera;
    @ViewInject(R.id.waimai_addpublish)
    private LinearLayout waiMaiLayout;
    @ViewInject(R.id.waimai)
    private LinearLayout isWaiMai;
    @ViewInject(R.id.check_addPublish)
    private CheckBox waimai;
    @ViewInject(R.id.starttime1_addpublish)
    private EditText beginTime;
    @ViewInject(R.id.endtime1_addpublish)
    private EditText endTime;
    @ViewInject(R.id.storename_addpublish)
    EditText shopName;
    @ViewInject(R.id.money_addpublish)
    EditText everyMoney;
    @ViewInject(R.id.countunit_addpublish)
    EditText count;
    @ViewInject(R.id.address_addpublish)
    EditText address;
    @ViewInject(R.id.contacts_addpublish)
    EditText linkMan;
    @ViewInject(R.id.phone_addpublish)
    EditText phone;
    @ViewInject(R.id.details_addpublish)
    EditText details;
    @ViewInject(R.id.starttime2_addpublish)
    EditText waimaistTime;
    @ViewInject(R.id.endtime2_addpublish)
    EditText waimaienTime;
    @ViewInject(R.id.area_addpublish)
    EditText waimaiarea;
    @ViewInject(R.id.howmuch_addpublish)
    EditText waimiaMoney;

    @ViewInject(R.id.photo_gridview)
    private CustomGridView gridView;
    //是否支持外卖
   int isWaimai=0;
    private static final int SELECT_PICTURE = 1;
    private static final int SELECT_CAMER = 2;
    Context mContext;
    String path = "";
    //图片地址
    List<String> list=new ArrayList<>();
    String childType;
    PhotoGridViewAdapter adapter;
    String bigTypename;
    String typename;
    String type;
    Publish publish=new Publish();
    List<Bitmap> imgList = new ArrayList<Bitmap>();
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if(shopName.equals("")||shopName==null){
            //获取传过来的bundle
            Bundle bundle=getIntent().getExtras();
            //获取传过来的具体值

    }
        initView();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initView() {
        adapter = new PhotoGridViewAdapter(AddPublishActivity.this,imgList);
        gridView.setAdapter(adapter);
        Bundle bundle = getIntent().getExtras();
        int mag=bundle.getInt("msg");
        if(mag==1){
            Publish publish=bundle.getParcelable("publish");
           this.publish.setLoveId(publish.getLoveId());
            shopName.setText(publish.getBusinessName());
            beginTime.setText(publish.getBusinessStarttime());
            endTime.setText(publish.getBusinessEndtime());
            everyMoney.setText(publish.getBusinessPrice());
            count.setText(publish.getBusinessMement());
            linkMan.setText(publish.getBusinessLinkman());
            phone.setText(publish.getBusinessPhone());
            address.setText(publish.getBusinessAddress());
            details.setText(publish.getBusinessDetails());
            img.setVisibility(View.VISIBLE);
            title.setVisibility(View.VISIBLE);
            bigTypename=publish.getBigTypeName();
            type=publish.getType();
            childType=publish.getChildType();
            typename=publish.getXiangxifenlei();
            isWaimai=publish.getIstakeaway();
            try {
                List<String> list1 = JSON.parseArray(publish.getPublishImg(), String.class);
                Log.e("tag",publish.getPublishImg());
                for (String key : list1) {
                    /*URL url = new URL(key);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    T.showShort(AddPublishActivity.this,url.getPath());
                    conn.setConnectTimeout(5000);
                    int max = conn.getContentLength();
                    InputStream is = conn.getInputStream();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int len;*/

                   /* while ((len = is.read(buffer)) != -1) {
                        baos.write(buffer, 0, len);
                    }
                    byte[] result = baos.toByteArray();
                   Bitmap bitmap=  BitmapFactory.decodeByteArray(result, 0, result.length);*/
                    Bitmap bitmap=CameraUtils.getxtsldraw(this,key);
                    list.add(key);
                    imgList.add(bitmap);
                    //T.showShort(AddPublishActivity.this,imgList.size());
                    adapter.notifyDataSetChanged();
                }
                }catch(Exception e){
                    e.printStackTrace();


            }
        if (isWaimai==1){
                ;

                waiMaiLayout.setVisibility(View.VISIBLE);
                waimaiarea.setText("3");
                waimai.setChecked(true);
                waimaistTime.setText(publish.getTakeawayStart());
                waimaienTime.setText(publish.getTakeawayEnd());
                waimiaMoney.setText(publish.getTakeawayFee());
            }else{
                waiMaiLayout.setVisibility(View.GONE);
            }
           // return;
        }else {
        /*设置可见*/
            img.setVisibility(View.VISIBLE);
            title.setVisibility(View.VISIBLE);
            bigTypename = bundle.getString("name");
            type = bundle.getString("type");
            childType = bundle.getString("childtype");
            if (bigTypename.equals("美食")) {
                waiMaiLayout.setVisibility(View.VISIBLE);
            } else {
                waiMaiLayout.setVisibility(View.GONE);
            }
        }
        //添加监听
        waimai.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    isWaimai=1;
                    isWaiMai.setVisibility(View.VISIBLE);
                }else {
                    isWaimai=0;
                    isWaiMai.setVisibility(View.GONE);
                }
            }
        });
        title.setText(bigTypename + "-" + childType);
        beginTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    Calendar c = Calendar.getInstance();
                    int hourOfDay = c.get(Calendar.HOUR_OF_DAY);
                    int minute = c.get(Calendar.MINUTE);
                    new TimePickerDialog(AddPublishActivity.this, new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            // TODO Auto-generated method stub
                            String min=minute+"";
                            String hour=hourOfDay+"";
                            if(hourOfDay<10){
                                hour="0"+hourOfDay;
                            }
                            if(minute<10){
                               min="0" +minute;
                            }
                            beginTime.setText(hour + ":" + min);
                           // Toast.makeText(AddPublishActivity.this, hourOfDay + ":" + minute, Toast.LENGTH_LONG).show();
                        }
                    }, hourOfDay, minute, true).show();
                }
            }
        });
        endTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    Calendar c = Calendar.getInstance();
                    int hourOfDay = c.get(Calendar.HOUR_OF_DAY);
                    int minute = c.get(Calendar.MINUTE);
                    new TimePickerDialog(AddPublishActivity.this, new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            // TODO Auto-generated method stub
                            String hour=hourOfDay+"";
                            if(hourOfDay<10){
                                hour="0"+hourOfDay;
                            }
                            String min=minute+"";
                            if(minute<10){
                                min="0" +minute;
                            }
                            endTime.setText(hour + ":" + min);
                            // Toast.makeText(AddPublishActivity.this, hourOfDay + ":" + minute, Toast.LENGTH_LONG).show();
                        }
                    }, hourOfDay, minute, true).show();
                }
                }

        });
        waimaistTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    Calendar c = Calendar.getInstance();
                    int hourOfDay = c.get(Calendar.HOUR_OF_DAY);
                    int minute = c.get(Calendar.MINUTE);
                    new TimePickerDialog(AddPublishActivity.this, new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            // TODO Auto-generated method stub
                            String hour=hourOfDay+"";
                            if(hourOfDay<10){
                                hour="0"+hourOfDay;
                            }
                            String min=minute+"";
                            if(minute<10){
                                min="0" +minute;
                            }
                            endTime.setText(hour + ":" + min);
                            // Toast.makeText(AddPublishActivity.this, hourOfDay + ":" + minute, Toast.LENGTH_LONG).show();
                        }
                    }, hourOfDay, minute, true).show();
                }
            }

        });
        waimaistTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    Calendar c = Calendar.getInstance();
                    int hourOfDay = c.get(Calendar.HOUR_OF_DAY);
                    int minute = c.get(Calendar.MINUTE);
                    new TimePickerDialog(AddPublishActivity.this, new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            // TODO Auto-generated method stub
                            String hour=hourOfDay+"";
                            if(hourOfDay<10){
                                hour="0"+hourOfDay;
                            }
                            String min=minute+"";
                            if(minute<10){
                                min="0" +minute;
                            }
                            endTime.setText(hour + ":" + min);
                            // Toast.makeText(AddPublishActivity.this, hourOfDay + ":" + minute, Toast.LENGTH_LONG).show();
                        }
                    }, hourOfDay, minute, true).show();
                }
            }

        });
//        //拍照
        mContext = this;


    }

  @Event(value={R.id.publish_addpublish,R.id.save_addpublish})
   private void publishOrDraft(View view){
      boolean isLogin= Login.getInstance().isLogin(AddPublishActivity.this);
      //已经登录
      if(isLogin){
          //获取商家名称
          String name=shopName.getText().toString();
          //获取开始时间
          String begin=beginTime.getText().toString();
          //获取结束时间
          String end=endTime.getText().toString();
          //
          String enrvey=everyMoney.getText().toString();
          //计量单位
          String counts=count.getText().toString();
          String adress=address.getText().toString();
          String constants=linkMan.getText().toString();
          String tel=phone.getText().toString();
          String reg="^\\s*$";
          if(name.matches(reg)){
              T.showShort(AddPublishActivity.this,"商家名称不能为空");
              return;
          }else if(begin.matches(reg)){
              T.showShort(AddPublishActivity.this,"开始时间不能为空");
              return;
          }
          //验证结束时间和开始时间
          else if(end.matches(reg)){
              T.showShort(AddPublishActivity.this,"结束时间不能为空");
              return;
          }
          SimpleDateFormat format=new SimpleDateFormat("hh:mm");
          try {
              Date date1=format.parse(begin);
              Date date2=format.parse(end);
              if(date2.before(date1)){
                  T.showShort(AddPublishActivity.this,"结束时间必须大于开始时间");
                  return;
              }
          } catch (ParseException e) {
              e.printStackTrace();
          }

          if(enrvey.matches(reg)){
              T.showShort(AddPublishActivity.this,"人均消费不能为空");
              return;
          }else if(counts.matches(reg)){
              T.showShort(AddPublishActivity.this,"计量单位不能为空");
              return;
          }else if(adress.matches(reg)){
              T.showShort(AddPublishActivity.this,"地址不能为空");
              return;
          }else if(constants.matches(reg)){
              T.showShort(AddPublishActivity.this,"联系人不能为空");
              return;
          }
          //验证电话号码

         // String phoneReg="";
         // Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
          //Matcher matcher = regex.matcher(tel);
          //Pattern p = Pattern.compile(phoneReg);
         // Matcher m = p.matcher(tel);
          //验证电话迪号码是否为空
          if(tel.matches(reg)){
              T.showShort(AddPublishActivity.this,"电话号码不能为空");
              return;
          }
          //publish=new Publish();
          String userInfo= (String) SPUtils.get(AddPublishActivity.this,Constants.USER_INFO,"");
          UserInfo.UserBean userBean= JSON.parseObject(userInfo, UserInfo.UserBean.class);
          publish.setUserMobile(userBean.getPhoneNuber());
          publish.setBusinessAddress(adress);
          publish.setBusinessDetails(details.getText().toString());
          publish.setBusinessEndtime(end);
         publish.setBusinessMement(counts);
          publish.setBusinessLinkman(constants);
        publish.setBusinessName(name);
         publish.setBusinessPhone(tel);
        publish.setBusinessPrice(enrvey);
         publish.setBusinessStarttime(begin);
         publish.setChildType(childType);
          publish.setIstakeaway(isWaimai);
         publish.setPublishImg(JSON.toJSONString(list));
          publish.setBigTypeName(bigTypename);
         if(isWaimai==1){
             publish.setTakeawayEnd(waimaienTime.getText().toString());
             publish.setTakeawayFee(waimiaMoney.getText().toString());
             //publish.sett
             publish.setTakeawayStart(waimaistTime.getText().toString());

         }
          publish.setXiangxifenlei(typename);
          publish.setType(type);




          //获取
          switch (view.getId()){
              case R.id.publish_addpublish:
                  //1.发布
                  publish.setPublishorLove(1);
                  try {
                      HttpService.getHttpService().publish(publish, new LoveHomeCallBack<String>() {
                          @Override
                          public void onSuccess(String result) {
                              PublishResults results=JSON.parseObject(result,PublishResults.class);
                              T.showShort(AddPublishActivity.this,results.getResults().getMsg());

                          }

                          @Override
                          public void onError(Throwable ex, boolean isOnCallback) {
                              //T.showShort(AddPublishActivity.this,"网络连接失败");
                          }
                      });
                     DBService.getInstance().collectPublish(publish);
                     //T.showShort(AddPublishActivity.this, (DBService.getInstance().selectPublish(userBean.getPhoneNuber(),1).toString()));
                    T.showShort(AddPublishActivity.this,"发布成功");
                } catch (DbException e) {
                      //T.showShort(AddPublishActivity.this,e.getMessage());
                      e.printStackTrace();
                }
                  AddPublishActivity.this.finish();
                  break;
              case R.id.save_addpublish:
                  publish.setPublishorLove(0);
                 /* */
                  try {
                      DBService.getInstance().drafPublish(publish);
                      T.showShort(AddPublishActivity.this,"保存成功");
                  } catch (DbException e) {
                      e.printStackTrace();
                  }
                  AddPublishActivity.this.finish();
                  break;
                //0.草稿

          }
      }else{
          Intent intent=new Intent();
          Bundle bundle=new Bundle();
          bundle.putString(Constants.NAME,Constants.PUBLISH);
          intent.putExtras(bundle);
          intent.setClass(AddPublishActivity.this,LoginActivity.class);
          startActivity(intent);
      }

   }

    @Event(R.id.camera_addpublish)
    private void click(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.camera_addpublish:
                PublishDialog.Builder builder = new PublishDialog.Builder(AddPublishActivity.this);
                View contentView = LayoutInflater.from(AddPublishActivity.this).inflate(R.layout.dialog_camera, null);
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
            default:
                break;
        }
    }
    @Event(R.id.leftView)
    private void back(View view){
        AddPublishActivity.this.finish();
        //退出动画效果
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AddPublishActivity.this.finish();
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
                            imgList.add(bitmap);
                            L.e("size2",imgList.size()+"");
                            list.add(Uri2url.getRealFilePath(AddPublishActivity.this,uri));
                            Log.e("TAG",list.size()+"");
                            adapter.notifyDataSetChanged();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                    break;
                //拍照添加图片
                case SELECT_CAMER:
                    Bitmap bm1 = CameraUtils.getxtsldraw(mContext, out.getAbsolutePath());
                    path = CameraUtils.creatfile(mContext, bm1, "usermodify");
                    if (null != bm1 && !"".equals(bm1)) {
                        imgList.add(bm1);
                    }
                    L.e("size",imgList.size()+"");
                    list.add(out.getAbsolutePath());
                    adapter.notifyDataSetChanged();
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
        String photoname = System.currentTimeMillis()+"a.jpg";
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
