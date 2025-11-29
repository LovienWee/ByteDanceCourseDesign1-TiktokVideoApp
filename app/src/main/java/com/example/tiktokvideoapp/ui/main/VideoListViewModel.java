package com.example.tiktokvideoapp.ui.main;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tiktokvideoapp.model.VideoItem;
import com.example.tiktokvideoapp.repository.VideoRepository;

import java.util.ArrayList;
import java.util.List;

public class VideoListViewModel extends ViewModel {

    private final MutableLiveData<List<VideoItem>> _videoList = new MutableLiveData<>();
    public LiveData<List<VideoItem>> videoList = _videoList;

    private final VideoRepository repository = VideoRepository.getInstance();

    // 分页相关
    private int currentPage = 1;
    private final int pageSize = 50;
    private boolean isLoading = false;
    private boolean hasMore = true;

    // 首次加载 / 下拉刷新可以调用这个
    public void loadFirstPage() {
        currentPage = 1;
        hasMore = true;
        loadPage(true);
    }

    // 上拉加载更多
    public void loadMore() {
        if (isLoading || !hasMore) return;
        currentPage++;
        loadPage(false);
    }

    private void loadPage(boolean isFirstPage) {
        isLoading = true;

        repository.loadVideoListPage(currentPage, pageSize, new VideoRepository.LoadCallback() {
            @Override
            public void onSuccess(List<VideoItem> list) {
                isLoading = false;

                if (list == null || list.isEmpty()) {
                    hasMore = false;
                    if (isFirstPage) {
                        _videoList.postValue(new ArrayList<>());
                    }
                    return;
                }

                if (isFirstPage) {
                    _videoList.postValue(list);
                } else {
                    List<VideoItem> current = _videoList.getValue();
                    if (current == null) {
                        current = new ArrayList<>();
                    } else {
                        current = new ArrayList<>(current); // 防止直接改原列表
                    }
                    current.addAll(list);
                    _videoList.postValue(current);
                }
            }

            @Override
            public void onError(Throwable t) {
                isLoading = false;
                // 出错时你可以在这里做日志 / 错误状态，这里简单忽略
            }
        });
    }
}
