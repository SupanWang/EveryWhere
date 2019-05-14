package com.example.nice.everywhere.ui.main.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.nice.everywhere.R;
import com.example.nice.everywhere.bean.CollectQueryBean;
import com.example.nice.everywhere.net.HomeService;
import com.example.nice.everywhere.ui.main.adapter.RouteCollectAdapter;
import com.example.nice.everywhere.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RouteCollectActivity extends AppCompatActivity {

    private TextView txt_tool_mine;
    private Toolbar toolbar;
    private RecyclerView rv_collect;
    private ArrayList<CollectQueryBean.ResultBean.CollectedRoutesBean> list;
    private RouteCollectAdapter collectAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_collect);
        initView();
        initData();
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HomeService.HomeUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HomeService homeService = retrofit.create(HomeService.class);

        final Observable<CollectQueryBean> collectQuery = homeService.getCollectQuery(1);
        collectQuery.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CollectQueryBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CollectQueryBean collectQueryBean) {
                        List<CollectQueryBean.ResultBean.CollectedRoutesBean> collectedRoutes = collectQueryBean.getResult().getCollectedRoutes();
                        list.addAll(collectedRoutes);
                        collectAdapter.update(list);
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showShort(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView() {
        txt_tool_mine = (TextView) findViewById(R.id.txt_tool_mine);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        rv_collect = (RecyclerView) findViewById(R.id.rv_collect);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        list = new ArrayList<>();
        collectAdapter = new RouteCollectAdapter(this);
        rv_collect.setLayoutManager(new LinearLayoutManager(this));
        rv_collect.setAdapter(collectAdapter);

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
