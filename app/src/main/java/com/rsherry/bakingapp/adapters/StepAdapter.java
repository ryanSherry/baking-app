package com.rsherry.bakingapp.adapters;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.rsherry.bakingapp.R;
import com.rsherry.bakingapp.activities.VideoPlaybackActivity;
import com.rsherry.bakingapp.data.Steps;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepViewHolder> {
    List<Steps> mSteps;
    private VideoPlaybackActivity.onPlayButtonClickedInterface mListener;

    public StepAdapter(List<Steps> steps, VideoPlaybackActivity.onPlayButtonClickedInterface listener) {
        mSteps = steps;
        mListener = listener;
    }

    @NonNull
    @Override
    public StepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.step, parent, false);
        return new StepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepAdapter.StepViewHolder holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return mSteps.size();
    }

    public class StepViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        @BindView(R.id.stepLabel)
        TextView mStepLabel;
        @BindView(R.id.step_image)
        ImageView mStepImage;
        @BindView(R.id.stepShortDescription)
        TextView mShortDescription;
        @BindView(R.id.stepDescription)
        TextView mDescription;
        @BindView(R.id.playStepVideo)
        Button mPlayVideoButton;
        private int mIndex;
        private String mUrl;

        public StepViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindView(int index) {
            mIndex = index;
            Steps step = mSteps.get(index);

            mUrl = step.getVideoUrl();
            String stepLabel = "Step: " + (index);
            mStepLabel.setText(stepLabel);
            mShortDescription.setText(step.getMshortDescription());
            mDescription.setText(step.getDescription());

            if (step.getVideoUrl() == null || step.getVideoUrl().equals("")) {
                mPlayVideoButton.setVisibility(View.GONE);
            } else {
                mPlayVideoButton.setOnClickListener(this);
            }

            Uri uri = Uri.parse(step.getThumbnailUrl());

            Picasso.get().load(uri)
                    .error(R.drawable.no_image_available)
                    .into(mStepImage);
        }

        @Override
        public void onClick(View v) {
            mListener.onItemPlayButtonClicked(mIndex, mUrl);
        }
    }
}
