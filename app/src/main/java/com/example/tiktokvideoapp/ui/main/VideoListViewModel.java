package com.example.tiktokvideoapp.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tiktokvideoapp.model.VideoItem;
import com.example.tiktokvideoapp.repository.VideoRepository;

import java.util.List;

public class VideoListViewModel extends ViewModel {

    private final MutableLiveData<List<VideoItem>> _videoList = new MutableLiveData<>();
    public LiveData<List<VideoItem>> videoList = _videoList;

    private final VideoRepository repository = VideoRepository.getInstance();

    public void loadVideos() {
        repository.loadVideoList(new VideoRepository.LoadCallback() {
            @Override
            public void onSuccess(List<VideoItem> list) {
                _videoList.postValue(list);
            }

            @Override
            public void onError(Throwable t) {
                _videoList.postValue(null);
            }
        });
    }
}
