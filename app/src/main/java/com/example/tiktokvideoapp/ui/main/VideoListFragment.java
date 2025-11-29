package com.example.tiktokvideoapp.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.tiktokvideoapp.R;
import com.example.tiktokvideoapp.model.VideoItem;
import com.example.tiktokvideoapp.ui.main.adapter.VideoListAdapter;

import java.util.ArrayList;

public class VideoListFragment extends Fragment {

    private RecyclerView recyclerView;
    private VideoListAdapter adapter;
    private VideoListViewModel viewModel;

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

        // 先给一个空数据的 adapter
        adapter = new VideoListAdapter(new ArrayList<VideoItem>());
        recyclerView.setAdapter(adapter);

        // 获取 ViewModel
        viewModel = new ViewModelProvider(this).get(VideoListViewModel.class);

        // 监听数据变化
        viewModel.videoList.observe(getViewLifecycleOwner(), list -> {
            if (list != null) {
                adapter.setData(list);
            }
        });

        // 触发加载数据
        viewModel.loadVideos();
    }
}
