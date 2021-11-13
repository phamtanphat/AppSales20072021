package com.example.appsales20072021.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.appsales20072021.R;
import com.example.appsales20072021.databinding.ActivityHomeBinding;
import com.example.appsales20072021.model.FoodModel;
import com.example.appsales20072021.repository.AuthenRepository;
import com.example.appsales20072021.repository.FoodRepository;
import com.example.appsales20072021.viewmodel.AuthenViewModel;
import com.example.appsales20072021.viewmodel.FoodViewModel;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding mBinding;
    FoodViewModel mFoodViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mFoodViewModel = new ViewModelProvider(this).get(FoodViewModel.class);
        mFoodViewModel.updateFoodRepository(new FoodRepository());

        observerData();
        event();
    }

    private void event() {
        mFoodViewModel.fetchFoodsModel();
    }

    private void observerData() {
        mFoodViewModel.getFoodsModel().observe(this, new Observer<List<FoodModel>>() {
            @Override
            public void onChanged(List<FoodModel> foodModels) {
                for (int i = 0; i < foodModels.size(); i++) {
                    Log.d("BBB",foodModels.get(i).toString());
                }
            }
        });

        mFoodViewModel.getLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
//                if (isLoading) {
//                    mBinding.containerLoading.layoutLoading.setVisibility(View.VISIBLE);
//                } else {
//                    mBinding.containerLoading.layoutLoading.setVisibility(View.GONE);
//                }
            }
        });

        mFoodViewModel.getError().observe(this, new Observer<Throwable>() {
            @Override
            public void onChanged(Throwable throwable) {
                if (throwable != null){
                    Toast.makeText(HomeActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}