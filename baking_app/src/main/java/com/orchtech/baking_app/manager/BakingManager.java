package com.orchtech.baking_app.manager;

import com.orchtech.baking_app.models.BakingModel;
import com.orchtech.baking_app.repository.RetrofitRepository;
import com.orchtech.baking_app.webservice.APIs;

import java.util.List;

import retrofit2.Call;

/**
 * Created by pc on 11/18/2017.
 */

public class BakingManager {

    RetrofitRepository retrofitRepository;

    public BakingManager() {retrofitRepository = new RetrofitRepository();}

    public Call<List<BakingModel>> GetBakings(){
        APIs Api_Service = retrofitRepository.getRetrofit().create(APIs.class);
        Call<List<BakingModel>> call_receipes = Api_Service.GetBakings();
        return call_receipes;
    }
}
