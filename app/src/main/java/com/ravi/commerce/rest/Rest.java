package com.ravi.commerce.rest;

import com.ravi.commerce.resp.login.LoginResponse;
import com.ravi.commerce.resp.register.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Rest {

    @Headers({"X-API-KEY: 3ae9d8799d1bb5e201e5704293bb54ef"})
    @FormUrlEncoded
    @POST("auth/login")
    Call<LoginResponse> getUserLogin(
            @Field("email") String email,
            @Field("password") String password);


    @Headers({"X-API-KEY: 3ae9d8799d1bb5e201e5704293bb54ef"})
    @FormUrlEncoded
    @POST("Auth/student_register")
    Call<RegisterResponse> getUserRegister(
            @Field("first_name") String first_name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("phone") String phone,
            @Field("last_name") String last_name);

}
