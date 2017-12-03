package com.orchtech.baking_app.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 11/17/2017.
 */

public class BakingModel implements Serializable, Parcelable {




    @SerializedName("id")
    @Expose
    private String Id;

    @SerializedName("name")
    @Expose
    private String Name;

    @SerializedName("ingredients")
    @Expose
    private ArrayList<IngredientsModel> ingredientsModels;

    @SerializedName("steps")
    @Expose
    private ArrayList<StepsModel> stepsModels;

    @SerializedName("servings")
    @Expose
    private String Servings;

    @SerializedName("image")
    @Expose
    private String Image;


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public ArrayList<IngredientsModel> getIngredientsModels() {
        return ingredientsModels;
    }

    public void setIngredientsModels(ArrayList<IngredientsModel> ingredientsModels) {
        this.ingredientsModels = ingredientsModels;
    }

    public ArrayList<StepsModel> getStepsModels() {
        return stepsModels;
    }

    public void setStepsModels(ArrayList<StepsModel> stepsModels) {
        this.stepsModels = stepsModels;
    }

    public String getServings() {
        return Servings;
    }

    public void setServings(String servings) {
        Servings = servings;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Id);
        dest.writeString(this.Name);
        dest.writeTypedList(this.ingredientsModels);
        dest.writeTypedList(this.stepsModels);
        dest.writeString(this.Servings);
        dest.writeString(this.Image);
    }

    public BakingModel() {
    }

    protected BakingModel(Parcel in) {
        this.Id = in.readString();
        this.Name = in.readString();
        this.ingredientsModels = in.createTypedArrayList(IngredientsModel.CREATOR);
        this.stepsModels = in.createTypedArrayList(StepsModel.CREATOR);
        this.Servings = in.readString();
        this.Image = in.readString();
    }

    public static final Parcelable.Creator<BakingModel> CREATOR = new Parcelable.Creator<BakingModel>() {
        @Override
        public BakingModel createFromParcel(Parcel source) {
            return new BakingModel(source);
        }

        @Override
        public BakingModel[] newArray(int size) {
            return new BakingModel[size];
        }
    };
}


