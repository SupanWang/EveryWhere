package com.example.nice.everywhere.ui.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.nice.everywhere.R;
import com.example.nice.everywhere.bean.RouteTypeBean;

import java.util.ArrayList;
import java.util.List;

public class RouteTypeAdapter extends RecyclerView.Adapter<RouteTypeAdapter.ViewHolder> {

    //    CenkLIenter cenkLIenter;
    private ArrayList<RouteTypeBean.ResultBean.BundlesBean> list = new ArrayList<>();
    private Context context;


    public RouteTypeAdapter(Context context) {
        this.context = context;
    }

    public void update(List<RouteTypeBean.ResultBean.BundlesBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.route_type__item, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {

        final RouteTypeBean.ResultBean.BundlesBean bundlesBean = list.get(position);
        RoundedCorners cornes = new RoundedCorners(15);//数字为圆角度数
        final RequestOptions options = new RequestOptions()
                .transforms(cornes)
                .placeholder(R.drawable.zhanweitu_xianlu_jingdian)   //占位图
                .diskCacheStrategy(DiskCacheStrategy.NONE)//不做磁盘缓存
                .skipMemoryCache(true);//不做内存缓存

        Glide.with(context).load(bundlesBean.getCardURL()).apply(options).into(viewHolder.img_type);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemTypeListener!=null){
                    onItemTypeListener.onItemType(bundlesBean);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img_type;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_type = itemView.findViewById(R.id.img_type);
        }
    }


    private OnItemTypeListener onItemTypeListener;

    public void setOnItemTypeListener(OnItemTypeListener onItemTypeListener) {
        this.onItemTypeListener = onItemTypeListener;
    }

    public interface OnItemTypeListener{
        void onItemType(RouteTypeBean.ResultBean.BundlesBean bundlesBean);
    }

}
