package com.example.appsales20072021.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.appsales20072021.model.ApiResponse;
import com.example.appsales20072021.model.UserModel;
import com.example.appsales20072021.repository.AuthenRepository;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthenViewModel extends ViewModel {

    private AuthenRepository authenRepository;
    private MutableLiveData<Boolean> loadingLiveData = new MutableLiveData<>();
    private MutableLiveData<ApiResponse<UserModel>> responseUserLiveData = new MutableLiveData<>();
    private MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();

    AuthenViewModel() {

    }

    public void setAuthenRepository(AuthenRepository repository) {
        this.authenRepository = repository;
    }

    public LiveData<Boolean> getLoading() {
        return loadingLiveData;
    }

    public LiveData<Throwable> getError() {
        return errorLiveData;
    }

    public LiveData<ApiResponse<UserModel>> getResultSignIn() {
        return responseUserLiveData;
    }

    public void handleSignIn(String email, String password) {
        if (authenRepository != null) {
            authenRepository.signIn(email, password)
                    .enqueue(new Callback<ApiResponse<UserModel>>() {
                        @Override
                        public void onResponse(Call<ApiResponse<UserModel>> call, Response<ApiResponse<UserModel>> response) {
                            if (response.body() != null){
                                ApiResponse<UserModel> data = response.body();
                                responseUserLiveData.setValue(data);
                            }else{
                                try {
                                    JSONObject jsonObject = new JSONObject(response.errorBody().toString());
                                    String message = jsonObject.getString("message");
                                    errorLiveData.setValue(new Throwable(message));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    errorLiveData.setValue(new Throwable(e.getMessage()));
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ApiResponse<UserModel>> call, Throwable t) {
                            Log.d("BBB",t.getMessage());
                        }
                    });
        }
    }


    public static class AuthenViewModelFactory implements ViewModelProvider.Factory {

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> viewModel) {
            if (viewModel.isAssignableFrom(ViewModel.class)) {
                throw new IllegalArgumentException("Error type");
            }
            return (T) new AuthenViewModel();
        }
    }
}
