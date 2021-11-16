package com.example.appsales20072021;

import android.app.Application;

import com.example.appsales20072021.api.ApiRequest;
import com.example.appsales20072021.api.RetrofitClient;
import com.example.appsales20072021.repository.AuthenRepository;
import com.example.appsales20072021.repository.FoodRepository;

public class MyApplication extends Application {

    public FoodRepository foodRepository;
    public AuthenRepository authenRepository;
    public ApiRequest apiRequest;
    @Override
    public void onCreate() {
        super.onCreate();
        apiRequest = RetrofitClient.getInstance(this).getApiRequest();
        foodRepository = new FoodRepository();
        foodRepository.updateRequest(apiRequest);
        authenRepository = new AuthenRepository();
        authenRepository.updateRequest(apiRequest);

    }
}
