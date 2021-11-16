package com.example.appsales20072021.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.appsales20072021.R;
import com.example.appsales20072021.databinding.ActivityDetailBinding;
import com.example.appsales20072021.databinding.ActivityHomeBinding;
import com.example.appsales20072021.model.FoodModel;
import com.example.appsales20072021.model.ImageModel;
import com.example.appsales20072021.view.adapter.SliderAdapter;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding mBinding;

    SliderView sliderView;
    List<Integer> images = new ArrayList<>();
    List<String> imgString = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        //Menu
        mBinding.toolbarDetail.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        images.add(R.drawable.circlek);
//        images.add(R.drawable.minhmap2);
//        images.add(R.drawable.phucloctho);
//
//        FoodModel foodModel = new FoodModel();
//        foodModel.images = new ArrayList<>();
//        foodModel.images.add(new ImageModel());
//        foodModel.images.add(new ImageModel());
//        foodModel.images.add(new ImageModel());
////        foodModel.images.get(0).imageUrl = "https://images.foody.vn/res/g95/942825/prof/s640x400/foody-upload-api-foody-mobile-avar2-190725141716.jpg";
////        foodModel.images.get(1).imageUrl = "https://images.foody.vn/res/g95/942825/s120x120/d3661864-9270-4c64-896e-32f6ee7991da.jpg";
////        foodModel.images.get(2).imageUrl = "https://images.foody.vn/res/g95/942825/s120x120/c0e5e86d-57f3-462c-9e40-e669e108521c.jpg";
//        imgString.add("https://images.foody.vn/res/g95/942825/prof/s640x400/foody-upload-api-foody-mobile-avar2-190725141716.jpg");
//        imgString.add("https://images.foody.vn/res/g95/942825/s120x120/d3661864-9270-4c64-896e-32f6ee7991da.jpg");
//        imgString.add("https://images.foody.vn/res/g95/942825/s120x120/c0e5e86d-57f3-462c-9e40-e669e108521c.jpg");

        Intent intent = getIntent();
        if(intent!=null){
            FoodModel foodModel = intent.getExtras().getParcelable("food");
            sliderView = findViewById(R.id.imageSliderFood);
            SliderAdapter sliderAdapter = new SliderAdapter(foodModel.images);
            //Slider
            sliderAdapter.setContext(this);
            sliderView.setSliderAdapter(sliderAdapter);
            sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
            sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
            sliderView.startAutoCycle();
            //Các textview
            mBinding.tvSliderFoodName.setText(foodModel.foodName);
            mBinding.tvSliderFoodPrice.setText(foodModel.price+"");
            mBinding.tvSliderFoodDescription.setText(foodModel.description);

        }


    }
}