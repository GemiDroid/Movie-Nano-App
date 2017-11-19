package com.orchtech.baking_app.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;


/**
 * Created by pc on 11/17/2017.
 */

public class StepsModel implements Serializable {

    @SerializedName("id")
    @Expose
    private String Id;

    @SerializedName("shortDescription")
    @Expose
    private String ShortDesc;

    @SerializedName("description")
    @Expose
    private String Description;

    @SerializedName("videoURL")
    @Expose
    private String VideoUrl;

    @SerializedName("thumbnailURL")
    @Expose
    private String ThumbUrl;


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getShortDesc() {
        return ShortDesc;
    }

    public void setShortDesc(String shortDesc) {
        ShortDesc = shortDesc;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getVideoUrl() {
        return VideoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        VideoUrl = videoUrl;
    }

    public String getThumbUrl() {
        return ThumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        ThumbUrl = thumbUrl;
    }
}
