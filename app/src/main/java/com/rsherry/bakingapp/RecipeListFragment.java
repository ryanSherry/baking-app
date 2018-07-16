package com.rsherry.bakingapp;

import android.app.Fragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rsherry.bakingapp.Adapters.RecipeAdapter;
import com.rsherry.bakingapp.data.Ingredients;
import com.rsherry.bakingapp.data.Recipe;
import com.rsherry.bakingapp.data.Steps;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

//public class RecipeListFragment extends Fragment {
//
//    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
//    List<Recipe> mRecipes = new ArrayList<>();
//
//    public RecipeListFragment() {
//
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
////        Ingredients ingredient = new Ingredients((double) 2,"tsp","sugar");
////        List<Ingredients> ingredients = new ArrayList<>();
////        ingredients.add(ingredient);
////
////        Steps step = new Steps(1,"Delicious Brownies","Delicious Brownies","http://test","");
////        List<Steps> steps = new ArrayList<>();
////        steps.add(step);
////
////
////        Recipe recipe = new Recipe("Brownies",1, ingredients,steps, 3, "");
////        mRecipes.add(recipe);
//
//        final View rootView = inflater.inflate(R.layout.recipe_master_list, container, false);
//        ButterKnife.bind(this, rootView);
//
//        RecipeAdapter mAdapter = new RecipeAdapter(mRecipes);
//        mRecyclerView.setAdapter(mAdapter);
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
//            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
//            mRecyclerView.setLayoutManager(layoutManager);
//        }
//        return rootView;
//    }
//}
