package com.example.lovehometown.common;

/**
 * Created by Administrator on 2016/7/18.
 */

import android.content.Context;

import com.example.lovehometown.constant.Constants;
import com.example.lovehometown.util.SPUtils;

/**
 * 判断是否登录
 */
public class Login {
    private static Login login;
    private Login(){

    }
    public static Login getInstance(){
        if (login==null){
            login=new Login();
        }
        return login;
    }
    //判断是否登录 true已经登录 false没有登录
     public  boolean isLogin(Context context){

         boolean isLogin= (boolean) SPUtils.get(context, Constants.IS_LOGIN,false);
         return isLogin;
     }
}
