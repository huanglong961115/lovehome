package com.example.lovehometown.service;

import com.example.lovehometown.R;
import com.example.lovehometown.callback.LoveHomeCallBack;

import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/7/19.
 */
public class HttpService {
    private  static  HttpService httpService;

    private HttpService(){

    }
    public static HttpService getHttpService(){
        if (httpService==null){
            httpService=new HttpService();
        }
        return httpService;
    }
    public void getPublishList(LoveHomeCallBack<String> callBack,int type){
        //地址
        String url="http://172.16.46.7:8080/LoveHome/index.jsp";
        RequestParams params=new RequestParams(url);
        params.addBodyParameter("type",type+"");
        x.http().post(params,callBack);
    }
}
