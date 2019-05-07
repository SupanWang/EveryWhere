package com.example.nice.everywhere.ui.main.fragment;


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
import com.example.nice.everywhere.util.Logger;
import com.example.nice.everywhere.util.ToastUtil;
import com.example.nice.everywhere.widget.IdentifyingCodeView;

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

    private int count = 10;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                if (count > 0) {
                    tv_send_again.setText("重新发送" + "(" + count + "s)");
                    count--;
                    initCount(count);//将时间传过去
                    handler.sendEmptyMessageDelayed(1, 1000);
                } else {

                }
            }

        }
    };

    private void initCount(final int count) {
        Logger.println(count+"");
        //碎片手动弹栈
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CodeFragment.setCount(count);//传时间，判断何时才能在发验证码

                /*FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                //添加到回退栈
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.add(R.id.frag,new CodeFragment()).commit();*/
                FragmentManager manager = getActivity().getSupportFragmentManager();
                //获取回退栈中碎片的数量
        /*int backStackEntryCount = manager.getBackStackEntryCount();
        Logger.println("回退栈Fragmnet数量:"+backStackEntryCount);*/
                //弹栈
                manager.popBackStack();



            }
        });
    }


    public CodeLodingFragment() {
        // Required empty public constructor
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
        Logger.println(count+"");
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
                        Logger.println(data);
                        if (!TextUtils.isEmpty(data)) {
                            tv_wait.setText(BaseApp.getRes().getString(R.string.verify_code) + data);
                        }
                    }
                });
    }

    private void initView(View inflate) {
        iv_back = (ImageView) inflate.findViewById(R.id.iv_back);
        tv_send_again = (TextView) inflate.findViewById(R.id.tv_send_again);
        icv = (IdentifyingCodeView) inflate.findViewById(R.id.icv);
        tv_wait = (TextView) inflate.findViewById(R.id.tv_wait);

        handler.sendEmptyMessageDelayed(1, 1000);   //倒计时监听




        icv.setOnEditorActionListener(new IdentifyingCodeView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                return false;
            }

            @Override
            public void onTextChanged(String s) {
                autoLogin();
            }
        });
    }


 /*   public void setData(String data) {
        if (!TextUtils.isEmpty(data)) {
            tv_wait.setText(BaseApp.getRes().getString(R.string.verify_code) + data);
        }
    }*/

    private void autoLogin() {
        Logger.println(icv.getTextContent());
        if (icv.getTextContent().length() >= 4) {
            //自动登录
            ToastUtil.showShort("自动登录");
            icv.setBackgroundEnter(false);
            tv_wait.setText(BaseApp.getRes().getString(R.string.wait_please));
        }
    }
}
