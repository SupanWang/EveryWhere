package com.example.nice.everywhere.callback;

import com.example.nice.everywhere.bean.HomeBean;

public interface HomeCallBack {

    void onSuccess(HomeBean homeBean);
    void onFailed(String str);
}
