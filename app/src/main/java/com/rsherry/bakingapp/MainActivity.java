package com.rsherry.bakingapp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rsherry.bakingapp.Adapters.RecipeAdapter;
import com.rsherry.bakingapp.data.Recipe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements RecipeListFragment.ShareRecipeListInterface, RecipeListFragment.OnRecipeSelectedInterface {

    public static final String RECIPE_LIST = "recipe_list";
    public static final String RECIPE_LIST_FRAGMENT = "recipe_list_fragment";
    public static final String RECIPE_INDEX = "recipe_index";
    private static final String VIEWPAGER_FRAGMENT = "viewpager_fragment";

    List<Recipe> mRecipes;
//    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //Logging with Timber

        Timber.plant(new Timber.DebugTree());

        RecipeViewModel recipeViewModel= ViewModelProviders.of(this).get(RecipeViewModel.class);

        recipeViewModel.getRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(@Nullable List<Recipe> recipes) {
                mRecipes = recipes;
                shareRecipeList(mRecipes);
//                generateRecipeList(mRecipes);
            }
        });
    }

    @Override
    public void shareRecipeList(List<Recipe> recipes) {
        //create fragment
        RecipeListFragment fragment = new RecipeListFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(RECIPE_LIST,(ArrayList<Recipe>) recipes);
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.placeHolder,fragment,RECIPE_LIST_FRAGMENT);
        fragmentTransaction.commit();

    }

    @Override
    public void onListRecipeSelected(int index) {
        ViewPagerFragment viewPagerFragment = new ViewPagerFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(RECIPE_INDEX, index);

        viewPagerFragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();;
        fragmentTransaction.replace(R.id.placeHolder, viewPagerFragment, VIEWPAGER_FRAGMENT);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

//    public void generateRecipeList(List<Recipe> recipeList) {
//        RecyclerView.Adapter adapter = new RecipeAdapter(recipeList);
//        mRecyclerView.setAdapter(adapter);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(layoutManager);
//    }
}