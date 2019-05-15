package com.example.nice.everywhere.ui.main.fragment;


import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nice.everywhere.R;
import com.example.nice.everywhere.base.BaseApp;
import com.example.nice.everywhere.bean.VerifyCodeBean;
import com.example.nice.everywhere.net.ApiService;
import com.example.nice.everywhere.net.BaseObserver;
import com.example.nice.everywhere.net.HttpUtils;
import com.example.nice.everywhere.net.RxUtils;
import com.example.nice.everywhere.ui.main.activity.LoginActivity;
import com.example.nice.everywhere.util.Logger;
import com.example.nice.everywhere.util.ToastUtil;
import com.example.nice.everywhere.widget.IdentifyingCodeView;

import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class CodeLodingFragment extends Fragment {

    private ImageView iv_back;
    private TextView tv_send_again;
    private IdentifyingCodeView icv;
    private TextView tv_wait;
    private String data;
    private String code = "";
    private int mTime;
    private CodeFragment fragment;

    public static CodeLodingFragment newInstance(String code){
        CodeLodingFragment codeLodingFragment = new CodeLodingFragment();
        Bundle bundle = new Bundle();
        bundle.putString("code",code);
        codeLodingFragment.setArguments(bundle);
        return codeLodingFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_code_loding, container, false);
        initView(inflate);

        initData();
        return inflate;
    }

    private void initData() {
        Logger.println(mTime+"");

    }

    public void setCode(String code){
        if (tv_wait != null) {
            tv_wait.setText(code);
        }
    }

    private void initView(View inflate) {
        String code = getArguments().getString("code");
        iv_back = (ImageView) inflate.findViewById(R.id.iv_back);
        tv_send_again = (TextView) inflate.findViewById(R.id.tv_send_again);
        icv = (IdentifyingCodeView) inflate.findViewById(R.id.icv);
        tv_wait = (TextView) inflate.findViewById(R.id.tv_wait);
        setCode(code);

        //碎片手动弹栈
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                //弹栈
                manager.popBackStack();
            }
        });


        tv_send_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_send_again.setEnabled(false);
                //调用它是有条件的
                if (mTime == 0){
                    //重新发起倒计时
                    fragment = (CodeFragment) getActivity().getSupportFragmentManager().findFragmentByTag(LoginActivity.TAG);
                    getVerifyCode();//再次获取验证码
                    fragment.countDown();
                }
            }
        });

        icv.setOnEditorActionListener(new IdentifyingCodeView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                return false;
            }
            @SuppressLint("ResourceAsColor")
            @Override
            public void onTextChanged(String s) {
                if (s!=null) {

//                    icv.setBackgroundColor(R.color.dedede);
                }
                autoLogin();
            }
        });

        icv.setInputCompleteListener(new IdentifyingCodeView.InputCompleteListener() {

            @Override
            public void inputComplete() {
               /* String textContent = icv.getTextContent();
                if (textContent!=null) {
                    icv.setBackgroundColor(R.color.dedede);
                }*/
            }

            @Override
            public void deleteContent() {
                icv.setBackgroundResource(R.color.white);
            }
        });
    }

    private void autoLogin() {
        Logger.println(icv.getTextContent());
        if (icv.getTextContent().length() >= 4) {
            //自动登录
            ToastUtil.showShort("自动登录");
            icv.setBackgroundEnter(false);
            tv_wait.setText(BaseApp.getRes().getString(R.string.wait_please));
        }
    }

    public void setCountDownTime(int time) {
        mTime = time;
        if (getContext() != null){
            if (time != 0){
                String format = String.format(getResources().getString(R.string.send_again) + "(%ss)", time);
                tv_send_again.setText(format);
                tv_send_again.setTextColor(getResources().getColor(R.color.c_999));
            }else {
                tv_send_again.setEnabled(true);
                tv_send_again.setText(getResources().getString(R.string.send_again));
                tv_send_again.setTextColor(getResources().getColor(R.color.c_fa6a13));
            }
        }
    }

    public void getVerifyCode() {
        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.sBaseUrl, ApiService.class);
        final Observable<VerifyCodeBean> verifyCode = apiserver.getVerifyCode();
        verifyCode.compose(RxUtils.<VerifyCodeBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<VerifyCodeBean>() {
                    @Override
                    public void error(String msg) {

                    }

                    @Override
                    protected void subscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(VerifyCodeBean verifyCodeBean) {
                        data = verifyCodeBean.getData();
                        Logger.println(CodeLodingFragment.this.data);
                        if (!TextUtils.isEmpty(CodeLodingFragment.this.data)) {
                            code = data;
                            fragment.mVerifyCode = code;
                            setCode(code);
                        }
                    }
                });

    }
}
