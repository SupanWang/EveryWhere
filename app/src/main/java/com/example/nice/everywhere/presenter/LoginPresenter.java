package com.example.nice.everywhere.presenter;


import com.example.nice.everywhere.base.BasePresenter;
import com.example.nice.everywhere.bean.VerifyCodeBean;
import com.example.nice.everywhere.model.LoginModel;
import com.example.nice.everywhere.net.ResultCallBack;
import com.example.nice.everywhere.view.main.LoginView;

/**
 * @author xts
 *         Created by asus on 2019/4/29.
 */

public class LoginPresenter extends BasePresenter<LoginView> {
    private LoginModel mLoginModel;

    @Override
    protected void initModel() {
        mLoginModel = new LoginModel();
        mModels.add(mLoginModel);
    }

    public void getVerifyCode() {
        mLoginModel.getVerifyCode(new ResultCallBack<VerifyCodeBean>() {
            @Override
            public void onSuccess(VerifyCodeBean bean) {

            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
