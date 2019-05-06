package com.example.nice.everywhere.ui.main.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.widget.FrameLayout;


import com.example.nice.everywhere.R;
import com.example.nice.everywhere.base.BaseActivity;
import com.example.nice.everywhere.presenter.LoginPresenter;
import com.example.nice.everywhere.ui.main.fragment.CodeFragment;
import com.example.nice.everywhere.ui.main.fragment.CodeLodingFragment;
import com.example.nice.everywhere.view.main.LoginView;
import com.umeng.socialize.UMShareAPI;

import java.util.ArrayList;

import butterknife.BindView;
//13、区块链  14、比特币（李笑来） 21、智能家居 （王帅）
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
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frag , fragments.get(0));
        transaction.show(fragments.get(0));
        transaction.commit();
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
