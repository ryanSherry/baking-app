package com.rsherry.bakingapp;

import android.app.Application;
import android.app.Fragment;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rsherry.bakingapp.Adapters.RecipeAdapter;
import com.rsherry.bakingapp.data.Recipe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SharedViewModel extends RecipeViewModel {

    private MutableLiveData<List<Recipe>> recipes;

    public static class RecipeListFragment extends Fragment {

        List<Recipe> mRecipeList;
        private SharedViewModel model;

        public RecipeListFragment() {

        }

//        @Nullable
//        @Override
//        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//
//            final View rootView = inflater.inflate(R.layout.recipe_master_list, container, false);
//            ButterKnife.bind(this, rootView);
//
//            model = ViewModelProviders.of((FragmentActivity) this.getActivity()).get(SharedViewModel.class);
//            RecipeAdapter mAdapter = new RecipeAdapter(model.getRecipes().getValue());
//
//            mRecyclerView.setAdapter(mAdapter);
//
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
//                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
//                mRecyclerView.setLayoutManager(layoutManager);
//            }
//            return rootView;
//        }
    }

}
