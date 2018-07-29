package com.rsherry.bakingapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoInternetFragment extends android.support.v4.app.Fragment {
    @BindView(R.id.noInternetRefreshButton)
    Button mRefreshButton;

    private internetConnectionOnClickRefresh mClickRefresh;

    public interface internetConnectionOnClickRefresh {
        void clickRefresh();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_no_internet, container, false);
        ButterKnife.bind(this, view);
        mRefreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickRefresh.clickRefresh();
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mClickRefresh = (internetConnectionOnClickRefresh) this.getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement internetConnectionOnClickRefresh");
        }
    }
}
