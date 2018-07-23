package com.rsherry.bakingapp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rsherry.bakingapp.data.Ingredients;
import com.rsherry.bakingapp.data.Recipe;
import com.rsherry.bakingapp.data.Steps;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements RecipeListFragment.ShareRecipeListInterface, RecipeListFragment.OnRecipeSelectedInterface, VideoPlaybackActivity.onPlayButtonClickedInterface {

    public static final String RECIPE_LIST = "recipe_list";
    public static final String RECIPE_LIST_FRAGMENT = "recipe_list_fragment";
    public static final String RECIPE_INDEX = "recipe_index";
    private static final String VIEWPAGER_FRAGMENT = "viewpager_fragment";
    public static final String RECIPE_INGREDIENT_LIST = "ingredient_list";
    public static final String RECIPE_STEP_LIST = "step_list";

    List<Recipe> mRecipes;
    private boolean mTwoPane;
//    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if(findViewById(R.id.two_pane) != null) {
            mTwoPane = true;
        } else {
            mTwoPane = false;
        }

        //Logging with Timber

        Timber.plant(new Timber.DebugTree());

        RecipeViewModel recipeViewModel= ViewModelProviders.of(this).get(RecipeViewModel.class);

        recipeViewModel.getRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(@Nullable List<Recipe> recipes) {
                mRecipes = recipes;
                shareRecipeList(mRecipes,savedInstanceState);
//                generateRecipeList(mRecipes);
            }
        });
    }

    @Override
    public void shareRecipeList(List<Recipe> recipes, Bundle onSavedInstanceState) {

        if(onSavedInstanceState == null) {

            RecipeListFragment fragment = new RecipeListFragment();

            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(RECIPE_LIST, (ArrayList<Recipe>) recipes);
            fragment.setArguments(bundle);

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            if(!mTwoPane) {
            fragmentTransaction.add(R.id.placeHolder, fragment, RECIPE_LIST_FRAGMENT);
            } else {
                fragmentTransaction.add(R.id.master_list_tablet_placeholder, fragment, RECIPE_LIST_FRAGMENT);
            }
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onListRecipeSelected(int index) {
        ViewPagerFragment viewPagerFragment = new ViewPagerFragment();

        Recipe recipe = mRecipes.get(index);

        Bundle bundle = new Bundle();
        bundle.putInt(RECIPE_INDEX, index);
        bundle.putParcelableArrayList(RECIPE_INGREDIENT_LIST, (ArrayList<Ingredients>) recipe.getIngredients());
        bundle.putParcelableArrayList(RECIPE_STEP_LIST, (ArrayList<Steps>) recipe.getSteps());

        viewPagerFragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //Handle UI for both tablets and phones
        if(!mTwoPane) {
            fragmentTransaction.replace(R.id.placeHolder, viewPagerFragment, VIEWPAGER_FRAGMENT);
            fragmentTransaction.addToBackStack(null);
        } else {
            fragmentTransaction.replace(R.id.steps_list_tablet_placeholder, viewPagerFragment, VIEWPAGER_FRAGMENT);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onItemPlayButtonClicked(int position, String url) {
        Intent intent = new Intent(this, VideoPlaybackActivity.class);
        intent.putExtra(VideoPlaybackActivity.VIDEO_PLAYBACK_URL,url);
        startActivity(intent);
    }
}