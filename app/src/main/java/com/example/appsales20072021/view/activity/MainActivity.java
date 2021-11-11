package com.example.appsales20072021.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.example.appsales20072021.R;
import com.example.appsales20072021.model.ApiResponse;
import com.example.appsales20072021.model.UserModel;
import com.example.appsales20072021.viewmodel.AuthenViewModel;

public class MainActivity extends AppCompatActivity {

    AuthenViewModel mAuthenViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuthenViewModel = new ViewModelProvider(this,new AuthenViewModel.AuthenViewModelFactory()).get(AuthenViewModel.class);

        //observer data
        mAuthenViewModel.getResultSignIn().observe(this, new Observer<ApiResponse<UserModel>>() {
            @Override
            public void onChanged(ApiResponse<UserModel> userModelApiResponse) {
                Log.d("BBB",userModelApiResponse.getData().toString());
            }
        });

        // call event

        mAuthenViewModel.handleSignIn("nguyenvana@gmail.com","12345678");
    }
}