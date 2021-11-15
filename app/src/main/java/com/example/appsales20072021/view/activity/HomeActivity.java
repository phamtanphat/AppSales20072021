package com.example.appsales20072021.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.appsales20072021.R;
import com.example.appsales20072021.databinding.ActivityHomeBinding;
import com.example.appsales20072021.model.FoodModel;
import com.example.appsales20072021.repository.AuthenRepository;
import com.example.appsales20072021.repository.FoodRepository;
import com.example.appsales20072021.view.adapter.FoodAdapter;
import com.example.appsales20072021.viewmodel.AuthenViewModel;
import com.example.appsales20072021.viewmodel.FoodViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding mBinding;
    FoodViewModel mFoodViewModel;
    FoodAdapter mFoodAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());



        initData();

        observerData();
        event();
    }

    private void initData() {
        mFoodViewModel = new ViewModelProvider(this).get(FoodViewModel.class);
        mFoodViewModel.updateFoodRepository(new FoodRepository());

        mBinding.toolbarHome.setTitle("Food");
        mBinding.toolbarHome.setTitleTextColor(Color.WHITE);

        mFoodAdapter = new FoodAdapter();
        mBinding.rcvFood.setHasFixedSize(true);
        mBinding.rcvFood.setAdapter(mFoodAdapter);

    }

    private void event() {
        mFoodViewModel.fetchFoodsModel();
    }

    private void observerData() {
        mFoodViewModel.getFoodsModel().observe(this, new Observer<List<FoodModel>>() {
            @Override
            public void onChanged(List<FoodModel> foodModels) {
                mFoodAdapter.updateListFoodModels(foodModels);
            }
        });

        mFoodViewModel.getLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if (isLoading) {
                    mBinding.containerLoading.layoutLoading.setVisibility(View.VISIBLE);
                } else {
                    mBinding.containerLoading.layoutLoading.setVisibility(View.GONE);
                }
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