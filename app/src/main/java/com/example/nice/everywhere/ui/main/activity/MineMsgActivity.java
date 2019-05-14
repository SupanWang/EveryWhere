package com.example.nice.everywhere.ui.main.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.nice.everywhere.R;
import com.example.nice.everywhere.bean.UpDateBean;
import com.example.nice.everywhere.net.BaseObserver;
import com.example.nice.everywhere.net.RxUtils;
import com.example.nice.everywhere.net.UserService;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MineMsgActivity extends AppCompatActivity {

    int NICHENGTYPE = 30;
    int QINMINGNTYPE = 20;
    private TextView txt_tool_mine;
    private TextView txt_finish;
    private Toolbar toolbar;
    private EditText txt_dialog_name;
    private EditText txt_num;
    private String name;
    private Intent intent;
    private int type;
    private RelativeLayout rlv_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_msg);
        intent = getIntent();
        type = intent.getIntExtra("type", 0);
        name = intent.getStringExtra("name");
        initView();
    }

    private void initView() {
        txt_tool_mine = (TextView) findViewById(R.id.txt_tool_mine);
        txt_finish = (TextView) findViewById(R.id.txt_finish);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txt_dialog_name = (EditText) findViewById(R.id.txt_dialog_name);
        txt_num = (EditText) findViewById(R.id.txt_num);


        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //ToolBar返回按钮
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        final ArrayList<String> strings = new ArrayList<>();
        strings.add("修改昵称");
        strings.add("个性签名");

        if (type == NICHENGTYPE) {
            txt_tool_mine.setText(strings.get(0));
        } else {
            txt_tool_mine.setText(strings.get(1));
        }

        txt_dialog_name.setText(name);
        txt_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == NICHENGTYPE) {

                    String dialogName = txt_dialog_name.getText().toString();
                    upDataName(dialogName);
                    intent.putExtra("dialogName", dialogName);
                    setResult(NICHENGTYPE, intent);
                } else if (type == QINMINGNTYPE) {
                    txt_tool_mine.setText(strings.get(1));
                    String dialogName = txt_dialog_name.getText().toString();
                    updateDesc(dialogName);
                    intent.putExtra("dialogName", dialogName);
                    setResult(QINMINGNTYPE, intent);
                }
            }
        });

/*
        rlv_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_dialog_name.requestFocus();
                txt_dialog_name.setFocusable( true );
                txt_dialog_name.performClick();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(txt_dialog_name, InputMethodManager.SHOW_IMPLICIT);
            }
        });*/
        int length = name.length();
        txt_num.setText(27-length+"/27");

        //设置EditText的光标默认在尾部
        txt_dialog_name.setSelection(txt_dialog_name.getText().length());

        txt_dialog_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txt_num.setText(27 - (s.length()) + "/27");
//                txt_dialog_name.setSelection(s.toString().length());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        rlv_update = (RelativeLayout) findViewById(R.id.rlv_update);
    }

    private void updateDesc(final String dialogName) {
        Retrofit Desc = new Retrofit.Builder()
                .baseUrl(UserService.UserUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserService service = Desc.create(UserService.class);
        Observable<UpDateBean> qianMing = service.getUpdateQianMing(dialogName);
        qianMing.compose(RxUtils.<UpDateBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<UpDateBean>() {
                    @Override
                    public void onNext(UpDateBean upDateBean) {

                        finish();
                    }

                    @Override
                    public void error(String msg) {

                    }

                    @Override
                    protected void subscribe(Disposable d) {

                    }
                });
    }


    private void upDataName(String dialogName) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UserService.UserUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        UserService userService = retrofit.create(UserService.class);
        Observable<UpDateBean> updateName = userService.getUpdateName(dialogName);
        updateName.compose(RxUtils.<UpDateBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<UpDateBean>() {
                    @Override
                    public void onNext(UpDateBean o) {
                        finish();
                    }

                    @Override
                    public void error(String msg) {
                    }

                    @Override
                    protected void subscribe(Disposable d) {

                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
