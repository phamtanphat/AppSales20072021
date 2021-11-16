package com.example.appsales20072021.api;

import com.example.appsales20072021.model.ApiResponse;
import com.example.appsales20072021.model.CartModel;
import com.example.appsales20072021.model.FoodModel;
import com.example.appsales20072021.model.OrderModel;
import com.example.appsales20072021.model.OrderedItemModel;
import com.example.appsales20072021.model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiRequest {

    @POST("user/sign-in")
    Call<ApiResponse<UserModel>> signIn(@Body UserModel userModel);

    @POST("user/sign-up")
    Call<ApiResponse<UserModel>> signUp(@Body UserModel userModel);

    @GET("food/list/0/10")
    Call<ApiResponse<List<FoodModel>>> getFoodsModel();

    @GET("order/count/shopping-cart")
    Call<ApiResponse<OrderModel>> totalCountCart();

    @POST("order/add-to-cart")
    Call<ApiResponse<OrderModel>> addToCart( @Body FoodModel foodModel);

    @GET("order/shopping-cart")
    Call<ApiResponse<CartModel>> getCart();

    @POST("order/update")
    Call<ApiResponse<String>> updateCart( @Body OrderedItemModel orderedItemModel);


}
