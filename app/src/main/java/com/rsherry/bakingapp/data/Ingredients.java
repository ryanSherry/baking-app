package com.rsherry.bakingapp.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Ingredients implements Parcelable {
    @SerializedName("quantity")
    private Double mquantity;

    @SerializedName("measure")
    private String mMeasure;

    @SerializedName("ingredient")
    private String mIngredient;

    public Ingredients(Double quantity, String measure, String ingredient) {
        mquantity = quantity;
        mMeasure = measure;
        mIngredient = ingredient;
    }

    public Double getMquantity() {
        return mquantity;
    }

    public void setMquantity(Double quantity) {
        mquantity = quantity;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(mquantity);
        dest.writeString(mMeasure);
        dest.writeString(mIngredient);
    }

    protected Ingredients(Parcel in) {
        if (in.readByte() == 0) {
            mquantity = null;
        } else {
            mquantity = in.readDouble();
        }
        mMeasure = in.readString();
        mIngredient = in.readString();
    }

    public static final Creator<Ingredients> CREATOR = new Creator<Ingredients>() {
        @Override
        public Ingredients createFromParcel(Parcel in) {
            return new Ingredients(in);
        }

        @Override
        public Ingredients[] newArray(int size) {
            return new Ingredients[size];
        }
    };
}
