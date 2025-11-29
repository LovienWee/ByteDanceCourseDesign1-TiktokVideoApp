package com.example.tiktokvideoapp.model;

public class VideoItem {

    public String id;
    public String title;
    public String coverUrl;
    public String tag;
    public String authorName;
    public String avatarUrl;
    public String videoUrl;
    public String viewerCount;   // 新增：观看人数文案，比如 "13K"

    public VideoItem(String id,
                     String title,
                     String coverUrl,
                     String tag,
                     String authorName,
                     String avatarUrl,
                     String videoUrl,
                     String viewerCount) {   // 记得这里也加
        this.id = id;
        this.title = title;
        this.coverUrl = coverUrl;
        this.tag = tag;
        this.authorName = authorName;
        this.avatarUrl = avatarUrl;
        this.videoUrl = videoUrl;
        this.viewerCount = viewerCount;
    }
}

