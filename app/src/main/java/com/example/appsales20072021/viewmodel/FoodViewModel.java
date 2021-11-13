package com.example.appsales20072021.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appsales20072021.base.BaseViewModel;
import com.example.appsales20072021.model.ApiResponse;
import com.example.appsales20072021.model.FoodModel;
import com.example.appsales20072021.model.UserModel;
import com.example.appsales20072021.repository.FoodRepository;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodViewModel extends BaseViewModel {
    private FoodRepository foodRepository;
    private MutableLiveData<List<FoodModel>> foodsModel = new MutableLiveData<>();

    public void updateFoodRepository(FoodRepository foodRepository){
        this.foodRepository = foodRepository;
    }

    public LiveData<List<FoodModel>> getFoodsModel(){
        return foodsModel;
    }


    public void fetchFoodsModel(){
        setLoading(true);
        foodRepository.getFoodsModel()
                .enqueue(new Callback<ApiResponse<List<FoodModel>>>() {
                    @Override
                    public void onResponse(Call<ApiResponse<List<FoodModel>>> call, Response<ApiResponse<List<FoodModel>>> response) {
                        if (response.body() != null) {
                            ApiResponse<List<FoodModel>> data = response.body();
                            foodsModel.setValue(data.getData());
                        } else {
                            try {
                                JSONObject jsonObject = new JSONObject(response.errorBody().string());
                                String message = jsonObject.getString("message");
                                setError(new Throwable(message));
                            } catch (JSONException | IOException e) {
                                e.printStackTrace();
                                setError(new Throwable(e.getMessage()));
                            }
                        }
                        setLoading(false);
                    }

                    @Override
                    public void onFailure(Call<ApiResponse<List<FoodModel>>> call, Throwable t) {
                        setError(t);
                        setLoading(false);
                    }
                });
    }
}
