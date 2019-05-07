package com.example.nice.everywhere.ui.main.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.widget.FrameLayout;


import com.example.nice.everywhere.R;
import com.example.nice.everywhere.base.BaseActivity;
import com.example.nice.everywhere.base.Constants;
import com.example.nice.everywhere.presenter.LoginPresenter;
import com.example.nice.everywhere.ui.main.fragment.CodeFragment;
import com.example.nice.everywhere.ui.main.fragment.CodeLodingFragment;
import com.example.nice.everywhere.util.Tools;
import com.example.nice.everywhere.view.main.LoginView;
import com.umeng.socialize.UMShareAPI;

import java.util.ArrayList;

import butterknife.BindView;

//5ccb18824ca357d28d0000cf   AppKey
public class LoginActivity extends BaseActivity<LoginView, LoginPresenter> implements LoginView {
    @BindView(R.id.frag)
    FrameLayout frag;
    private FragmentManager manager;
    private ArrayList<Fragment> fragments;
    private final int CODE = 0;
    private final int CODELOING = 1;
    //上一次显示的fragmnet的索引
    private int mLastFragmentPosition = 0;

    public static final int TYPE_LOGIN = 0;
    public static final int TYPE_BIND = 1;
    private int mType;

    /**
     * 启动当前Activiy
     * @param context
     * @param type 如果是0:代表登录界面;1:代表要绑定手机
     */
    public static void startAct(Context context , int type){
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra(Constants.TYPE,type);
        context.startActivity(intent);
    }

    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        manager = getSupportFragmentManager();
        fragments = new ArrayList<>();
        fragments.add(new CodeFragment());
        fragments.add(new CodeLodingFragment());

        getIntentData();

        FragmentManager manager = getSupportFragmentManager();
        CodeFragment fragment = CodeFragment.newIntance(mType);
        manager.beginTransaction().add(R.id.frag, fragment).commit();

//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.add(R.id.frag , fragments.get(0));
//        transaction.show(fragments.get(0));
//        transaction.commit();
    }

    private void getIntentData() {
        mType = getIntent().getIntExtra(Constants.TYPE, TYPE_LOGIN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //内存泄漏解决方案
        UMShareAPI.get(this).release();
    }
}
