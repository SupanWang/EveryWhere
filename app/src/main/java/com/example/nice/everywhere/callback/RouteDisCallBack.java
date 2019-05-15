package com.example.nice.everywhere.callback;

import com.example.nice.everywhere.bean.DiscussBean;

public interface RouteDisCallBack {
    void onSuccess(DiscussBean discussBean);
    void onFailed(String str);
}
