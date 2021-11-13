package com.example.appsales20072021.api;

import com.example.appsales20072021.model.ApiResponse;
import com.example.appsales20072021.model.UserModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiRequest {

    @POST("user/sign-in")
    Call<ApiResponse<UserModel>> signIn(@Body UserModel userModel);

    @POST("user/sign-up")
    Call<ApiResponse<UserModel>> signUp(@Body UserModel userModel);
}
