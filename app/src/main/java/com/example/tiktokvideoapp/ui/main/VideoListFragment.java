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

        StaggeredGridLayoutManager layoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new VideoListAdapter(new ArrayList<VideoItem>());
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(VideoListViewModel.class);

        viewModel.videoList.observe(getViewLifecycleOwner(), list -> {
            if (list != null) {
                adapter.setData(list);
            }
        });

        // 监听滑动事件，实现“滑到底部加载更多”
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView rv, int dx, int dy) {
                super.onScrolled(rv, dx, dy);

                if (dy <= 0) return; // 只关心向下滑

                StaggeredGridLayoutManager lm = (StaggeredGridLayoutManager) rv.getLayoutManager();
                if (lm == null) return;

                int[] lastVisibleItemPositions = lm.findLastVisibleItemPositions(null);
                int lastVisible = Math.max(lastVisibleItemPositions[0], lastVisibleItemPositions[1]);
                int totalItemCount = lm.getItemCount();

                // 当最后可见位置接近总数末尾 4 个时，加载更多
                if (totalItemCount - lastVisible <= 4) {
                    viewModel.loadMore();
                }
            }
        });

        // 加载第一页数据
        viewModel.loadFirstPage();
    }

}
