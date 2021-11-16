package com.example.appsales20072021.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appsales20072021.databinding.ItemCartBinding;
import com.example.appsales20072021.model.OrderedItemModel;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{
    private List<OrderedItemModel> lstOrderedItemModels;
    private OnCartListener onCartListener;

    public CartAdapter(){

    }

    public void updateListOrderedItemModels(List<OrderedItemModel> orderedItemModels){
        lstOrderedItemModels = orderedItemModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemCartBinding itemCartBinding = ItemCartBinding.inflate(layoutInflater,parent,false);
        return new CartViewHolder(itemCartBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        ((CartViewHolder)holder).bind(holder.itemView,lstOrderedItemModels.get(position));
    }

    @Override
    public int getItemCount() {
        return lstOrderedItemModels.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder{
        //Binding
         ItemCartBinding mBinding;

        public CartViewHolder(ItemCartBinding itemCartBinding){
            super(itemCartBinding.getRoot());
            mBinding = itemCartBinding;

        }
        void bind(View itemView, OrderedItemModel orderedItemModel){
            //Image
            Glide.with(itemView)
                    .load(orderedItemModel.images.get(0).imageUrl)
                    .into(mBinding.imageView);
            mBinding.textViewName.setText(orderedItemModel.foodName);
            mBinding.textViewPrice.setText(orderedItemModel.price+"");
            mBinding.textviewQuantity.setText(orderedItemModel.quantity+"");

            mBinding.btnMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onCartListener.setOnOrderedItemClickListener(getAdapterPosition(),1);
                }
            });

            mBinding.btnPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onCartListener.setOnOrderedItemClickListener(getAdapterPosition(),2);
                }
            });
        }
    }

    public interface OnCartListener{
        //type=1: nhấn vào nút "-"
        //type=2: nhấn vào nút "+"
        public void setOnOrderedItemClickListener(int position, int type);
    }
    public void setOnCartListener(OnCartListener onCartListener){
        this.onCartListener = onCartListener;
    }

}
