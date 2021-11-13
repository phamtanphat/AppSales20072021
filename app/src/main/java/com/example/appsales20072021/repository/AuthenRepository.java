package com.example.appsales20072021.repository;

import com.example.appsales20072021.api.ApiRequest;
import com.example.appsales20072021.api.RetrofitClient;
import com.example.appsales20072021.model.ApiResponse;
import com.example.appsales20072021.model.UserModel;

import retrofit2.Call;

public class AuthenRepository {
    private ApiRequest apiRequest;

    public AuthenRepository(){
        apiRequest = RetrofitClient.getInstance().getApiRequest();
    }

    public Call<ApiResponse<UserModel>> signIn(UserModel userModel){
       return apiRequest.signIn(userModel);
    }

    public Call<ApiResponse<UserModel>> signUp(UserModel userModel){
        return apiRequest.signUp(userModel);
    }

}
