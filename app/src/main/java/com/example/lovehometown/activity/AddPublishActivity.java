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

import com.example.lovehometown.R;
import com.example.lovehometown.adapter.PhotoGridViewAdapter;
import com.example.lovehometown.customview.PublishDialog;
import com.example.lovehometown.util.CameraUtils;
import com.example.lovehometown.util.L;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    @ViewInject(R.id.photo_gridview)
    private GridView gridView;
    private static final int SELECT_PICTURE = 1;
    private static final int SELECT_CAMER = 2;
    Context mContext;
    String path = "";
    PhotoGridViewAdapter adapter;
    List<Bitmap> imgList = new ArrayList<Bitmap>();
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        initView();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initView() {
        /*设置可见*/
        img.setVisibility(View.VISIBLE);
        title.setVisibility(View.VISIBLE);
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        String type = bundle.getString("type");
        if(name.equals("美食")){
            waiMaiLayout.setVisibility(View.VISIBLE);
        }else{
            waiMaiLayout.setVisibility(View.GONE);
        }
        //添加监听
        waimai.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    isWaiMai.setVisibility(View.VISIBLE);
                }else {
                    isWaiMai.setVisibility(View.GONE);
                }
            }
        });
        title.setText(name + "-" + type);
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
                            if(minute<10){
                               min="0" +minute;
                            }
                            beginTime.setText(hourOfDay + ":" + min);
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
                            String min=minute+"";
                            if(minute<10){
                                min="0" +minute;
                            }
                            endTime.setText(hourOfDay + ":" + min);
                            // Toast.makeText(AddPublishActivity.this, hourOfDay + ":" + minute, Toast.LENGTH_LONG).show();
                        }
                    }, hourOfDay, minute, true).show();
                }
                }

        });
//        //拍照
        mContext = this;
        adapter = new PhotoGridViewAdapter(AddPublishActivity.this,imgList);
        gridView.setAdapter(adapter);

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
