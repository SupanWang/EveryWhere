package com.example.nice.everywhere.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.nice.everywhere.R;
import com.example.nice.everywhere.bean.RouteCollectBean;
import com.example.nice.everywhere.bean.RouteDetalBean;
import com.example.nice.everywhere.model.RouteModelImpl;
import com.example.nice.everywhere.net.HomeService;
import com.example.nice.everywhere.presenter.RoutePresenter;
import com.example.nice.everywhere.presenter.RoutePresenterImpl;
import com.example.nice.everywhere.util.Logger;
import com.example.nice.everywhere.util.ToastUtil;
import com.example.nice.everywhere.view.main.RouteView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

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

public class HomeRouteActivity extends AppCompatActivity implements RouteView, View.OnClickListener {

    private ArrayList<RouteDetalBean.ResultBean.RouteBean> routeBeans;
    private ArrayList<RouteDetalBean.ResultBean.BanmiBean> banmiBeans;
    private ArrayList<RouteDetalBean.ResultBean.ReviewsBean> reviewsBeans;
    private TextView txt_route_city;
    private TextView txt_route_name;
    private TextView txt_route_desc;
    private ImageView img_big_home;
    private ImageView img_banmi;
    private TextView txt_banmi_name;
    private TextView txt_banmi_work;
    private TextView txt_banmi_city;
    private TextView txt_banmi_desc;
    private ImageView img_talk1;
    private TextView txt_talk1_user;
    private TextView txt_talk1_time;
    private TextView txt_talk1_desc;
    private ImageView img_talk2;
    private TextView txt_talk2_user;
    private TextView txt_talk2_time;
    private TextView txt_talk2_biaoqing;
    private ImageView img_talk3;
    private TextView txt_talk3_user;
    private TextView txt_talk3_time;
    private TextView txt_talk3_desc;
    private TextView txt_pingjia;
    private Button mBtn_share;
    private Button mBtn_collect;
    private RelativeLayout rlv_gongneng;
    private Button mBtn_route;
    private Button mBtn_price;
    private ScrollView scroll;


