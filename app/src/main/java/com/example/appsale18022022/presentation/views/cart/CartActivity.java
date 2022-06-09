package com.example.appsale18022022.presentation.views.cart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.appsale18022022.R;
import com.example.appsale18022022.data.datasources.remote.AppResource;
import com.example.appsale18022022.data.models.Food;
import com.example.appsale18022022.data.models.Order;
import com.example.appsale18022022.presentation.adapters.CartAdapter;
import com.example.appsale18022022.presentation.views.home.HomeActivity;
import com.example.appsale18022022.presentation.views.home.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    HomeViewModel cartViewModel;
    LinearLayout layoutLoading;
    RecyclerView rcvCart;
    CartAdapter cartAdapter;
    List<Food> mListFood;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        layoutLoading = findViewById(R.id.layout_loading);
        rcvCart = findViewById(R.id.recyclerViewCart);
        toolbar = findViewById(R.id.toolbar_cart);
        cartViewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

                return (T) new HomeViewModel(getApplicationContext());

            }}).get(HomeViewModel.class);
        mListFood = new ArrayList<>();
        cartAdapter = new CartAdapter();
        rcvCart.setAdapter(cartAdapter);
        rcvCart.setHasFixedSize(true);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Cart");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white,null));

        cartViewModel.getCart().observe(this, new Observer<AppResource<Order>>() {
            @Override
            public void onChanged(AppResource<Order> orderAppResource) {
                switch (orderAppResource.status) {
                    case LOADING:
                        layoutLoading.setVisibility(View.VISIBLE);
                        break;
                    case SUCCESS:
                        layoutLoading.setVisibility(View.GONE);
                        cartAdapter.updateListProduct(orderAppResource.data.getFoods());
                        break;
                    case ERROR:
                        Toast.makeText(CartActivity.this, orderAppResource.message, Toast.LENGTH_SHORT).show();
                        layoutLoading.setVisibility(View.GONE);
                        break;
                }
            }
        });
    }
}