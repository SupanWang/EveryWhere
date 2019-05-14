package com.example.nice.everywhere.ui.main.fragment;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nice.everywhere.R;
import com.example.nice.everywhere.base.Constants;
import com.example.nice.everywhere.bean.LoginInfo;
import com.example.nice.everywhere.bean.VerifyCodeBean;
import com.example.nice.everywhere.net.ApiService;
import com.example.nice.everywhere.net.BaseObserver;
import com.example.nice.everywhere.net.EveryWhereService;
import com.example.nice.everywhere.net.HttpUtils;
import com.example.nice.everywhere.net.RxUtils;
import com.example.nice.everywhere.ui.main.activity.LoginActivity;
import com.example.nice.everywhere.ui.main.activity.MainActivity;
import com.example.nice.everywhere.ui.main.activity.WebActivity;
import com.example.nice.everywhere.util.Logger;
import com.example.nice.everywhere.util.ToastUtil;
import com.example.nice.everywhere.util.Tools;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class CodeFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "CodeFragment";
    private static Button btn_send_verify;
    private static int COUNT_DOWN_TIME = 10;
    //验证码
    public String mVerifyCode = "";

    UMAuthListener authListener = new UMAuthListener() {

        /*@desc 授权开始的回调
        @param platform 平台名称*/
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }
        /*@desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回*/

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

            for (Map.Entry<String, String> entry : data.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                Log.d(TAG, "key: " + key + "," + "value :" + value);
            }
            Toast.makeText(getActivity(), "成功了", Toast.LENGTH_SHORT).show();

            SharedPreferences sp = getActivity().getSharedPreferences("isSuccess", Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = sp.edit();
            edit.putBoolean("isSuccess", false);
            edit.commit();
            //只写微博的,微信的成功不了
            if (platform == SHARE_MEDIA.SINA) {
                loginSina(data.get("uid"));
            }
        }


        /* @desc 授权失败的回调
        @param platform 平台名称
        @param action 行为序号，开发者用不上
        @param t 错误原因*/
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(getActivity(), "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /*
        @desc 授权取消的回调
        @param platform 平台名称
        @param action 行为序号，开发者用不上
        */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(getActivity(), "取消了", Toast.LENGTH_LONG).show();
        }
    };
    private ImageView iv_back;
    private TextView tv_hello;
    private TextView tv_login;
    private TextView tv_coutry_code;
    private EditText et_phone;
    private LinearLayout ll_container;
    private View view;
    private LinearLayout ll_or;
    private ImageView iv_wechat;
    private ImageView iv_qq;
    private ImageView iv_sina;
    private TextView tv_protocol;
    private LinearLayout ll_oauth;
    private CodeLodingFragment codeLodingFragment;
    private String imgUrl = "http://tvax4.sinaimg.cn/crop.0.0.664.664.50/006rTk8Wly8fofptfjs0oj30ig0igt9k.jpg";
    //因为登录和绑定手机号码是用的一个碎片,所以需要使用type隐藏和显示某一些view
    private int mType;
    //如果是0:代表登录界面;1:代表要绑定手机
    private boolean isSuccess = false;
    private int mTime = COUNT_DOWN_TIME;
    private Handler mHandler;
    private String data;


    public CodeFragment() {
        // Required empty public constructor
    }

    public static CodeFragment newIntance(int type) {
        CodeFragment fragment = new CodeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.TYPE, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_code, container, false);
        initPer();
        initView(inflate);
        return inflate;
    }

    //权限处理
    private void initPer() {
        String[] per = {Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

        ActivityCompat.requestPermissions(getActivity(), per, 100);
    }

    private void initView(View inflate) {
        iv_back = (ImageView) inflate.findViewById(R.id.iv_back);
        tv_hello = (TextView) inflate.findViewById(R.id.tv_hello);
        tv_login = (TextView) inflate.findViewById(R.id.tv_login);
        tv_coutry_code = (TextView) inflate.findViewById(R.id.tv_coutry_code);
        et_phone = (EditText) inflate.findViewById(R.id.et_phone);
        btn_send_verify = (Button) inflate.findViewById(R.id.btn_send_verify);
        ll_container = (LinearLayout) inflate.findViewById(R.id.ll_container);
        view = (View) inflate.findViewById(R.id.view);
        ll_or = (LinearLayout) inflate.findViewById(R.id.ll_or);
        iv_wechat = (ImageView) inflate.findViewById(R.id.iv_wechat);
        iv_qq = (ImageView) inflate.findViewById(R.id.iv_qq);
        iv_sina = (ImageView) inflate.findViewById(R.id.iv_sina);
        tv_protocol = (TextView) inflate.findViewById(R.id.tv_protocol);
        ll_oauth = (LinearLayout) inflate.findViewById(R.id.ll_oauth);

        btn_send_verify.setOnClickListener(this);

        iv_qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showShort("三方QQ登录");
                login(SHARE_MEDIA.QQ);//QQ登录
            }
        });

        iv_sina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showShort("三方微博登录");
                login(SHARE_MEDIA.SINA);//微信登录
            }
        });

        initListener();//文本框的监听

        getArgumentsData();   //
        setProtocol();    //设置协议
        showOrHideView();  //显示隐藏View

    }

    /**
     * 因为登录和绑定手机号码是用的一个碎片,所以需要使用type隐藏和显示某一些view
     */
    private void showOrHideView() {
        if (mType == LoginActivity.TYPE_LOGIN) {
            //登录
            //View.VISIBLE 显示
            //View.INVISIBLE 隐藏,占位置
            //View.GONE 隐藏 不占位置
            iv_back.setVisibility(View.INVISIBLE);
            ll_oauth.setVisibility(View.VISIBLE);
            ll_or.setVisibility(View.VISIBLE);
        } else {
            //绑定
            iv_back.setVisibility(View.VISIBLE);
            ll_oauth.setVisibility(View.GONE);
            ll_or.setVisibility(View.GONE);
        }
    }

    private void getArgumentsData() {
        Bundle arguments = getArguments();
        mType = arguments.getInt(Constants.TYPE);
    }

    private void setProtocol() {
        //图文混排
        SpannableStringBuilder ss = new SpannableStringBuilder(getResources().getString(R.string.agree_protocol));

        //设置下划线
        UnderlineSpan underlineSpan = new UnderlineSpan();
        ss.setSpan(underlineSpan, 12, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                ToastUtil.showShort("点击率");
                WebActivity.startAct(getActivity());
            }
        };
        ss.setSpan(clickableSpan, 12, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置前景色
        ForegroundColorSpan what = new ForegroundColorSpan(getResources().getColor(R.color.c_fa6a13));
        ss.setSpan(what, 12, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        //需要设置这个ClickableSpan才会有效果
        tv_protocol.setMovementMethod(LinkMovementMethod.getInstance());

        tv_protocol.setText(ss);
    }

    private void initListener() {
        //文本发生改变监听
        et_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                switchBtnState(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * 根据输入框中是否有内容,切换发送验证码的背景
     *
     * @param s
     */
    private void switchBtnState(CharSequence s) {
        if (TextUtils.isEmpty(s)) {
            btn_send_verify.setBackgroundResource(R.drawable.bg_btn_ea_r15);
        } else {
            btn_send_verify.setBackgroundResource(R.drawable.bg_btn_fa6a13_r15);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send_verify:
                getVerifyCode();
                if (mTime == COUNT_DOWN_TIME) {
                    countDown();
                }
                addVerifyFragment();
                break;
        }
    }

    private void time() {
        //避免多次执行倒计时
        if (mTime > 0 && mTime < COUNT_DOWN_TIME) {
            return;
        }
        getVerifyCode();
    }

    /*
    倒计时，如果执行中，不要在调用
     */
    public void countDown() {
        if (mHandler == null) {
            mHandler = new Handler();
        }
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //避免倒计时变成负值
                if (mTime <= 0) {
                    mTime = COUNT_DOWN_TIME;
                    return;
                }
                mTime--;
                if (codeLodingFragment != null) {
                    codeLodingFragment.setCountDownTime(mTime);
                }
                countDown();
            }
        }, 1000);
    }

    private void getVerifyCode() {
        if (mTime > 0 && mTime < COUNT_DOWN_TIME) {
            //倒计时中
            return;
        }
        //获取验证码
        getVertify();
    }

    private void getVertify() {
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
                            mVerifyCode = data;
                            codeLodingFragment.setCode(data);
                        }
                    }
                });
    }

    private void addVerifyFragment() {
        if (TextUtils.isEmpty(getPhone())) {
            ToastUtil.showShort("请先输入手机号");
            return;
        }
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        //添加到回退栈
        fragmentTransaction.addToBackStack(null);
        codeLodingFragment = CodeLodingFragment.newInstance(mVerifyCode);
        fragmentTransaction.add(R.id.frag, codeLodingFragment).commit();

        //关闭软键盘
        Tools.closeKeyBoard(getActivity());
    }

    public String getPhone() {
        return et_phone.getText().toString().trim();
    }

    //三方登录
    private void login(SHARE_MEDIA type) {
        UMShareAPI umShareAPI = UMShareAPI.get(getActivity());
        umShareAPI.getPlatformInfo(getActivity(), type, authListener);
    }

    private void loginSina(String uid) {
        EveryWhereService apiserver = HttpUtils.getInstance().getApiserver(EveryWhereService.BASE_URL, EveryWhereService.class);
        apiserver.postWeiboLogin(uid)
                .compose(RxUtils.<LoginInfo>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<LoginInfo>() {
                    @Override
                    public void onNext(LoginInfo loginInfo) {
                        if (loginInfo != null) {
                            if (loginInfo.getCode() == EveryWhereService.SUCCESS_CODE) {
                                Log.d(TAG, "onNext: " + loginInfo);

                                LoginInfo.ResultBean result = loginInfo.getResult();
                                String photo = result.getPhoto();
                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                intent.putExtra("photo", photo);
                                startActivity(intent);
                            } else {
                                ToastUtil.showShort(loginInfo.getDesc());
                            }
                        }
                    }

                    @Override
                    public void error(String msg) {

                    }

                    @Override
                    protected void subscribe(Disposable d) {

                    }
                });
    }
}
