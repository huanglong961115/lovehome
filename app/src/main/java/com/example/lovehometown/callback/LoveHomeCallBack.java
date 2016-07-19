package com.example.lovehometown.callback;

import org.xutils.common.Callback;

/**
 * Created by Administrator on 2016/7/19.
 */
public abstract class LoveHomeCallBack<T> implements Callback.CommonCallback<T>{
    @Override
    public abstract void onSuccess(T result);

    @Override
    public abstract void onError(Throwable ex, boolean isOnCallback) ;

    @Override
    public void onCancelled(CancelledException cex) {

    }

    @Override
    public void onFinished() {

    }
}
