package com.example.nice.everywhere.callback;

import com.example.nice.everywhere.bean.BanMiBean;
import com.example.nice.everywhere.bean.HomeBean;

public interface BanmiCallBack {
    void onSuccess(BanMiBean banMiBean);
    void onFailed(String str);
}
