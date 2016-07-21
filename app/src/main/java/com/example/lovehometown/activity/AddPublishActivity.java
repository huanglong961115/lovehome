package com.example.lovehometown.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lovehometown.R;
import com.example.lovehometown.adapter.PhotoGridViewAdapter;
import com.example.lovehometown.customview.PublishDialog;
import com.example.lovehometown.util.L;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

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
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        initView();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initView() {
        /*设置可见*/
        img.setVisibility(View.VISIBLE);
        title.setVisibility(View.VISIBLE);
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        String type = bundle.getString("type");
        title.setText(name + "-" + type);
    }

    @Event(R.id.camera_addpublish)
    private void click(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.camera_addpublish:
                PublishDialog.Builder builder = new PublishDialog.Builder(AddPublishActivity.this);
                View contentView = LayoutInflater.from(AddPublishActivity.this).inflate(R.layout.dialog_camera, null);
                Button takephoto = (Button) contentView.findViewById(R.id.takephoto);
                Button choosephoto = (Button) contentView.findViewById(R.id.choosephoto);
                builder.setContentView(contentView);
                PublishDialog dialog2 = builder.create();
                takephoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                choosephoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                dialog2.show();
                break;
            default:
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "AddPublish Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.lovehometown.activity/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "AddPublish Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.lovehometown.activity/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
