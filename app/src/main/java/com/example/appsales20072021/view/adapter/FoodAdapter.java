package com.example.appsales20072021.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appsales20072021.R;
import com.example.appsales20072021.databinding.ItemFoodBinding;
import com.example.appsales20072021.model.FoodModel;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private List<FoodModel> lstFoodModels;

    public FoodAdapter() {
        lstFoodModels = new ArrayList<>();
    }

    public void updateListFoodModels(List<FoodModel> foodModels) {
        if (lstFoodModels.size() > 0) {
            lstFoodModels.clear();
        }
        lstFoodModels.addAll(foodModels);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemFoodBinding itemFoodBinding = ItemFoodBinding.inflate(layoutInflater,parent,false);
        return new FoodViewHolder(itemFoodBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        holder.bind(lstFoodModels.get(position));

    }

    @Override
    public int getItemCount() {
        return lstFoodModels != null && lstFoodModels.size() > 0 ? lstFoodModels.size() : 0;
    }

    class FoodViewHolder extends RecyclerView.ViewHolder {
        ItemFoodBinding mBinding;

        public FoodViewHolder(ItemFoodBinding itemFoodBinding) {
            super(itemFoodBinding.getRoot());
            mBinding = itemFoodBinding;
        }

        void bind(FoodModel foodModel) {
            Glide
                    .with(mBinding.getRoot().getContext())
                    .load(foodModel.images.get(0).imageUrl)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(mBinding.imageView);

            mBinding.textViewName.setText(foodModel.foodName);
            NumberFormat formatter = new DecimalFormat("#,###");
            mBinding.textViewPrice.setText("Giá " + formatter.format(foodModel.price) + " đ");
        }
    }
}
