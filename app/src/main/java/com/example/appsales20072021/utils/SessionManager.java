package com.example.appsales20072021.utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;

import com.example.appsales20072021.R;

public class SessionManager {
    private Context context;
    private SharedPreferences prefs;
    private static final String USER_TOKEN = "user_token";

    public SessionManager(Context context) {
        this.context = context;
        prefs = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
    }

    //Function to save Authen token
    public void saveAuthToken(String token) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(USER_TOKEN, token);
        editor.apply();
    }

    //Function to fetch Authen token
    @Nullable
    public String fetchAuthToken() {
        return prefs.getString(USER_TOKEN, null);
    }
}
