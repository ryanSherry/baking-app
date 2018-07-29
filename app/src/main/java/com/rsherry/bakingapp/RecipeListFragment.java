package com.rsherry.bakingapp;

import android.app.Fragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.rsherry.bakingapp.Adapters.RecipeAdapter;
import com.rsherry.bakingapp.data.Ingredients;
import com.rsherry.bakingapp.data.Recipe;
import com.rsherry.bakingapp.data.Steps;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeListFragment extends android.support.v4.app.Fragment {

    @BindView(R.id.recipeRecyclerView) RecyclerView mRecyclerView;
    List<Recipe> mRecipes;

    public interface ShareRecipeListInterface {
        void shareRecipeList(List<Recipe> recipes, Bundle onSavedInstanceState);
    }

    public interface OnRecipeSelectedInterface {
        void onListRecipeSelected(int index);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        OnRecipeSelectedInterface listener = (OnRecipeSelectedInterface) getActivity();

        View view = inflater.inflate(R.layout.fragment_recipelist, container, false);
        ButterKnife.bind(this, view);

        Bundle bundle = this.getArguments();

        if (bundle != null) {
            mRecipes = bundle.getParcelableArrayList(MainActivity.RECIPE_LIST);

            RecipeAdapter recipeAdapter = new RecipeAdapter(mRecipes, listener);
            mRecyclerView.setAdapter(recipeAdapter);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            mRecyclerView.setLayoutManager(layoutManager);
        }
        return view;
    }
}
