package com.orchtech.baking_app.webservice;

import com.orchtech.baking_app.models.BakingModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by pc on 11/18/2017.
 */

public interface APIs {


    @GET("baking.json")
    Call<List<BakingModel>> GetBakings();

}
