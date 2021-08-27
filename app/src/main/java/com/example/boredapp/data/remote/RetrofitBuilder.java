package com.example.boredapp.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    private RetrofitBuilder(){}

    private static BoredApi instance;

    public static BoredApi getInstance(){
        if (instance == null) {
            instance = createRetrofit();
        }
        return instance;
    }

    private static BoredApi createRetrofit(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        return new Retrofit.Builder()
                .baseUrl("https://android-3-mocker.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(BoredApi.class);
    }

}