package com.example.nice.everywhere.ui.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nice.everywhere.R;
import com.example.nice.everywhere.bean.BanMiBean;
import com.example.nice.everywhere.bean.BanMiGuanBean;
import com.example.nice.everywhere.net.BanmiService;
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

public class BanmiAdapter extends RecyclerView.Adapter<BanmiAdapter.ViewHolder> {

    //    CenkLIenter cenkLIenter;
    private ArrayList<BanMiBean.ResultBean.BanmiBean> list = new ArrayList<>();
    private Context context;

    public BanmiAdapter(Context context) {
        this.context = context;
    }

    public void update(List<BanMiBean.ResultBean.BanmiBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.banmi_item, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {
        boolean isFollowed = list.get(position).isIsFollowed();
        if (isFollowed){
            viewHolder.img_banmi_heart.setImageResource(R.mipmap.follow);
        }else {
            viewHolder.img_banmi_heart.setImageResource(R.mipmap.follow_unselected);
        }
        viewHolder.txt_banmi_name.setText(list.get(position).getName());
        viewHolder.txt_guanzhu.setText(list.get(position).getFollowing() + "人关注");
        viewHolder.txt_banmi_city.setText(list.get(position).getLocation());
        viewHolder.txt_banmi_occupation.setText(list.get(position).getOccupation());
        Glide.with(context).load(list.get(position).getPhoto()).into(viewHolder.img_banmi);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null){
//                    BanMiBean.ResultBean.BanmiBean banmiBean = list.get(position);
                    onItemClickListener.onItemClick(list.get(position));
                }
            }
        });



        viewHolder.img_banmi_heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(position).isIsFollowed()) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(BanmiService.HomeUrl)
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    BanmiService banmiService = retrofit.create(BanmiService.class);

                    Observable<BanMiGuanBean> banMiGuan = banmiService.getBanMiCancel(list.get(position).getId()+"");

                    banMiGuan.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<BanMiGuanBean>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(BanMiGuanBean banMiGuanBean) {
                                    ToastUtil.showShort(banMiGuanBean.getResult().getMessage());
                                    Glide.with(context)
                                            .load(R.mipmap.follow_unselected)
                                            .into(viewHolder.img_banmi_heart);

                                    Log.d("sddss", "onClick: " + position);
                                    list.get(position).setIsFollowed(false);
                                }

                                @Override
                                public void onError(Throwable e) {
                                    ToastUtil.showShort("伴米关注失败:" + e.getMessage());
                                }

                                @Override
                                public void onComplete() {

                                }
                            });

                } else{
                    Retrofit build = new Retrofit.Builder()
                            .baseUrl(BanmiService.HomeUrl)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build();

                    BanmiService service = build.create(BanmiService.class);

                    Observable<BanMiGuanBean> banMiCancel = service.getBanMiGuan(list.get(position).getId()+"");
                    banMiCancel.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<BanMiGuanBean>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }
                                @Override
                                public void onNext(BanMiGuanBean banMiGuanBean) {
                                   ToastUtil.showShort(banMiGuanBean.getResult().getMessage());
                                    Glide.with(context)
                                            .load(R.mipmap.follow)
                                            .into(viewHolder.img_banmi_heart);
                                    Log.d("sddss", "onClick: " + position);
                                    list.get(position).setIsFollowed(true);
                                }

                                @Override
                                public void onError(Throwable e) {
                                    ToastUtil.showShort("伴米取消关注失败:" + e.getMessage());
                                }

                                @Override
                                public void onComplete() {

                                }
                            });

                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img_banmi;
        private TextView txt_banmi_name;
        private TextView txt_guanzhu;
        private TextView txt_banmi_city;
        private TextView txt_banmi_occupation;
        private ImageView img_banmi_heart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_banmi_heart = itemView.findViewById(R.id.img_banmi_heart);
            img_banmi = itemView.findViewById(R.id.img_banmi);
            txt_banmi_name = itemView.findViewById(R.id.txt_banmi_name);
            txt_guanzhu = itemView.findViewById(R.id.txt_guanzhu);
            txt_banmi_city = itemView.findViewById(R.id.txt_banmi_city);
            txt_banmi_occupation = itemView.findViewById(R.id.txt_banmi_occupation);
        }
    }


    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(BanMiBean.ResultBean.BanmiBean banmiBean);
    }
}
