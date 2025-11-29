package com.example.tiktokvideoapp.repository;

import com.example.tiktokvideoapp.model.VideoItem;

import java.util.ArrayList;
import java.util.List;

public class VideoRepository {

    private static volatile VideoRepository sInstance;

    // 所有 mock 数据（一次生成，分页从这里切片）
    private List<VideoItem> allMockData;

    private VideoRepository() {
        allMockData = createMockData();
    }

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

    public interface LoadCallback {
        void onSuccess(List<VideoItem> list);
        void onError(Throwable t);
    }

    /**
     * 按页加载数据
     * @param page 第几页，从 1 开始
     * @param pageSize 每页多少条
     */
    public void loadVideoListPage(int page, int pageSize, LoadCallback callback) {
        try {
            if (allMockData == null) {
                allMockData = createMockData();
            }

            int from = (page - 1) * pageSize;
            if (from >= allMockData.size()) {
                // 没有更多了
                callback.onSuccess(new ArrayList<>());
                return;
            }

            int to = Math.min(from + pageSize, allMockData.size());
            List<VideoItem> subList = new ArrayList<>(allMockData.subList(from, to));
            callback.onSuccess(subList);
        } catch (Exception e) {
            callback.onError(e);
        }
    }

    // ====== 下面维持自动 mock 逻辑，生成 200/1000 条 ======

    private List<VideoItem> createMockData() {
        List<VideoItem> list = new ArrayList<>();

        String[] names = {
                "Keeendrix", "John", "Jony", "JesseLiu", "CoachLeo",
                "LuckyStar", "FunnyCat", "AnalystPro", "MacroKing", "ChatSlave",
                "TryHarder", "CoachAnna", "PrankBro", "UnluckyStar", "SkinCollector",
                "SoloMain", "SupportLife", "WhaleDaddy", "PotatoPlayer", "NightOwl"
        };

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

        // 数据量更高
        for (int i = 1; i <= 1000; i++) {

            String author = names[(int) (Math.random() * names.length)];
            String title = titles[(int) (Math.random() * titles.length)];

            int viewer = 1 + (int) (Math.random() * 30);
            String viewerCount = viewer + "K";

            list.add(new VideoItem(
                    String.valueOf(i),
                    title,
                    "https://picsum.photos/400/600?random=" + i,
                    "LIVE",
                    author,
                    "https://picsum.photos/100/100?random=" + (i + 1000),
                    "",
                    viewerCount
            ));
        }

        return list;
    }
}
