package com.example.nice.everywhere.ui.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nice.everywhere.R;
import com.example.nice.everywhere.bean.HomeBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HomeMoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<HomeBean.ResultBean.RoutesBean> list = new ArrayList<>();
    private ArrayList<HomeBean.ResultBean.BannersBean> bannersBeans = new ArrayList<>();
    private Context context;


    public HomeMoreAdapter(Context context) {
        this.context = context;
    }

    public void update(List<HomeBean.ResultBean.RoutesBean> list, List<HomeBean.ResultBean.BannersBean> bannersBeans) {
        this.list.clear();
        this.list.addAll(list);
        this.bannersBeans.clear();
        this.bannersBeans.addAll(bannersBeans);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        RecyclerView.ViewHolder viewHolder = null;
        if (position == 1) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
            viewHolder = new LeftViewHolder(inflate);
        } else {
            View inflate = LayoutInflater.from(context).inflate(R.layout.home_item, null);
            viewHolder = new RightViewHolder(inflate);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof LeftViewHolder) {
            LeftViewHolder leftViewHolder = (LeftViewHolder) viewHolder;
            HomeBean.ResultBean.BannersBean bannersBean = bannersBeans.get(position);

            leftViewHolder.banner.setImages(bannersBeans);
            leftViewHolder.banner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    HomeBean.ResultBean.BannersBean dataBean = (HomeBean.ResultBean.BannersBean) path;
                    Glide.with(context).load(dataBean.getImageURL()).into(imageView);
                }
            });
            leftViewHolder.banner.start();

        } else if (viewHolder instanceof RightViewHolder) {
            RightViewHolder rightViewHolder = (RightViewHolder) viewHolder;

            int ption = position;
            if (bannersBeans.size() > 0) {     //size 大于0的时候
                ption = ption - 1;    //当有了轮播的位置  总长度-1;
            }
            HomeBean.ResultBean.RoutesBean routesBean = list.get(ption);
            rightViewHolder.txt_name.setText(routesBean.getTitle());
            rightViewHolder.txt_city.setText(routesBean.getCity());
            rightViewHolder.btn_price.setText("￥" + routesBean.getPrice());
            rightViewHolder.txt_desc.setText(routesBean.getIntro());
            rightViewHolder.txt_num.setText(routesBean.getPrice());

            Glide.with(context).load(routesBean.getCardURL()).into(rightViewHolder.img_big);

        }
    }


    @Override
    public int getItemCount() {
        if (bannersBeans.size() > 0) {
            return list.size() + 1;   //当banner 长度大于0 返回总长度加1
        } else {
            return list.size();  // 当banner  长度小于
        }


    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 1;
        } else {
            return 2;
        }
    }


    class LeftViewHolder extends RecyclerView.ViewHolder {

        private Banner banner;

        public LeftViewHolder(@NonNull View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
        }
    }


    public class RightViewHolder extends RecyclerView.ViewHolder {

        private TextView txt_name;
        private TextView txt_city;
        private Button btn_price;
        private TextView txt_desc;
        private TextView txt_num;
        private ImageView img_big;


        public RightViewHolder(@NonNull View itemView) {
            super(itemView);
            img_big = itemView.findViewById(R.id.img_big);
            txt_name = itemView.findViewById(R.id.txt_name);
            txt_city = itemView.findViewById(R.id.txt_city);
            btn_price = itemView.findViewById(R.id.btn_price);
            txt_desc = itemView.findViewById(R.id.txt_desc);
            txt_num = itemView.findViewById(R.id.txt_num);
        }
    }
}
