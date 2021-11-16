package com.example.appsales20072021.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appsales20072021.R;
import com.example.appsales20072021.databinding.ItemFoodBinding;
import com.example.appsales20072021.model.FoodModel;

import java.util.List;

import javax.microedition.khronos.opengles.GL;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private List<FoodModel> lstFoodModels;
    private OnFoodListener onFoodListener;

    public FoodAdapter(){

    }

    public void updateListFoodModels(List<FoodModel> foodModels){
        lstFoodModels = foodModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        View view = layoutInflater.inflate(R.layout.item_food,parent,false);
//        return new FoodViewHolder(view);
        ItemFoodBinding itemFoodBinding = ItemFoodBinding.inflate(layoutInflater,parent,false);
        return new FoodViewHolder(itemFoodBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        ((FoodViewHolder)holder).bind(holder.itemView,lstFoodModels.get(position));
    }

    @Override
    public int getItemCount() {
        return lstFoodModels.size();
    }

    class FoodViewHolder extends RecyclerView.ViewHolder{
//        ImageView imageView;
//        TextView tvName, tvPrice;
//        public FoodViewHolder(View itemView){
//            super(itemView);
//            imageView = itemView.findViewById(R.id.imageView);
//            tvName = itemView.findViewById(R.id.textViewName);
//            tvPrice = itemView.findViewById(R.id.textViewPrice);
//        }
//        void bind(View itemView, FoodModel foodModel){
//            //Image
//                Glide.with(itemView)
//                        .load(foodModel.images.get(0).imageUrl)
//                        .into(imageView);
//            tvName.setText(foodModel.foodName);
//            tvPrice.setText(foodModel.price+"");
//        }
        //Binding
        ItemFoodBinding mBinding;

        public FoodViewHolder(ItemFoodBinding itemFoodBinding){
            super(itemFoodBinding.getRoot());
            mBinding = itemFoodBinding;

        }
        void bind(View itemView, FoodModel foodModel){
            //Image
            Glide.with(itemView)
                    .load(foodModel.images.get(0).imageUrl)
                    .into(mBinding.imageView);
            mBinding.textViewName.setText(foodModel.foodName);
            mBinding.textViewPrice.setText(foodModel.price+"");
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onFoodListener.setOnFoodClickListener(getAdapterPosition(),1);
                }
            });
            mBinding.btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onFoodListener.setOnFoodClickListener(getAdapterPosition(),2);
                }
            });
        }
    }

    public interface OnFoodListener{
        //type=1: nhấn vào thẻ
        //type=2: nhấn vào nút "Add to cart"
        public void setOnFoodClickListener(int position, int type);
    }
    public void setOnFoodListener(OnFoodListener onFoodListener){
        this.onFoodListener = onFoodListener;
    }

}
