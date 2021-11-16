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

    public void updateRequest(ApiRequest apiRequest){
        this.apiRequest = apiRequest;
    }

    public Call<ApiResponse<List<FoodModel>>> getFoodsModel(){
        return apiRequest.getFoodsModel();
    }

    public Call<ApiResponse<OrderModel>> getTotalCount(){
        return apiRequest.totalCountCart();
    }

    public Call<ApiResponse<OrderModel>> getOrderModel(FoodModel foodModel){
        return apiRequest.addToCart(foodModel);
    }

    public Call<ApiResponse<CartModel>> getCartModel(){
        return apiRequest.getCart();
    }

    public Call<ApiResponse<String>> updateCart(OrderedItemModel orderedItemModel){
        return apiRequest.updateCart(orderedItemModel);
    }

}
