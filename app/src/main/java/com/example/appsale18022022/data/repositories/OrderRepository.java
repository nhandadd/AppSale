package com.example.appsale18022022.data.repositories;

import android.content.Context;

import com.example.appsale18022022.data.datasources.remote.ApiService;
import com.example.appsale18022022.data.datasources.remote.AppResource;
import com.example.appsale18022022.data.datasources.remote.RetrofitClient;
import com.example.appsale18022022.data.models.Food;
import com.example.appsale18022022.data.models.Order;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;

public class OrderRepository {
    private ApiService apiService;

    public OrderRepository(Context context) {
        apiService = RetrofitClient.getInstance(context).getApiService();
    }

    public Call<AppResource<Order>> addToCart(String idFood) {
        HashMap<String,String> body = new HashMap<>();
        body.put("id_product",idFood);
        return apiService.addToCart(body);
    }
    public Call<AppResource<Order>> getCarts(String idOrder) {
        HashMap<String,String> body = new HashMap<>();
        body.put("id_order", idOrder);
        return apiService.getCarts(body);
    }
}