    private RouteDetalBean.ResultBean.RouteBean route;
    private RouteDetalBean.ResultBean.BanmiBean banmi;
    private List<RouteDetalBean.ResultBean.ReviewsBean> reviews;
    private String id;
    private FloatingActionButton floatbutton;
    private boolean isCollected;
    private Button mBtn_Yicollect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_route);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        initView();
        initData();
    }

    private void initData() {
        RoutePresenter routePresenter = new RoutePresenterImpl(new RouteModelImpl(), this);
        routePresenter.getRouteList(id);
    }

    private void initView() {
        txt_route_city = (TextView) findViewById(R.id.txt_route_city);
        txt_route_city.setOnClickListener(this);
        txt_route_name = (TextView) findViewById(R.id.txt_route_name);
        txt_route_name.setOnClickListener(this);
        txt_route_desc = (TextView) findViewById(R.id.txt_route_desc);
        txt_route_desc.setOnClickListener(this);
        img_big_home = (ImageView) findViewById(R.id.img_big_home);
        img_big_home.setOnClickListener(this);
        img_banmi = (ImageView) findViewById(R.id.img_banmi);
        img_banmi.setOnClickListener(this);
        txt_banmi_name = (TextView) findViewById(R.id.txt_banmi_name);
        txt_banmi_name.setOnClickListener(this);
        txt_banmi_work = (TextView) findViewById(R.id.txt_banmi_work);
        txt_banmi_work.setOnClickListener(this);
        txt_banmi_city = (TextView) findViewById(R.id.txt_banmi_city);
        txt_banmi_city.setOnClickListener(this);
        txt_banmi_desc = (TextView) findViewById(R.id.txt_banmi_desc);
        txt_banmi_desc.setOnClickListener(this);
        img_talk1 = (ImageView) findViewById(R.id.img_talk1);
        img_talk1.setOnClickListener(this);
        txt_talk1_user = (TextView) findViewById(R.id.txt_talk1_user);
        txt_talk1_user.setOnClickListener(this);
        txt_talk1_time = (TextView) findViewById(R.id.txt_talk1_time);
        txt_talk1_time.setOnClickListener(this);
        txt_talk1_desc = (TextView) findViewById(R.id.txt_talk1_desc);
        txt_talk1_desc.setOnClickListener(this);
        img_talk2 = (ImageView) findViewById(R.id.img_talk2);
        img_talk2.setOnClickListener(this);
        txt_talk2_user = (TextView) findViewById(R.id.txt_talk2_user);
        txt_talk2_user.setOnClickListener(this);
        txt_talk2_time = (TextView) findViewById(R.id.txt_talk2_time);
        txt_talk2_time.setOnClickListener(this);
        txt_talk2_biaoqing = (TextView) findViewById(R.id.txt_talk2_biaoqing);
        txt_talk2_biaoqing.setOnClickListener(this);
        img_talk3 = (ImageView) findViewById(R.id.img_talk3);
        img_talk3.setOnClickListener(this);
        txt_talk3_user = (TextView) findViewById(R.id.txt_talk3_user);
        txt_talk3_user.setOnClickListener(this);
        txt_talk3_time = (TextView) findViewById(R.id.txt_talk3_time);
        txt_talk3_time.setOnClickListener(this);
        txt_talk3_desc = (TextView) findViewById(R.id.txt_talk3_desc);
        txt_talk3_desc.setOnClickListener(this);
        txt_pingjia = (TextView) findViewById(R.id.txt_pingjia);
        txt_pingjia.setOnClickListener(this);
        mBtn_share = (Button) findViewById(R.id.mBtn_share);
        mBtn_share.setOnClickListener(this);
        mBtn_collect = (Button) findViewById(R.id.mBtn_collect);
        mBtn_collect.setOnClickListener(this);
        rlv_gongneng = (RelativeLayout) findViewById(R.id.rlv_gongneng);
        rlv_gongneng.setOnClickListener(this);
        mBtn_route = (Button) findViewById(R.id.mBtn_route);
        mBtn_route.setOnClickListener(this);
        mBtn_price = (Button) findViewById(R.id.mBtn_price);
        mBtn_price.setOnClickListener(this);
        scroll = (ScrollView) findViewById(R.id.scroll);
        scroll.setOnClickListener(this);
        mBtn_Yicollect = (Button) findViewById(R.id.mBtn_Yicollect);
        mBtn_Yicollect.setOnClickListener(this);
        floatbutton = (FloatingActionButton) findViewById(R.id.floatbutton);

        floatbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        list();
    }

    private void list() {
        routeBeans = new ArrayList<>();
        reviewsBeans = new ArrayList<>();
        banmiBeans = new ArrayList<>();
    }

    @Override
    public void onSuccess(RouteDetalBean routeDetalBean) {
        route = routeDetalBean.getResult().getRoute();
        banmi = routeDetalBean.getResult().getBanmi();
        reviews = routeDetalBean.getResult().getReviews();

        //路线
        txt_route_city.setText(route.getCity());
        txt_route_name.setText(route.getTitle());
        txt_route_desc.setText(route.getIntro());
        mBtn_price.setText("￥" + route.getPrice());
        RoundedCorners roundedCorners = new RoundedCorners(15);//数字为圆角度数
        RequestOptions coverRequestOptions = new RequestOptions()
                .transforms(roundedCorners)
                .placeholder(R.drawable.zhanweitu_home_kapian)   //占位图
                .diskCacheStrategy(DiskCacheStrategy.ALL)//不做磁盘缓存
                .skipMemoryCache(true);//不做内存缓存

        Glide.with(this).load(route.getCardURL()).apply(coverRequestOptions).into(img_big_home);

        //伴米
        RequestOptions options = RequestOptions.circleCropTransform();
        Glide.with(this).load(banmi.getPhoto()).apply(options).into(img_banmi);
        txt_banmi_name.setText(banmi.getName());
        txt_banmi_work.setText(banmi.getOccupation());
        txt_banmi_city.setText(banmi.getLocation());
        txt_banmi_desc.setText(banmi.getIntroduction());

        //用户评价
        Glide.with(this).load(reviews.get(0).getUserPhoto()).apply(options).into(img_talk1);
        txt_talk1_user.setText(reviews.get(0).getUserName());
        txt_talk1_time.setText(reviews.get(0).getCreatedAt());
        txt_talk1_desc.setText(reviews.get(0).getContent());

        //用户评价2
        Glide.with(this).load(reviews.get(1).getUserPhoto()).apply(options).into(img_talk2);
        txt_talk2_user.setText(reviews.get(1).getUserName());
        txt_talk2_time.setText(reviews.get(1).getCreatedAt());
        txt_talk2_biaoqing.setText(reviews.get(1).getContent());

        //用户评价3
        Glide.with(this).load(reviews.get(2).getUserPhoto()).apply(options).into(img_talk3);
        txt_talk3_user.setText(reviews.get(2).getUserName());
        txt_talk3_time.setText(reviews.get(2).getCreatedAt());
        txt_talk3_desc.setText(reviews.get(2).getContent());

        isCollected = route.isIsCollected();
        if (isCollected) {
            mBtn_Yicollect.setVisibility(View.VISIBLE);
            mBtn_collect.setVisibility(View.GONE);
        } else {
            mBtn_collect.setVisibility(View.VISIBLE);
            mBtn_Yicollect.setVisibility(View.GONE);
        }
    }

    @Override
    public void onFalied(String str) {
        Logger.println(str);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mBtn_share:
                shareBorad();//分享
                break;
            case R.id.mBtn_collect:
                isCollected = route.isIsCollected();
                if (isCollected) {
                    mBtn_Yicollect.setVisibility(View.VISIBLE);
                    mBtn_collect.setVisibility(View.GONE);
                } else {
                    mBtn_collect.setVisibility(View.VISIBLE);
                    mBtn_Yicollect.setVisibility(View.GONE);
                }
                collectRoute();//收藏线路
                break;
            case R.id.mBtn_Yicollect:
               if (isCollected) {
                   mBtn_collect.setVisibility(View.VISIBLE);
                   mBtn_Yicollect.setVisibility(View.GONE);
               }else {
                   mBtn_Yicollect.setVisibility(View.VISIBLE);
                   mBtn_collect.setVisibility(View.GONE);
               }
                collectRoute();//收藏线路
                break;
            case R.id.mBtn_route:
            break;
            case R.id.mBtn_price:
            break;
            case R.id.floatbutton:
            break;
        }
    }

    private void shareBorad() {
        UMImage thumb =  new UMImage(this, route.getShareImageWechat());
        //
        thumb.compressStyle = UMImage.CompressStyle.SCALE;//大小压缩，默认为大小压缩，
        new ShareAction(HomeRouteActivity.this).withText(route.getShareContent())
                .withMedia(thumb).
                setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN)
                .setCallback(umShareListener).open();
    }

    private UMShareListener umShareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(HomeRouteActivity.this,"成功了",Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(HomeRouteActivity.this,"失败"+t.getMessage(),Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(HomeRouteActivity.this,"取消了",Toast.LENGTH_LONG).show();

        }
    };

    private void collectRoute() {
        if (isCollected) {
            Retrofit build = new Retrofit.Builder()
                    .baseUrl(HomeService.HomeUrl)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            HomeService homeService = build.create(HomeService.class);

            Observable<RouteCollectBean> collectRoute = homeService.getCollectRouteDis(id);

            collectRoute.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<RouteCollectBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(RouteCollectBean routeCollectBean) {
                            ToastUtil.showShort("取消收藏成功");
                            mBtn_collect.setVisibility(View.VISIBLE);
                            mBtn_Yicollect.setVisibility(View.GONE);
                            route.setIsCollected(false);
                        }

                        @Override
                        public void onError(Throwable e) {
                            ToastUtil.showShort("取消收藏失败:" + e.getMessage());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        } else {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(HomeService.HomeUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            HomeService service = retrofit.create(HomeService.class);

            Observable<RouteCollectBean> collectRouteDis = service.getCollectRoute(id);

            collectRouteDis.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<RouteCollectBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(RouteCollectBean routeCollectBean) {
                            ToastUtil.showShort("收藏成功");
                            mBtn_Yicollect.setVisibility(View.VISIBLE);
                            mBtn_collect.setVisibility(View.GONE);
                            route.setIsCollected(true);
                        }

                        @Override
                        public void onError(Throwable e) {
                            ToastUtil.showShort("收藏失败:" + e.getMessage());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }

}
