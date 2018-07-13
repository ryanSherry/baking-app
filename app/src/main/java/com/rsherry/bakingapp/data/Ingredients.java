package com.rsherry.bakingapp.data;

import com.google.gson.annotations.SerializedName;

public class Ingredients {
    @SerializedName("quantity")
    private Double mquantity;

    @SerializedName("measure")
    private String mMeasure;

    @SerializedName("ingredient")
    private String mIngredient;

    public Ingredients(Double mquantity, String measure, String ingredient) {
        this.mquantity = mquantity;
        mMeasure = measure;
        mIngredient = ingredient;
    }

    public Double getMquantity() {
        return mquantity;
    }

    public void setMquantity(Double mquantity) {
        this.mquantity = mquantity;
    }

    public String getMeasure() {
        return mMeasure;
    }

    public void setMeasure(String measure) {
        mMeasure = measure;
    }

    public String getIngredient() {
        return mIngredient;
    }

    public void setIngredient(String ingredient) {
        mIngredient = ingredient;
    }
}
