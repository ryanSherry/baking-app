package com.rsherry.bakingapp.Adapters;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.rsherry.bakingapp.R;
import com.rsherry.bakingapp.data.Steps;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepViewHolder> {
    List<Steps> mSteps;

    public StepAdapter(List<Steps> steps) {
        mSteps = steps;
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

    public class StepViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.step_image) ImageView mStepImage;
        @BindView(R.id.stepShortDescription) TextView mShortDescription;
        @BindView(R.id.stepDescription) TextView mDescription;
        @BindView(R.id.playerView) SimpleExoPlayerView mPlayerView;

        public StepViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindView(int index) {
            Steps step = mSteps.get(index);
            mShortDescription.setText(step.getMshortDescription());
            mDescription.setText(step.getDescription());

            if (step.getVideoUrl() == null || step.getVideoUrl().equals("")) {
                mPlayerView.setVisibility(View.GONE);
            }

            Uri uri = Uri.parse(step.getThumbnailUrl());

            Picasso.get().load(uri)
                    .error(R.drawable.no_image_available)
                    .into(mStepImage);
        }
    }
}
