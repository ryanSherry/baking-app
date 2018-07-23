package com.rsherry.bakingapp.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Steps implements Parcelable {
    @SerializedName("id")
    private int mId;

    @SerializedName("shortDescription")
    private String mshortDescription;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("videoURL")
    private String mVideoUrl;

    @SerializedName("thumbnailURL")
    private String mThumbnailUrl;

    public Steps(int id, String mshortDescription, String description, String videoUrl, String thumbnailUrl) {
        mId = id;
        this.mshortDescription = mshortDescription;
        mDescription = description;
        mVideoUrl = videoUrl;
        mThumbnailUrl = thumbnailUrl;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getMshortDescription() {
        return mshortDescription;
    }

    public void setMshortDescription(String mshortDescription) {
        this.mshortDescription = mshortDescription;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getVideoUrl() {
        return mVideoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        mVideoUrl = videoUrl;
    }

    public String getThumbnailUrl() {
        return mThumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        mThumbnailUrl = thumbnailUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mshortDescription);
        dest.writeString(mDescription);
        dest.writeString(mVideoUrl);
        dest.writeString(mThumbnailUrl);
    }

    protected Steps(Parcel in) {
        mId = in.readInt();
        mshortDescription = in.readString();
        mDescription = in.readString();
        mVideoUrl = in.readString();
        mThumbnailUrl = in.readString();
    }

    public static final Creator<Steps> CREATOR = new Creator<Steps>() {
        @Override
        public Steps createFromParcel(Parcel in) {
            return new Steps(in);
        }

        @Override
        public Steps[] newArray(int size) {
            return new Steps[size];
        }
    };


}
