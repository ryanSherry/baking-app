package com.rsherry.bakingapp.networking;

import com.rsherry.bakingapp.data.Recipe;

import java.util.List;

import retrofit2.http.GET;

public interface GetEndpointData {

    @GET("2017/May/59121517_baking/baking.json")
    retrofit2.Call<List<Recipe>> getRecipeResults();

}
