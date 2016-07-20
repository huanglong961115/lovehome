package com.example.lovehometown.service;

import com.example.lovehometown.R;
import com.example.lovehometown.callback.LoveHomeCallBack;
import com.example.lovehometown.constant.Constants;

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

    /**
     * 得到分类的数据
     * @param callBack
     * @param type
     */
    public void getPublishList(LoveHomeCallBack<String> callBack,int type){
        //地址
        //String url="http://172.16.46.7:8080/LoveHome/index.jsp";
        RequestParams params=new RequestParams(Constants.PUBLISH_URL);
        params.addBodyParameter("type",type+"");
        x.http().post(params,callBack);
    }
    public void login(String userName,String password,LoveHomeCallBack<String> callBack){
        RequestParams params=new RequestParams(Constants.LOGIN_URL);
        params.addQueryStringParameter("userPhone",userName);
        params.addBodyParameter("password",password);
        x.http().get(params,callBack);
    }
    public void getPublishlist(String pubtype,LoveHomeCallBack<String> callBack){
        RequestParams params=new RequestParams(Constants.PUBLISH_LIST_URL);
        params.addQueryStringParameter("pubtype",pubtype);
        x.http().get(params,callBack);
    }
}
