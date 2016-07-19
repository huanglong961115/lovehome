package com.example.lovehometown.javascriptinterface;

import android.content.Context;
import android.webkit.JavascriptInterface;

import com.example.lovehometown.util.T;

/**
 * Created by Administrator on 2016/7/19.
 */
public class DetailsJavaScript {
    private Context context;
    private String jsonData;

    public DetailsJavaScript(Context context, String jsonData) {
        this.context = context;
        this.jsonData = jsonData;
    }
    @JavascriptInterface
    public String details(){
        return this.jsonData;
    }
}
