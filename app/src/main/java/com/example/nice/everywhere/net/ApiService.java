package com.example.nice.everywhere.net;


import com.example.nice.everywhere.bean.VerifyCodeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author ws
 *         Created by asus on 2019/4/30.
 */

public interface ApiService {
    String sBaseUrl = "http://yun918.cn/study/public/index.php/";

    /**
     * 获取验证码
     * @return
     */
    @GET("verify")
    Observable<VerifyCodeBean> getVerifyCode();
}
