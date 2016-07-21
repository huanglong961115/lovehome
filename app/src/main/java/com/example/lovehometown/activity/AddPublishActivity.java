package com.example.lovehometown.activity;

import android.app.Dialog;
import android.app.TimePickerDialog;
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

//    @ViewInject(R.id.photo_gridview)
//    private GridView gridView;
//    private static final int SELECT_PICTURE = 1;
//    private static final int SELECT_CAMER = 2;
//    Context mContext;
//    String path = "";
//    MyAdapter ImgAdapter;
//    List<Bitmap> imgList = new ArrayList<Bitmap>();
//    ImageView ivDelete;
//    private boolean isShowDelete = false;// 根据这个变量来判断是否显示删除图标，true是显示，false是不显示
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
//        mContext = this;
//        ivDelete = (ImageView) findViewById(R.id.img_delete);
//        ImgAdapter = new MyAdapter();
//        ImgAdapter.setIsShowDelete(isShowDelete);
//        gridView.setAdapter(ImgAdapter);
//
//        // 单击事件
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//                                    long arg3) {
//
//                ivDelete = (ImageView) findViewById(R.id.img_delete);
//
//                if (isShowDelete == true) {
//                    // 如果处于正在删除的状态，单击则删除图标消失
//                    isShowDelete = false;
//                    ImgAdapter.setIsShowDelete(isShowDelete);
//                } else {
//                        Toast.makeText(AddPublishActivity.this, "放大图片喽", Toast.LENGTH_SHORT).show();
//                }
//                ImgAdapter.notifyDataSetChanged();
//            }
//        });
//
//        // 长按事件
//        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view,
//                                           int position, long id) {
//
//                // 长按显示删除图标
//                if (isShowDelete == false) {
//                    isShowDelete = true;
//                }
//                ImgAdapter.setIsShowDelete(isShowDelete);
//
//                return true;
//            }
//        });
//

    }



    @Event(R.id.camera_addpublish)
    private void click(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.camera_addpublish:
//                PublishDialog.Builder builder = new PublishDialog.Builder(AddPublishActivity.this);
//                View contentView = LayoutInflater.from(AddPublishActivity.this).inflate(R.layout.dialog_camera, null);
//                contentView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT));
//                Button takephoto = (Button) contentView.findViewById(R.id.takephoto);
//                Button choosephoto = (Button) contentView.findViewById(R.id.choosephoto);
//                Button cancle=(Button)contentView.findViewById(R.id.canale_camera);
//                builder.setContentView(contentView);
//                final PublishDialog dialog2 = builder.create(R.style.dialogStyle);
//                takephoto.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        toGetCameraImage();
//                        dialog2.dismiss();
//                    }
//                });
//                choosephoto.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        toGetLocalImage();
//                        dialog2.dismiss();
//                    }
//                });
//                cancle.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog2.dismiss();
//                    }
//                });
//                Window window = dialog2.getWindow();
//                dialog2.show();
//                WindowManager windowManager = getWindowManager();
//                Display display = windowManager.getDefaultDisplay();
//                WindowManager.LayoutParams lp = dialog2.getWindow().getAttributes();
//                lp.width = (int) (display.getWidth()); //设置宽度
//                dialog2.getWindow().setAttributes(lp);
//                //设置window显示的位置
//                window.setGravity(Gravity.BOTTOM);
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

//    /**
//     * 回调
//     */
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK) {
//            switch (requestCode) {
//                //从相册选择
//                case SELECT_PICTURE:
//                    Uri vUri = data.getData();
//                    // 将图片内容解析成字节数组
//                    String[] proj = { MediaStore.Images.Media.DATA };
//                    Cursor cursor = managedQuery(vUri, proj, null, null, null);
//                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//                    cursor.moveToFirst();
//                    String path1 = cursor.getString(column_index);
//                    Bitmap bm = CameraUtils.getxtsldraw(mContext, path1);
//                    path = CameraUtils.creatfile(mContext, bm, "usermodify");
//                    if (null != bm && !"".equals(bm)) {
//                        imgList.add(bm);
//                    }
//                    ImgAdapter.notifyDataSetChanged();
//                    break;
//                //拍照添加图片
//                case SELECT_CAMER:
//                    Bitmap bm1 = CameraUtils.getxtsldraw(mContext, out.getAbsolutePath());
//                    path = CameraUtils.creatfile(mContext, bm1, "usermodify");
//
//                    if (null != bm1 && !"".equals(bm1)) {
//                        imgList.add(bm1);
//                    }
//                    ImgAdapter.notifyDataSetChanged();
//                    break;
//                default:
//                    break;
//            }
//
//        }
//    }
//    /**
//     * 选择本地图片
//    */
//    public void toGetLocalImage() {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(intent, SELECT_PICTURE);
//
//    }
//
//    /**
//     * 照相选择图片
//     */
//    public void toGetCameraImage() {
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE, null);
//        String photoname = "a.jpg";
//        out = new File(getSDPath(), photoname);
//        Uri uri = Uri.fromFile(out);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
//        startActivityForResult(intent, SELECT_CAMER);
//        // finish();
//    }
//
//    /**
//     * 获取sd卡路径
//     *
//     * @return
//     */
//    private File getSDPath() {
//        File sdDir = null;
//        boolean sdCardExist = Environment.getExternalStorageState().equals(
//                Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
//        if (sdCardExist) {
//            // 这里可以修改为你的路径
//            sdDir = new File(Environment.getExternalStorageDirectory()
//                    + "/DCIM/Camera");
//
//        }
//        return sdDir;
//    }
//    File out;
//    public class MyAdapter extends BaseAdapter {
//
//        private boolean isDelete;  //用于删除图标的显隐
//        private LayoutInflater inflater = LayoutInflater.from(mContext);
//
//        @Override
//        public int getCount() {
//
//            //需要额外多出一个用于添加图片
//            return imgList.size() + 1;
//
//        }
//
//        @Override
//        public Object getItem(int arg0) {
//            return imgList.get(arg0);
//        }
//
//        @Override
//        public long getItemId(int arg0) {
//            return arg0;
//        }
//
//        @Override
//        public View getView(final int position, View convertView, ViewGroup arg2) {
//
//            //初始化页面和相关控件
//            convertView = inflater.inflate(R.layout.dialog_camera, null);
//            ImageView img_pic = (ImageView) convertView
//                    .findViewById(R.id.img_grid);
//            ImageView delete = (ImageView) convertView
//                    .findViewById(R.id.img_delete);
//
//            //默认的添加图片的那个item是不需要显示删除图片的
//            if (imgList.size() >= 1) {
//                if (position <= imgList.size() - 1) {
//                    img_pic.setImageBitmap(imgList.get(position));
//                    // 设置删除按钮是否显示
//                    delete.setVisibility(isDelete ? View.VISIBLE : View.GONE);
//                }
//            }
//
//            //当处于删除状态时，删除事件可用
//            //注意：必须放到getView这个方法中，放到onitemClick中是不起作用的。
//            if (isDelete) {
//                delete.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        imgList.remove(position);
//                        ImgAdapter.notifyDataSetChanged();
//
//                    }
//                });
//            }
//
//            return convertView;
//        }
//        /**
//         * 设置是否显示删除图片
//         *
//         * @param isShowDelete
//         */
//        public void setIsShowDelete(boolean isShowDelete) {
//            this.isDelete = isShowDelete;
//            notifyDataSetChanged();
//        }
//    }

}
