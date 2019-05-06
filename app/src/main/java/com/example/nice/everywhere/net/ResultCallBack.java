package com.example.nice.everywhere.net;

/**
 * @author ws
 *         Created by asus on 2019/4/30.
         */

public interface ResultCallBack<T> {
    void onSuccess(T bean);
    void onFail(String msg);
}
