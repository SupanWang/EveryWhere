package com.example.nice.everywhere.ui.main.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.nice.everywhere.R;
import com.example.nice.everywhere.bean.BanMiDetailBean;
import com.example.nice.everywhere.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class BanmiDetsilAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //    CenkLIenter cenkLIenter;
    private ArrayList<BanMiDetailBean.ResultBean.ActivitiesBean> activitiesBeans = new ArrayList<>();
    private Context context;
    private MediaPlayer mediaPlayer;
    private BanMiDetailBean.ResultBean.ActivitiesBean activitiesBean;
    private ImageView img_stop;



    public BanmiDetsilAdapter(Context context) {
        this.context = context;
    }

    public void update(List<BanMiDetailBean.ResultBean.ActivitiesBean> activitiesBeans) {
        this.activitiesBeans.clear();
        this.activitiesBeans.addAll(activitiesBeans);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        RecyclerView.ViewHolder viewHolder = null;
        if (position == 0) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.banmi_dong_item, null);
            viewHolder = new LeftViewHolder(inflate);
        } else if (position == 1) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.banmi_dong_item2, null);
            viewHolder = new RightViewHolder(inflate);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof LeftViewHolder) {
            final LeftViewHolder leftViewHolder = (LeftViewHolder) viewHolder;
            activitiesBean = activitiesBeans.get(position);

            leftViewHolder.txt_state_time.setText(activitiesBean.getDate());
            leftViewHolder.txt_state_speak.setText(activitiesBean.getReplyCount() + "");
            leftViewHolder.txt_state_zanNum.setText(activitiesBean.getLikeCount() + "");

            mediaPlayer = new MediaPlayer();
            initMediaPlayer();//初始化播放器 MediaPlayer

            leftViewHolder.img_play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.showShort("播放音频");
                    //如果没在播放中，立刻开始播放。
                    if (!mediaPlayer.isPlaying()) {
                        mediaPlayer.start();
                        img_stop.setVisibility(View.VISIBLE);
                    }
                  /*  //如果在播放中，立刻暂停。
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.pause();
                    }
                    //如果在播放中，立刻停止。
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.reset();
                        initMediaPlayer();//初始化播放器 MediaPlayer
                    }*/
                }
            });

            img_stop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.pause();
                        leftViewHolder.img_play.setVisibility(View.VISIBLE);
                        img_stop.setVisibility(View.GONE);
                    }
                    //如果在播放中，立刻停止。
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.reset();
                        initMediaPlayer();//初始化播放器 MediaPlayer
                    }
                }
            });

        } else if (viewHolder instanceof RightViewHolder) {
            RightViewHolder rightViewHolder = (RightViewHolder) viewHolder;
            BanMiDetailBean.ResultBean.ActivitiesBean activitiesBean = activitiesBeans.get(position);

            rightViewHolder.txt_state_time2.setText(activitiesBean.getDate());
            rightViewHolder.txt_state_content.setText(activitiesBean.getContent());
            rightViewHolder.txt_state_speak2.setText(activitiesBean.getReplyCount() + "");
            rightViewHolder.txt_state_zanNum2.setText(activitiesBean.getLikeCount() + "");


            RoundedCorners roundedCorners = new RoundedCorners(10);//数字为圆角度数
            RequestOptions coverRequestOptions = new RequestOptions()
                    .transforms(roundedCorners)
                    .placeholder(R.drawable.zhanweitu_home_kapian)   //占位图
                    .diskCacheStrategy(DiskCacheStrategy.ALL)//不做磁盘缓存
                    .skipMemoryCache(true);//不做内存缓存

            if (activitiesBean.getImages().size() > 0) {
                rightViewHolder.img_state_big.enable();    //默认不缩放，需要加这一行代码
                Glide.with(context).load(activitiesBean.getImages().get(0)).apply(coverRequestOptions).into(rightViewHolder.img_state_big);
            }


            rightViewHolder.img_state_big.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

/*    protected void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }*/


    private void initMediaPlayer() {
        try {
//            File file = new File(Environment.getDataDirectory(), activitiesBean.getAudioURL());
            mediaPlayer.setDataSource(activitiesBean.getAudioURL());//指定音频文件路径
            mediaPlayer.setLooping(true);//设置为循环播放
            mediaPlayer.prepare();//初始化播放器MediaPlayer

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return activitiesBeans.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (activitiesBeans.get(position).getAudioURL().length() > 0) {
            return 0;
        } else {
            return 1;
        }
    }


    class LeftViewHolder extends RecyclerView.ViewHolder {

        private TextView txt_state_time;
        private TextView txt_state_listen;
        private ImageView img_play;
        private TextView txt_state_speak;
        private ImageView img_speak;
        private TextView txt_state_zanNum;
        private ImageView img_zan;

        public LeftViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_state_time = itemView.findViewById(R.id.txt_state_time);
            img_stop = itemView.findViewById(R.id.img_stop);
            txt_state_listen = itemView.findViewById(R.id.txt_state_listen);
            img_play = itemView.findViewById(R.id.img_play);
            txt_state_speak = itemView.findViewById(R.id.txt_state_speak);
            img_speak = itemView.findViewById(R.id.img_speak);
            txt_state_zanNum = itemView.findViewById(R.id.txt_state_zanNum);
            img_zan = itemView.findViewById(R.id.img_zan);
        }
    }

    public class RightViewHolder extends RecyclerView.ViewHolder {

        private TextView txt_state_time2;
        private TextView txt_state_listen2;
        private TextView txt_state_title;
//        private ImageView img_state_big;
        private TextView txt_state_speak2;
        private ImageView img_speak2;
        private TextView txt_state_zanNum2;
        private ImageView img_zan2;
        private TextView txt_state_content;

        private PhotoView img_state_big;


        public RightViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_state_time2 = itemView.findViewById(R.id.txt_state_time2);
            txt_state_content = itemView.findViewById(R.id.txt_state_content);
            txt_state_listen2 = itemView.findViewById(R.id.txt_state_listen2);
            txt_state_title = itemView.findViewById(R.id.txt_state_title);
            img_state_big = itemView.findViewById(R.id.img_state_big);
            txt_state_speak2 = itemView.findViewById(R.id.txt_state_speak2);
            img_speak2 = itemView.findViewById(R.id.img_speak2);
            txt_state_zanNum2 = itemView.findViewById(R.id.txt_state_zanNum2);
            img_zan2 = itemView.findViewById(R.id.img_zan2);
        }
    }

}
