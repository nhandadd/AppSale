package com.example.appsale18022022.data.datasources.remote;

import com.example.appsale18022022.data.models.Food;
import com.example.appsale18022022.data.models.Order;
import com.example.appsale18022022.data.models.User;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @POST("user/sign-in")
    Call<AppResource<User>> login(@Body User user);

    @POST("user/sign-up")
    Call<AppResource<User>> register(@Body User user);

    @GET("product")
    Call<AppResource<List<Food>>> fetchFoods();

    @POST("order/add-to-cart")
    Call<AppResource<Order>> addToCart(@Body HashMap<String,String> body);
    @GET("order")
    Call<AppResource<Order>> getCarts(@Body HashMap<String,String> body);
}
