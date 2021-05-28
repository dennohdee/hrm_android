package com.example.hrm;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

interface StoreUser {
    @POST("users/add")
    @FormUrlEncoded
    Call<Post> savePost(@Field("name") String name,
                        @Field("email") String email);
}
