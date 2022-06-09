package com.example.appsale18022022.data.repositories;

import android.content.Context;

import com.example.appsale18022022.data.datasources.remote.ApiService;
import com.example.appsale18022022.data.datasources.remote.AppResource;
import com.example.appsale18022022.data.datasources.remote.RetrofitClient;
import com.example.appsale18022022.data.models.Food;

import java.util.List;

import retrofit2.Call;

public class FoodRepository {
    private ApiService apiService;

    public FoodRepository(Context context) {
        apiService = RetrofitClient.getInstance(context).getApiService();
    }

    public Call<AppResource<List<Food>>> fetchFoods() {
        return apiService.fetchFoods();
    }

}
