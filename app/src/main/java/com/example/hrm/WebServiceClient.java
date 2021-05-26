package com.example.hrm;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebServiceClient {

    static Retrofit getClient() {

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)).build();

        return new Retrofit.Builder()
                .baseUrl("http://192.168.8.101:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

}