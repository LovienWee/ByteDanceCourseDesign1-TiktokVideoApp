package com.example.tiktokvideoapp.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.tiktokvideoapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tvBack = findViewById(R.id.tv_back);
        tvBack.setOnClickListener(v -> finish());


        // 默认加载列表 Fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new VideoListFragment())
                .commit();
    }
}
