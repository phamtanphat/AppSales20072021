package com.example.appsales20072021.repository;

import com.example.appsales20072021.api.ApiRequest;
import com.example.appsales20072021.api.RetrofitClient;
import com.example.appsales20072021.model.ApiResponse;
import com.example.appsales20072021.model.UserModel;

import retrofit2.Call;
import retrofit2.Retrofit;

public class AuthenRepository {
    private ApiRequest apiRequest;

    AuthenRepository(){
        apiRequest = RetrofitClient.getInstance().getApiRequest();
    }

    public Call<ApiResponse<UserModel>> signIn(String email , String password){
       return apiRequest.signIn(email,password);
    }

}
