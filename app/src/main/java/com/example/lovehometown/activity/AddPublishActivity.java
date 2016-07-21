package com.example.lovehometown.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lovehometown.R;
import com.example.lovehometown.adapter.PhotoGridViewAdapter;
import com.example.lovehometown.util.L;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    List<Bitmap> photos;
    GridView gridView;
    PhotoGridViewAdapter adapter;
    private File mPhotoFile;
    private String mPhotoPath;
    private static final int CAMERA_REQUEST = 1888;//相机请求的接口
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        initView();
    }
    private void initView() {
        /*设置可见*/
        img.setVisibility(View.VISIBLE);
        title.setVisibility(View.VISIBLE);
        Bundle bundle=getIntent().getExtras();
        String name=bundle.getString("name");
        String type=bundle.getString("type");
        title.setText(name+"-"+type);
        photos=new ArrayList<Bitmap>();
        gridView= (GridView) findViewById(R.id.photo_gridview);
    }
    @Event(R.id.camera_addpublish)
    private void click(View view){
        int id=view.getId();
        switch (id){
            case R.id.camera_addpublish:
                try{
                    Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    mPhotoPath="mnt/sdcard/DCIM/Camera/"+getPhotoFileName();//保存照片的路径
                    mPhotoFile = new File(mPhotoPath);
                    if (!mPhotoFile.exists()) {
                        mPhotoFile.createNewFile();
                    }
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mPhotoFile));
                    startActivityForResult(intent, CAMERA_REQUEST);
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";
    }
    //重写的onActivityResult方法中可以获取到返回的照片数据
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST) {
            Bitmap bitmap = BitmapFactory.decodeFile(mPhotoPath, null);//加载手机磁盘上的资源
            photos.add(bitmap);
            L.e("hehe", photos.size() + "");
            adapter = new PhotoGridViewAdapter(AddPublishActivity.this, photos);
            gridView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
//            photo_camera.setImageBitmap(bitmap);
        }
    }
}
