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
import com.example.nice.everywhere.bean.DiscussBean;

import java.util.ArrayList;
import java.util.List;

public class DisCussAdapter extends RecyclerView.Adapter<DisCussAdapter.ViewHolder> {

    private ArrayList<DiscussBean.ResultBean.ReviewsBean> list = new ArrayList<>();
    private Context context;


    public DisCussAdapter(Context context) {
        this.context = context;
    }

    public void update(List<DiscussBean.ResultBean.ReviewsBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.route_discuss_item, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {

        if (list.get(position).getUserName() != null) {
            viewHolder.txt_dis_name.setText(list.get(position).getUserName());
        }else {
            viewHolder.txt_dis_name.setText("伴米");
        }
        viewHolder.txt_dis_date.setText(list.get(position).getCreatedAt());
        viewHolder.txt_dis_content.setText(list.get(position).getContent());

        //圆形图片，需要单独写一个实体类，继承extends AppGlideModule，加注解@GlideModule
        RequestOptions options = RequestOptions.circleCropTransform();
        if (list.get(position).getUserPhoto() != null) {
            Glide.with(context).load(list.get(position).getUserPhoto()).apply(options).into(viewHolder.img_discuss);
        }else {
            Glide.with(context).load(R.drawable.zhanweitu_touxiang).apply(options).into(viewHolder.img_discuss);
        }

    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img_discuss;
        private TextView txt_dis_name;
        private TextView txt_dis_date;
        private TextView txt_dis_content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_discuss = itemView.findViewById(R.id.img_discuss);
            txt_dis_name = itemView.findViewById(R.id.txt_dis_name);
            txt_dis_date = itemView.findViewById(R.id.txt_dis_date);
            txt_dis_content = itemView.findViewById(R.id.txt_dis_content);
        }
    }

}
