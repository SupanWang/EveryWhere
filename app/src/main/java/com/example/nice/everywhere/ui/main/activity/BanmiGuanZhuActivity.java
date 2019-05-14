package com.example.nice.everywhere.ui.main.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.nice.everywhere.R;
import com.example.nice.everywhere.bean.BanMiBean;
import com.example.nice.everywhere.net.BanmiService;
import com.example.nice.everywhere.ui.main.adapter.BanmiAdapter;
import com.example.nice.everywhere.util.ToastUtil;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class BanmiGuanZhuActivity extends AppCompatActivity {

    private TextView txt_tool_mine;
    private Toolbar toolbar;
    private RecyclerView rv_ban_dao;
    private BanmiAdapter adapter;
    private ArrayList<BanMiBean.ResultBean.BanmiBean> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banmi_collect);
        initView();
        initData();
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BanmiService.HomeUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BanmiService banmiService = retrofit.create(BanmiService.class);

        Observable<BanMiBean> banQuery = banmiService.getBanQuery(1);

        banQuery.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BanMiBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BanMiBean banMiBean) {
                        list.addAll(banMiBean.getResult().getBanmi());
                        adapter.update(list);
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showShort("查看失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    private void initView() {
        txt_tool_mine = (TextView) findViewById(R.id.txt_tool_mine);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        rv_ban_dao = (RecyclerView) findViewById(R.id.rv_ban_dao);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        list = new ArrayList<>();
        adapter = new BanmiAdapter(this);
        rv_ban_dao.setLayoutManager(new LinearLayoutManager(this));
        rv_ban_dao.setAdapter(adapter);


        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
