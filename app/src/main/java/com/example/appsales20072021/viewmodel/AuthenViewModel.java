package com.example.appsales20072021.viewmodel;

import android.os.Handler;
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

import java.io.IOException;

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

    public LiveData<ApiResponse<UserModel>> getResult() {
        return responseUserLiveData;
    }

    public void handleSignIn(String email, String password) {
        if (authenRepository != null) {
            loadingLiveData.setValue(true);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    authenRepository.signIn(new UserModel(email,password))
                            .enqueue(new Callback<ApiResponse<UserModel>>() {
                                @Override
                                public void onResponse(Call<ApiResponse<UserModel>> call, Response<ApiResponse<UserModel>> response) {
                                    if (response.body() != null) {
                                        ApiResponse<UserModel> data = response.body();
                                        responseUserLiveData.setValue(data);
                                    } else {
                                        try {
                                            JSONObject jsonObject = new JSONObject(response.errorBody().string());
                                            String message = jsonObject.getString("message");
                                            errorLiveData.setValue(new Throwable(message));
                                        } catch (JSONException | IOException e) {
                                            e.printStackTrace();
                                            errorLiveData.setValue(new Throwable(e.getMessage()));
                                        }
                                    }
                                    loadingLiveData.setValue(false);
                                }

                                @Override
                                public void onFailure(Call<ApiResponse<UserModel>> call, Throwable t) {
                                    Log.d("BBB", t.getMessage());
                                    errorLiveData.setValue(t);
                                    loadingLiveData.setValue(false);
                                }
                            });
                }
            }, 2000);

        }
    }

    public void handleSignUp(String email, String password, String fullName , String phone , String address) {
        if (authenRepository != null) {
            loadingLiveData.setValue(true);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    authenRepository.signUp(new UserModel(fullName,email,password,phone,address))
                            .enqueue(new Callback<ApiResponse<UserModel>>() {
                                @Override
                                public void onResponse(Call<ApiResponse<UserModel>> call, Response<ApiResponse<UserModel>> response) {
                                    if (response.body() != null) {
                                        ApiResponse<UserModel> data = response.body();
                                        responseUserLiveData.setValue(data);
                                    } else {
                                        try {
                                            JSONObject jsonObject = new JSONObject(response.errorBody().string());
                                            String message = jsonObject.getString("message");
                                            errorLiveData.setValue(new Throwable(message));
                                        } catch (JSONException | IOException e) {
                                            e.printStackTrace();
                                            errorLiveData.setValue(new Throwable(e.getMessage()));
                                        }
                                    }
                                    loadingLiveData.setValue(false);
                                }

                                @Override
                                public void onFailure(Call<ApiResponse<UserModel>> call, Throwable t) {
                                    Log.d("BBB", t.getMessage());
                                    errorLiveData.setValue(t);
                                    loadingLiveData.setValue(false);
                                }
                            });
                }
            }, 2000);

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
