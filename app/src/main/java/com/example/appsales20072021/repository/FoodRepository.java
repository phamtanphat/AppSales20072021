package com.example.appsales20072021.repository;

import com.example.appsales20072021.api.ApiRequest;
import com.example.appsales20072021.api.RetrofitClient;
import com.example.appsales20072021.model.ApiResponse;
import com.example.appsales20072021.model.FoodModel;
import com.example.appsales20072021.model.UserModel;

import java.util.List;

import retrofit2.Call;

public class FoodRepository {
    private ApiRequest apiRequest;

    public FoodRepository(){
        apiRequest = RetrofitClient.getInstance().getApiRequest();
    }

    public Call<ApiResponse<List<FoodModel>>> getFoodsModel(){
        return apiRequest.getFoodsModel();
    }

}
