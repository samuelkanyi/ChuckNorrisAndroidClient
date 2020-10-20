package com.example.chucknorrisapp.controller;

import com.example.chucknorrisapp.api.ChuckNorrisService;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller {
    static final String BASE_URL = "https://api.chucknorris.io/jokes/";
    private static Call call = null;
    private static Retrofit retrofit = null;

    public static ChuckNorrisService getInstance() {
        if (call == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ChuckNorrisService.class);
    }
}
