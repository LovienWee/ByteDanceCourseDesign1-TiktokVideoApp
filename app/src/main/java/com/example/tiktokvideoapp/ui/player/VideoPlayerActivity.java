package com.example.tiktokvideoapp.ui.player;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.tiktokvideoapp.R;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.ui.PlayerView;

public class VideoPlayerActivity extends AppCompatActivity {

    // 测试视频
    private static final String VIDEO_URL =
            "https://sf1-cdn-tos.huoshanstatic.com/obj/media-fe/xgplayer_doc_video/mp4/xgplayer-demo-360p.mp4";

    private ExoPlayer player;
    private PlayerView playerView;
    private TextView tvTitle;
    private TextView tvBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        playerView = findViewById(R.id.player_view);
        tvTitle = findViewById(R.id.tv_title);
        tvBack = findViewById(R.id.tv_back);

        // 标题从列表带过来
        String title = getIntent().getStringExtra("title");
        if (title == null) {
            title = "LIVE VIDEO";
        }
        tvTitle.setText(title);

        tvBack.setOnClickListener(v -> finish());

        initPlayer();
    }

    private void initPlayer() {
        // 1. 创建播放器实例
        player = new ExoPlayer.Builder(this).build();
        playerView.setPlayer(player);

        // 2. 设置播放的媒体资源（网络 URL）
        MediaItem mediaItem = MediaItem.fromUri(VIDEO_URL);
        player.setMediaItem(mediaItem);

        // 3. 准备并开始播放
        player.prepare();
        player.play();
    }

    private void releasePlayer() {
        if (player != null) {
            player.release();
            player = null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        releasePlayer();
    }
}
