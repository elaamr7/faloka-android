package com.example.faloka_mobile.Login;

import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.faloka_mobile.API.ApiConfig;
import com.example.faloka_mobile.API.ApiService;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Route;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository{

    public static final void userLogin(String email, String password, Context context){

        TokenManager tokenManager = TokenManager.getInstance(context.getSharedPreferences("Token",0));

        Call<LoginResponse> client = ApiConfig.getApiService(tokenManager).getSession(email,password);
        client.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        if(tokenManager.getToken()==null){
                            tokenManager.saveToken(response.body());
                        }
                    }
                }
                else{
                    if(response.body()!=null){}
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("failure", "onFailure: " + t.getMessage());
            }

        });
    }
}
