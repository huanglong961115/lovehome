package com.example.lovehometown.service;

import com.example.lovehometown.R;
import com.example.lovehometown.callback.LoveHomeCallBack;
import com.example.lovehometown.constant.Constants;

import org.xutils.common.Callback;
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
    public void getPublishList(int type,LoveHomeCallBack<String> callBack){
        //地址
        //String url="http://172.16.46.7:8080/LoveHome/index.jsp";
        RequestParams params=new RequestParams(Constants.PUBLISH_URL);
        params.addQueryStringParameter("pubtype",type+"");
        x.http().get(params,callBack);
    }

    /**
     * 登录
     * @param userName
     * @param password
     * @param callBack
     */
    public void login(String userName,String password,LoveHomeCallBack<String> callBack){
        RequestParams params=new RequestParams(Constants.LOGIN_URL);
        params.addQueryStringParameter("userPhone",userName);
        params.addBodyParameter("password",password);
        x.http().get(params,callBack);
    }

    /**
     * 发送验证码
     * @param phonenumber
     * @param callBack
     */
   public void sendCode(String phonenumber,LoveHomeCallBack<String> callBack){
       RequestParams params=new RequestParams(Constants.SMS_URL);
       params.addQueryStringParameter("userPhone",phonenumber);
       x.http().get(params,callBack);
   }
    //注册
    public void register(String phoneNumber,String code,String password,LoveHomeCallBack<String> callBack){
        RequestParams params=new RequestParams(Constants.REGISTER_URL);
        params.addQueryStringParameter("userPhone",phoneNumber);
        params.addQueryStringParameter("code",code);
        params.addQueryStringParameter("password",password);
        x.http().get(params,callBack);
    }
    //修改名字
   public void updateUserName(String userPhone,String userName,LoveHomeCallBack<String> callBack) {
       RequestParams params=new RequestParams(Constants.UPDATE_USERNAME);
       params.addQueryStringParameter("userPhone",userPhone);
       params.addQueryStringParameter("userName",userName);
       x.http().get(params,callBack);
   }
    //修改联系方式
    public void updateConstant(String userPhone,String userContast,LoveHomeCallBack<String> callBack){
        RequestParams params=new RequestParams(Constants.UPDATE_USER_CONSTANT);
        params.addQueryStringParameter("userPhone",userPhone);
        params.addQueryStringParameter("userContast",userContast);
        x.http().get(params,callBack);
    }
    //修改联系地址
    public void updateAddress(String userPhone,String userAddress,LoveHomeCallBack<String> callBack){
        RequestParams params=new RequestParams(Constants.UPDATE_USER_ADDRESS);
        params.addQueryStringParameter("userPhone",userPhone);
        params.addQueryStringParameter("userAddress",userAddress);
        x.http().get(params,callBack);
    }
    //修改密码
    public void updatePassword(String userPhone,String oldPassword,String newPassword,LoveHomeCallBack<String> callBack){
        RequestParams params=new RequestParams(Constants.UPDATE_PASSWORD);
        params.addQueryStringParameter("userPhone",userPhone);
        params.addQueryStringParameter("password",oldPassword);
        params.addQueryStringParameter("newpassword",newPassword);
        x.http().get(params,callBack);
    }
    //忘记密码
    public void forgetPassword(String userPhone,String code,LoveHomeCallBack<String> callBack){
        RequestParams params=new RequestParams(Constants.FORGET_PASSWORD);
        params.addQueryStringParameter("userPhone",userPhone);
        params.addQueryStringParameter("code",code);
        x.http().get(params,callBack);
    }

}
