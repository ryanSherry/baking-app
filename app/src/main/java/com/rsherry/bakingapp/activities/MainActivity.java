package com.rsherry.bakingapp.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.rsherry.bakingapp.R;
import com.rsherry.bakingapp.RecipeViewModel;
import com.rsherry.bakingapp.data.Ingredients;
import com.rsherry.bakingapp.data.Recipe;
import com.rsherry.bakingapp.data.Steps;
import com.rsherry.bakingapp.fragments.NoInternetFragment;
import com.rsherry.bakingapp.fragments.RecipeListFragment;
import com.rsherry.bakingapp.fragments.ViewPagerFragment;
import com.rsherry.bakingapp.widget.UpdateAppWidgetService;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements RecipeListFragment.ShareRecipeListInterface, RecipeListFragment.OnRecipeSelectedInterface, VideoPlaybackActivity.onPlayButtonClickedInterface, NoInternetFragment.internetConnectionOnClickRefresh {

    public static final String RECIPE_LIST = "recipe_list";
    public static final String RECIPE_LIST_FRAGMENT = "recipe_list_fragment";
    public static final String RECIPE_INDEX = "recipe_index";
    private static final String VIEWPAGER_FRAGMENT = "viewpager_fragment";
    public static final String RECIPE_INGREDIENT_LIST = "ingredient_list";
    public static final String RECIPE_STEP_LIST = "step_list";
    private static final String NO_INTERNET_FRAGMENT = "no_internet";

    List<Recipe> mRecipes;
    private boolean mTwoPane;
    Intent mStarterIntent;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            mRecipes = savedInstanceState.getParcelableArrayList(RECIPE_LIST);
        }


        // Using to refresh activity when there is no internet connection
        mStarterIntent = getIntent();

        if (findViewById(R.id.two_pane) != null) {
            mTwoPane = true;
        } else {
            mTwoPane = false;
        }

        //Logging with Timber

        Timber.plant(new Timber.DebugTree());

        RecipeViewModel recipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);

        recipeViewModel.getRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(@Nullable List<Recipe> recipes) {
                mRecipes = recipes;
                shareRecipeList(mRecipes, savedInstanceState);
            }
        });
        
        // Show toast if no internet connection

        if (!networkIsConnected(getApplicationContext()) && mRecipes == null) {
            Toast.makeText(MainActivity.this, "No Internet Connection, try again later", Toast.LENGTH_LONG).show();
            noConnectionFragmentGenerator();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(RECIPE_LIST, (ArrayList<Recipe>) mRecipes);
    }

    @Override
    public void shareRecipeList(List<Recipe> recipes, Bundle onSavedInstanceState) {

        if (onSavedInstanceState == null) {

            RecipeListFragment fragment = new RecipeListFragment();

            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(RECIPE_LIST, (ArrayList<Recipe>) recipes);
            fragment.setArguments(bundle);

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            if (!mTwoPane) {
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

        UpdateAppWidgetService.startActionUpdateAppWidgetService(this, recipe.getIngredients());

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //Handle UI for both tablets and phones
        if (!mTwoPane) {
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
        intent.putExtra(VideoPlaybackActivity.VIDEO_PLAYBACK_URL, url);
        startActivity(intent);
    }


    public static boolean networkIsConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm != null ? cm.getActiveNetworkInfo() : null;

        return activeNetwork != null && activeNetwork.isConnected();
    }

    private void noConnectionFragmentGenerator() {
        NoInternetFragment noInternetFragment = new NoInternetFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.placeHolder, noInternetFragment, NO_INTERNET_FRAGMENT);
        fragmentTransaction.commit();
    }

    @Override
    public void clickRefresh() {
        finish();
        startActivity(mStarterIntent);
    }
}