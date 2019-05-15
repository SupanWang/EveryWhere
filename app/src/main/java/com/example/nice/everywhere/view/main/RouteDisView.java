package com.example.nice.everywhere.view.main;

import com.example.nice.everywhere.bean.DiscussBean;

public interface RouteDisView {
    void onSuccess(DiscussBean discussBean);
    void onFailed(String str);
}
