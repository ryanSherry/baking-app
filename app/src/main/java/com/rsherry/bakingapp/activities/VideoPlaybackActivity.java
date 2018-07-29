package com.rsherry.bakingapp.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.rsherry.bakingapp.R;
import com.rsherry.bakingapp.fragments.VideoPlaybackFragment;

import butterknife.ButterKnife;

public class VideoPlaybackActivity extends AppCompatActivity {
    private static final String VIDEO_PLAYBACK_FRAGMENT = "video_playback_fragment";
    public static final String VIDEO_PLAYBACK_URL = "video_playback_url";

    public interface onPlayButtonClickedInterface {
        void onItemPlayButtonClicked(int position, String url);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (findViewById(R.id.master_list_tablet_placeholder) == null) {
            if (this.getResources().getConfiguration().orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
                Toast.makeText(this, "Switch to landscape mode for full-screen video", Toast.LENGTH_LONG).show();
            }
        }

        setContentView(R.layout.activity_video_playback);
        ButterKnife.bind(this);
        if (savedInstanceState == null) {
            showVideo();
        }
    }

    public void showVideo() {
        VideoPlaybackFragment videoPlaybackFragment = new VideoPlaybackFragment();

        //Get video url
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        videoPlaybackFragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.videoPlaceHolder, videoPlaybackFragment, VIDEO_PLAYBACK_FRAGMENT);
        transaction.commit();
    }
}
