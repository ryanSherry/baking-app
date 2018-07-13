package com.rsherry.bakingapp.data;

import com.google.gson.annotations.SerializedName;

public class Steps {
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
}
