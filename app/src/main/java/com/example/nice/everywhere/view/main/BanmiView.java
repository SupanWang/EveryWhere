package com.example.nice.everywhere.view.main;

import com.example.nice.everywhere.bean.BanMiBean;

public interface BanmiView {
    void onSuccess(BanMiBean banMiBean);
    void onFailed(String str);
}
