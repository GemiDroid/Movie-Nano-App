package com.orchtech.baking_app.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by pc on 11/17/2017.
 */

public class IngredientsModel implements Serializable, Parcelable {



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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Quantity);
        dest.writeString(this.Measure);
        dest.writeString(this.Ingredient);
    }

    public IngredientsModel() {
    }

    protected IngredientsModel(Parcel in) {
        this.Quantity = in.readString();
        this.Measure = in.readString();
        this.Ingredient = in.readString();
    }

    public static final Parcelable.Creator<IngredientsModel> CREATOR = new Parcelable.Creator<IngredientsModel>() {
        @Override
        public IngredientsModel createFromParcel(Parcel source) {
            return new IngredientsModel(source);
        }

        @Override
        public IngredientsModel[] newArray(int size) {
            return new IngredientsModel[size];
        }
    };
}
