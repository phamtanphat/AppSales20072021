package com.example.appsales20072021.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.appsales20072021.databinding.ActivitySignUpBinding;
import com.example.appsales20072021.model.ApiResponse;
import com.example.appsales20072021.model.UserModel;
import com.example.appsales20072021.repository.AuthenRepository;
import com.example.appsales20072021.viewmodel.AuthenViewModel;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding mBinding;
    AuthenViewModel mAuthenViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mAuthenViewModel = new ViewModelProvider(this, new AuthenViewModel.AuthenViewModelFactory()).get(AuthenViewModel.class);
        mAuthenViewModel.setAuthenRepository(new AuthenRepository());

        observerData();
        event();
    }
    private void event() {
        mBinding.buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mBinding.textEditEmail.getText().toString();
                String fullName = mBinding.textEditName.getText().toString();
                String phone = mBinding.textEditPhone.getText().toString();
                String address = mBinding.textEditLocation.getText().toString();
                String pass = mBinding.textEditPassword.getText().toString();

                if (email.length() > 0 && pass.length() > 0) {
                    mAuthenViewModel.handleSignUp(email,pass,fullName,phone,address);
                } else {
                    Toast.makeText(SignUpActivity.this, "Bạn chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void observerData() {
        mAuthenViewModel.getResult().observe(this, new Observer<ApiResponse<UserModel>>() {
            @Override
            public void onChanged(ApiResponse<UserModel> userModelApiResponse) {
                if (userModelApiResponse.getData() != null) {
                    Toast.makeText(SignUpActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        mAuthenViewModel.getLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if (isLoading) {
                    mBinding.containerLoading.layoutLoading.setVisibility(View.VISIBLE);
                } else {
                    mBinding.containerLoading.layoutLoading.setVisibility(View.GONE);
                }
            }
        });

        mAuthenViewModel.getError().observe(this, new Observer<Throwable>() {
            @Override
            public void onChanged(Throwable throwable) {
                if (throwable != null){
                    Toast.makeText(SignUpActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}