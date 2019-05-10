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
import com.example.nice.everywhere.R;
import com.example.nice.everywhere.bean.BanMiBean;

import java.util.ArrayList;
import java.util.List;

public class BanmiAdapter extends RecyclerView.Adapter<BanmiAdapter.ViewHolder> {

    private ArrayList<BanMiBean.ResultBean.BanmiBean> list = new ArrayList<>();
    private Context context;
    private BanMiBean.ResultBean.BanmiBean banmiBean;
    private boolean isFollowed;


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
        banmiBean = list.get(position);
        viewHolder.txt_banmi_name.setText(banmiBean.getName());
        viewHolder.txt_guanzhu.setText(banmiBean.getFollowing() + "人关注");
        viewHolder.txt_banmi_city.setText(banmiBean.getLocation());
        viewHolder.txt_banmi_occupation.setText(banmiBean.getOccupation());

        Glide.with(context).load(banmiBean.getPhoto()).into(viewHolder.img_banmi);


        viewHolder.img_banmi_heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (list.get(position).isIsFollowed()) {
                    Glide.with(context)
                            .load(R.mipmap.follow_unselected)
                            .into(viewHolder.img_banmi_heart);
                    list.get(position).setIsFollowed(false);

                } else {
                    Glide.with(context)
                            .load(R.mipmap.follow)
                            .into(viewHolder.img_banmi_heart);
                    list.get(position).setIsFollowed(true);
                }


/*            isFollowed = true;
                if (isFollowed==true) {
                    viewHolder.img_banmi_heart.setImageResource(R.drawable.xinselect);
                }else {
                    isFollowed = false;
                    viewHolder.img_banmi_heart.setImageResource(R.drawable.xinunselect);
                }

                BanmiDaoBean banmiDaoBean = new BanmiDaoBean();
                banmiDaoBean.setLid(null);
                banmiDaoBean.setFollowing(banmiBean.getFollowing());
                banmiDaoBean.setLocation(banmiBean.getLocation());
                banmiDaoBean.setName(banmiBean.getName());
                banmiDaoBean.setOccupation(banmiBean.getOccupation());
                banmiDaoBean.setPhoto(banmiBean.getPhoto());
                DbUtils.getDbUtils().insertAll(banmiDaoBean);
                ToastUtil.showShort("收藏成功");*/

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
}
