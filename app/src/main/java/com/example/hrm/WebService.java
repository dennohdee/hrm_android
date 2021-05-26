package com.example.hrm;

import com.example.hrm.UserData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface WebService {

    @GET("users/get_all")
    Call<UserData> getUserData();

}