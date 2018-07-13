package com.rsherry.bakingapp.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Recipe {

    @SerializedName("id")
    private int mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("ingredients")
    private List<Ingredients> mIngredients;

    @SerializedName("steps")
    private List<Steps> mSteps;

    public Recipe(String name, int id, List<Ingredients> ingredients, List<Steps> steps) {
        mName = name;
        mId = id;
        mIngredients = ingredients;
        mSteps = steps;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public List<Ingredients> getIngredients() {
        return mIngredients;
    }

    public void setIngredients(List<Ingredients> ingredients) {
        mIngredients = ingredients;
    }

    public List<Steps> getSteps() {
        return mSteps;
    }

    public void setSteps(List<Steps> steps) {
        mSteps = steps;
    }
}
