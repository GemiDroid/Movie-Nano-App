package com.orchtech.baking_app.manager;

import com.orchtech.baking_app.models.ReceipesModel;
import com.orchtech.baking_app.repository.RetrofitRepository;
import com.orchtech.baking_app.webservice.APIs;

import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * Created by pc on 11/18/2017.
 */

public class ReceipeManager {

    RetrofitRepository retrofitRepository;

    public ReceipeManager() {retrofitRepository = new RetrofitRepository();}

    public Call<ReceipesModel> GetReceipes(){
        APIs Api_Service = retrofitRepository.getRetrofit().create(APIs.class);
        Call<ReceipesModel> call_receipes = Api_Service.GetReceipes();
        return call_receipes;
    }
}
