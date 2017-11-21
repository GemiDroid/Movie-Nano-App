package com.orchtech.baking_app.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

/**
 * Created by pc on 11/17/2017.
 */

public class BakingModel implements Serializable {


    @SerializedName("id")
    @Expose
    private String Id;

    @SerializedName("name")
    @Expose
    private String Name;

    @SerializedName("ingredients")
    @Expose
    private List<IngredientsModel>  ingredientsModels;

    @SerializedName("steps")
    @Expose
    private List<StepsModel> stepsModels;

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

    public List<IngredientsModel> getIngredientsModels() {
        return ingredientsModels;
    }

    public void setIngredientsModels(List<IngredientsModel> ingredientsModels) {
        this.ingredientsModels = ingredientsModels;
    }

    public List<StepsModel> getStepsModels() {
        return stepsModels;
    }

    public void setStepsModels(List<StepsModel> stepsModels) {
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
}


