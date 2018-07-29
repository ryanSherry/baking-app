package com.rsherry.bakingapp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.rsherry.bakingapp.data.Recipe;
import com.rsherry.bakingapp.networking.RecipeRepository;

import java.util.ArrayList;
import java.util.List;

public class RecipeViewModel extends ViewModel {
    private MutableLiveData<List<Recipe>> recipes;

    public LiveData<List<Recipe>> getRecipes() {
        if (recipes == null) {
            recipes = new MutableLiveData<List<Recipe>>();
            loadRecipes();
        }
        return recipes;
    }

    public void loadRecipes() {
        RecipeRepository recipeRepository = new RecipeRepository();
        recipes = recipeRepository.getRecipes();
    }
}
