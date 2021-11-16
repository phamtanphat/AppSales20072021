package com.example.appsales20072021.repository;

import com.example.appsales20072021.api.ApiRequest;
import com.example.appsales20072021.api.RetrofitClient;
import com.example.appsales20072021.model.ApiResponse;
import com.example.appsales20072021.model.CartModel;
import com.example.appsales20072021.model.FoodModel;
import com.example.appsales20072021.model.OrderModel;
import com.example.appsales20072021.model.OrderedItemModel;
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

    public Call<ApiResponse<OrderModel>> getTotalCount(String token){
        return apiRequest.totalCountCart(token);
    }

    public Call<ApiResponse<OrderModel>> getOrderModel(String token, FoodModel foodModel){
        return apiRequest.addToCart(token,foodModel);
    }

    public Call<ApiResponse<CartModel>> getCartModel(String token){
        return apiRequest.getCart(token);
    }

    public Call<ApiResponse<String>> updateCart(String token, OrderedItemModel orderedItemModel){
        return apiRequest.updateCart(token,orderedItemModel);
    }

}
