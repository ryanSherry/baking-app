package com.rsherry.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rsherry.bakingapp.Adapters.StepAdapter;
import com.rsherry.bakingapp.data.Steps;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class StepsFragment extends Fragment implements VideoPlaybackActivity.onPlayButtonClickedInterface {
    @BindView(R.id.stepsListRecyclerView)
    RecyclerView mRecyclerView;
    List<Steps> mSteps;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        VideoPlaybackActivity.onPlayButtonClickedInterface listener = (VideoPlaybackActivity.onPlayButtonClickedInterface) getActivity();

        View view = inflater.inflate(R.layout.fragment_steps, container, false);
        ButterKnife.bind(this,view);

        Bundle bundle = this.getArguments();

        if(bundle != null) {

            mSteps = bundle.getParcelableArrayList(MainActivity.RECIPE_STEP_LIST);
            StepAdapter adapter = new StepAdapter(mSteps, listener);
            mRecyclerView.setAdapter(adapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            mRecyclerView.setLayoutManager(layoutManager);
        } else {
            Timber.e("bundle sent to stepsFragment is null");
        }

        return view;
    }


    @Override
    public void onItemPlayButtonClicked(int position, String url) {
        Intent intent = new Intent(getActivity(), VideoPlaybackActivity.class);
        intent.putExtra(VideoPlaybackActivity.VIDEO_PLAYBACK_URL,url);

        startActivity(intent);
    }
}
