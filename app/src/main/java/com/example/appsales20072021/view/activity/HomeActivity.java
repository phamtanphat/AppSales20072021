package com.example.appsales20072021.view.activity;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appsales20072021.R;
import com.example.appsales20072021.databinding.ActivityHomeBinding;
import com.example.appsales20072021.model.FoodModel;
import com.example.appsales20072021.model.OrderModel;
import com.example.appsales20072021.repository.AuthenRepository;
import com.example.appsales20072021.repository.FoodRepository;
import com.example.appsales20072021.view.adapter.FoodAdapter;
import com.example.appsales20072021.viewmodel.AuthenViewModel;
import com.example.appsales20072021.viewmodel.FoodViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    //Menu
    private FrameLayout redCircle;
    private TextView countTextView;
    private int numOfProduct = 0;
    //Binding
    ActivityHomeBinding mBinding;
    //Model
    FoodViewModel mFoodViewModel;

    //Recyclerview
    RecyclerView mRcvFood;
    List<FoodModel> mListFood;
    FoodAdapter mFoodAdapter;

    //Token
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        //Menu
        prepareOptionsMenu();
        mBinding.toolbarHome.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return menuItemSelected(item);
            }
        });

        //ViewModel
        mFoodViewModel = new ViewModelProvider(this).get(FoodViewModel.class);
        mFoodViewModel.updateFoodRepository(new FoodRepository());

        //Recyclerview
        mRcvFood = mBinding.recyclerView;
        mListFood = new ArrayList<>();
        mFoodAdapter = new FoodAdapter();
        mFoodAdapter.updateListFoodModels(mListFood);
        mRcvFood.setLayoutManager(new LinearLayoutManager(this));
        mRcvFood.setHasFixedSize(true);
        mRcvFood.setAdapter(mFoodAdapter);

        //Token
        Intent intent = getIntent();
        if(intent != null){
            token = intent.getStringExtra("Token");
            Log.d("BBB", "Token: " + token);
        }


        observerData();
        event();
    }

    private void event() {
        //Load data
        mFoodViewModel.fetchFoodsModel();
        //Load TotalCount (số sản phẩm trong cart)
        mFoodViewModel.fetchTotalCount(token);

        //Event khi click Recyclerview
        mFoodAdapter.setOnFoodListener(new FoodAdapter.OnFoodListener() {
            @Override
            public void setOnFoodClickListener(int position, int type) {
                switch (type){
                    case 1: //Click vào item
                        Intent intent = new Intent(HomeActivity.this,DetailActivity.class);
                        intent.putExtra("food", mListFood.get(position));
                        startActivity(intent);
                        break;
                    case 2: //Click vào nút "Add to cart"
                        mFoodViewModel.fetchOrderModel(token,mListFood.get(position));
                        break;
                }

            }
        });
    }


    private void observerData() {
        //Observe List các sản phẩm
        mFoodViewModel.getFoodsModel().observe(this, new Observer<List<FoodModel>>() {
            @Override
            public void onChanged(List<FoodModel> foodModels) {
//                for (int i = 0; i < foodModels.size(); i++) {
//                    Log.d("BBB",foodModels.get(i).toString());
//                }
                mFoodAdapter.updateListFoodModels(foodModels);
                mListFood = foodModels;
            }
        });
        //Observe Total count cho giỏ hàng lần đầu
        mFoodViewModel.getTotalCount().observe(this, new Observer<OrderModel>() {
            @Override
            public void onChanged(OrderModel orderModel) {
                numOfProduct = orderModel.total;
                updateCartIcon(numOfProduct);
            }
        });

        //Observe Order object để cập nhật giỏ hàng
        mFoodViewModel.getOrderModelLiveData().observe(this, new Observer<OrderModel>() {
            @Override
            public void onChanged(OrderModel orderModel) {
                numOfProduct = orderModel.total;
                updateCartIcon(numOfProduct);
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

    private void updateCartIcon(int product) {
        // if alert count extends into two digits, just show the red circle
        if (0 < product && product < 90) {
            countTextView.setTextSize(15);
            countTextView.setText(String.valueOf(product));
        } else {
            countTextView.setTextSize(12);
            countTextView.setText("90+");
        }
//        Visibility là thuộc tính của View: setVisible() hoặc android;
//        - VISIBLE: hiển thị
//        - INVISIBLE: Không hiển thị nhưng có chiếm chỗ trong layout
//        - GONE: không hiển thị, không chiếm chỗ trong layout
        redCircle.setVisibility((product > 0) ? VISIBLE : GONE);
    }
    //Dành cho Cart menu có actionLayout
    public void prepareOptionsMenu() {
        final MenuItem menuItemView = mBinding.toolbarHome.getMenu().findItem(R.id.product_menu_cart_item);
        FrameLayout rootView = (FrameLayout) menuItemView.getActionView();
        redCircle = (FrameLayout) rootView.findViewById(R.id.view_cart_red_circle);
        countTextView = (TextView) rootView.findViewById(R.id.view_cart_count_textview);

        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuItemSelected(menuItemView);
            }
        });
    }

    public boolean menuItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.product_menu_cart_item:
//                numOfProduct += 1;
//                updateCartIcon(numOfProduct);
                Intent intent = new Intent(this, CartActivity.class);
                intent.putExtra("Token",token);
                startActivity(intent);
                return true;
//            case R.id.favorite:
//                Toast.makeText(HomeActivity.this, "Favourite Clicked", Toast.LENGTH_SHORT).show();
//                return true;
            default:
                return false;
        }
    }
}