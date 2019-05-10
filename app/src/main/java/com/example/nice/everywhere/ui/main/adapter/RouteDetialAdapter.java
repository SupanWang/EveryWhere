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
import com.bumptech.glide.request.RequestOptions;
import com.example.nice.everywhere.R;
import com.example.nice.everywhere.bean.RouteDetalBean;

import java.util.ArrayList;
import java.util.List;

public class RouteDetialAdapter extends RecyclerView.Adapter<RouteDetialAdapter.ViewHolder> {


    private ArrayList<RouteDetalBean.ResultBean> list = new ArrayList<>();
    private Context context;

    public RouteDetialAdapter(Context context) {
        this.context = context;
    }

    public void update(List<RouteDetalBean.ResultBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.routedetal_item, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        RouteDetalBean.ResultBean resultBean = list.get(position);
       /* viewHolder.name.setText(newListBean.getTitle());
        viewHolder.title.setText(newListBean.getPublishTime());
        //圆形图片，需要单独写一个实体类，继承extends AppGlideModule，加注解@GlideModule
        RequestOptions options = RequestOptions.circleCropTransform();
        Glide.with(context).load(newListBean.getImageListThumb()).apply(options).into(viewHolder.img);*/

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView title;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.title);
        }
    }
}
