package com.example.nice.everywhere.view.main;

import com.example.nice.everywhere.bean.BanMiDetailBean;

public interface BanMiDetailView {

    void onSuccess(BanMiDetailBean banMiDetailBean);
    void onFailed(String str);
}
