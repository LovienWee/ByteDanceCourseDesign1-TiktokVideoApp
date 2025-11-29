package com.example.tiktokvideoapp.repository;

import com.example.tiktokvideoapp.model.VideoItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 负责提供视频数据：后续会放
 * - 联网请求
 * - 本地缓存（比如 SharedPreferences / Room）
 */
public class VideoRepository {

    // 单例（全局只要一个仓库对象）
    private static volatile VideoRepository sInstance;

    private VideoRepository() {}

    public static VideoRepository getInstance() {
        if (sInstance == null) {
            synchronized (VideoRepository.class) {
                if (sInstance == null) {
                    sInstance = new VideoRepository();
                }
            }
        }
        return sInstance;
    }

    // 回调接口：数据加载成功后通知调用方
    public interface LoadCallback {
        void onSuccess(List<VideoItem> list);
        void onError(Throwable t);
    }

    /**
     * 对外暴露的加载方法
     * 现在先用本地 mock，后面这里会改成：先读缓存 → 再请求网络
     */
    public void loadVideoList(LoadCallback callback) {
        try {
            List<VideoItem> list = createMockData();
            callback.onSuccess(list);
        } catch (Exception e) {
            callback.onError(e);
        }
    }

    // 暂时把你之前的 mock 数据搬过来
    private List<VideoItem> createMockData() {
        List<VideoItem> list = new ArrayList<>();

        // 准备几个昵称池
        String[] names = {
                "Keeendrix", "John", "Jony", "JesseLiu", "CoachLeo",
                "LuckyStar", "FunnyCat", "AnalystPro", "MacroKing", "ChatSlave",
                "TryHarder", "CoachAnna", "PrankBro", "UnluckyStar", "SkinCollector",
                "SoloMain", "SupportLife", "WhaleDaddy", "PotatoPlayer", "NightOwl"
        };

        // 准备几个标题池（随机组合）
        String[] titles = {
                "Look how beautiful this flamingo is, nature creates the best colors.",
                "Can you imagine? The most beautiful creature in this jungle fight.",
                "Rank game highlights, we turned the game around last minute.",
                "New hero first day, insane burst damage!",
                "Teaching you how to climb rank fast.",
                "Unbelievable clutch moment with only 1 HP left.",
                "Funny fails compilation from ranked games.",
                "New patch analysis: strongest heroes right now.",
                "Full map control strategy tutorial.",
                "Viewers choose my build, no excuses.",
                "Trying no-death challenge, can we make it?",
                "Reviewing your replays and coaching.",
                "Trolling duo queue with weird builds.",
                "Unlucky moments, the game hates me.",
                "New skin showcase and animation review.",
                "Solo queue challenge: no duo partner.",
                "How to carry your ADC as a support.",
                "Every time I die, gifting skin to viewers.",
                "Ultra low graphics challenge.",
                "Late night chill stream."
        };

        for (int i = 1; i <= 1000; i++) {

            // 随机选一个昵称
            String author = names[(int) (Math.random() * names.length)];

            // 随机选一个标题
            String title = titles[(int) (Math.random() * titles.length)];

            // 随机人数 1K - 30K
            int viewer = 1 + (int)(Math.random() * 30);
            String viewerCount = viewer + "K";

            list.add(new VideoItem(
                    String.valueOf(i),
                    title,
                    "https://picsum.photos/400/600?random=" + i,     // 每条封面不同
                    "LIVE",
                    author,
                    "https://picsum.photos/100/100?random=" + (i + 1000), // 每条头像不同
                    "", // 视频 URL 可以留空
                    viewerCount
            ));
        }

        return list;
    }


}
