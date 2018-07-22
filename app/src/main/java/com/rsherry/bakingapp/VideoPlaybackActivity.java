package com.rsherry.bakingapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public class VideoPlaybackActivity extends AppCompatActivity {
    private static final String VIDEO_PLAYBACK_FRAGMENT = "video_playback_fragment";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_playback);
        ButterKnife.bind(this);
        showVideo();
    }

    public void showVideo() {
        VideoPlaybackFragment videoPlaybackFragment = new VideoPlaybackFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.videoPlaceHolder, videoPlaybackFragment, VIDEO_PLAYBACK_FRAGMENT);
        transaction.commit();
    }
}
