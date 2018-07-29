package com.rsherry.bakingapp.networking;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.rsherry.bakingapp.data.Recipe;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class RecipeRepository {
    private static Retrofit retrofit;
    public static final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public MutableLiveData<List<Recipe>> getRecipes() {
        final MutableLiveData<List<Recipe>> mRecipes = new MutableLiveData<>();

        getRetrofitInstance().create(GetEndpointData.class).getRecipeResults().enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Recipe>> call, Response<List<Recipe>> response) {
                mRecipes.setValue(response.body());
            }

            @Override
            public void onFailure(retrofit2.Call<List<Recipe>> call, Throwable t) {

            }
        });
        return mRecipes;
    }
}
