package com.example.appsales20072021.api;

import com.example.appsales20072021.model.ApiResponse;
import com.example.appsales20072021.model.UserModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiRequest {

    @FormUrlEncoded
    @POST("user/sign-in")
    Call<ApiResponse<UserModel>> signIn(
            @Field("email") String email,
            @Field("password") String password
    );
}
