package com.example.nice.everywhere.view.main;

import com.example.nice.everywhere.bean.HomeBean;

public interface HomeView {
    void onSuccess(HomeBean homeBean);
    void onFailed(String str);
}
