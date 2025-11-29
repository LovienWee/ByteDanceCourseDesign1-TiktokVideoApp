package com.example.tiktokvideoapp.ui.main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tiktokvideoapp.R;
import com.example.tiktokvideoapp.model.VideoItem;

import java.util.List;

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.VideoViewHolder> {

    private List<VideoItem> data;

    public VideoListAdapter(List<VideoItem> data) {
        this.data = data;
    }

    public void setData(List<VideoItem> newData) {
        this.data = newData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_video, parent, false);
        return new VideoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        VideoItem item = data.get(position);

        holder.tvTitle.setText(item.title);
        holder.tvTag.setText(item.tag);
        holder.tvNickname.setText(item.authorName);
        holder.tvViewCount.setText(item.viewerCount);

        // 封面图（centerCrop）
        Glide.with(holder.itemView.getContext())
                .load(item.coverUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .centerCrop()
                .into(holder.ivCover);

        // 头像图（圆形裁剪）
        Glide.with(holder.itemView.getContext())
                .load(item.avatarUrl)
                .placeholder(R.mipmap.ic_launcher_round)
                .circleCrop()
                .into(holder.ivAvatar);

        holder.itemView.setOnClickListener(v ->
                Toast.makeText(v.getContext(),
                        "点击了：" + item.title,
                        Toast.LENGTH_SHORT).show()
        );
    }


    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    static class VideoViewHolder extends RecyclerView.ViewHolder {

        ImageView ivCover;
        TextView tvTag;
        ImageView ivAvatar;
        TextView tvNickname;
        TextView tvTitle;
        TextView tvViewCount;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCover = itemView.findViewById(R.id.iv_cover);
            tvTag = itemView.findViewById(R.id.tv_tag);
            ivAvatar = itemView.findViewById(R.id.iv_avatar);
            tvNickname = itemView.findViewById(R.id.tv_nickname);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvViewCount = itemView.findViewById(R.id.tv_view_count);
        }
    }
}
