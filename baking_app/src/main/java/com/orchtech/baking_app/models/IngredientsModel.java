package com.orchtech.baking_app.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by pc on 11/17/2017.
 */

public class IngredientsModel implements Serializable {


    @SerializedName("quantity")
    @Expose
    private String Quantity;


    @SerializedName("measure")
    @Expose
    private String Measure;


    @SerializedName("ingredient")
    @Expose
    private String Ingredient;


    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getMeasure() {
        return Measure;
    }

    public void setMeasure(String measure) {
        Measure = measure;
    }

    public String getIngredient() {
        return Ingredient;
    }

    public void setIngredient(String ingredient) {
        Ingredient = ingredient;
    }
}
