package com.example.nice.everywhere.callback;

import com.example.nice.everywhere.bean.BanMiDetailBean;

public interface BanMiDetailCallBack {
    void onSuccess(BanMiDetailBean banMiDetailBean);
    void onFailed(String str);
}
