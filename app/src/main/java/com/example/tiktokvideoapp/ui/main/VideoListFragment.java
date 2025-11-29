package com.example.tiktokvideoapp.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tiktokvideoapp.R;
import com.example.tiktokvideoapp.model.VideoItem;
import com.example.tiktokvideoapp.ui.main.adapter.VideoListAdapter;

import java.util.ArrayList;
import java.util.List;

public class VideoListFragment extends Fragment {

    private RecyclerView recyclerView;
    private VideoListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_video_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_view);

        // 2 列瀑布流布局
        StaggeredGridLayoutManager layoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        // 准备一些假数据
        List<VideoItem> mockData = createMockData();

        adapter = new VideoListAdapter(mockData);
        recyclerView.setAdapter(adapter);
    }

    private List<VideoItem> createMockData() {
        List<VideoItem> list = new ArrayList<>();

        list.add(new VideoItem("1",
                "Look how beautiful this flamingo is, nature creates the best colors.",
                "", "LIVE", "Keeendrix", "", "", "13K"));
        list.add(new VideoItem("2",
                "Can you imagine? The most beautiful creature in this jungle fight.",
                "", "LIVE", "John", "", "", "13K"));
        list.add(new VideoItem("3",
                "Rank game highlights, we turned the game around in the last 5 minutes.",
                "", "LIVE", "Jony", "", "", "13K"));
        list.add(new VideoItem("4",
                "New hero first day, insane burst damage and crazy mobility.",
                "", "LIVE", "JesseLiu", "", "", "13K"));
        list.add(new VideoItem("5",
                "Teaching you how to climb from Gold to Mythic in 3 days.",
                "", "LIVE", "CoachLeo", "", "", "13K"));
        list.add(new VideoItem("6",
                "Only 1 HP left but still won the fight, unbelievable clutch moment.",
                "", "LIVE", "LuckyStar", "", "", "13K"));
        list.add(new VideoItem("7",
                "Funny fails compilation, my teammates are the real comedians.",
                "", "LIVE", "FunnyCat", "", "", "13K"));
        list.add(new VideoItem("8",
                "New patch analysis, which heroes are the strongest right now?",
                "", "LIVE", "AnalystPro", "", "", "13K"));

        return list;
    }

}
