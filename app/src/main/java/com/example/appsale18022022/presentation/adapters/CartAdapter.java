package com.example.appsale18022022.presentation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appsale18022022.R;
import com.example.appsale18022022.common.AppConstant;
import com.example.appsale18022022.data.models.Food;
import com.example.appsale18022022.data.models.Order;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    List<Food> listCarts;
    Context context;
    public CartAdapter() {
        listCarts = new ArrayList<>();
    }

    public void updateListProduct(List<Food> data) {
        if (listCarts != null && listCarts.size() > 0) {
            listCarts.clear();
        }
        listCarts.addAll(data);
        notifyDataSetChanged();
    }

    public List<Food> getListFoods(){
        return listCarts;
    }

    @NonNull
    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.bind(context, listCarts.get(position));
    }
    @Override
    public int getItemCount() {
        return listCarts.size();
    }
    class CartViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvQuantity, tvPrice;
        ImageView img;
        AppCompatButton btnAddQuantity, btnSubtract;

        public CartViewHolder(@NonNull View view) {
            super(view);
            tvQuantity = view.findViewById(R.id.quantityFood);
            tvName = view.findViewById(R.id.nameFood);
            tvPrice = view.findViewById(R.id.textViewPrice);
            img = view.findViewById(R.id.imageViewFood);
            btnAddQuantity = view.findViewById(R.id.buttonAddQuantity);
            btnSubtract = view.findViewById(R.id.buttonSubtractQuantity);

        }

        public void bind(Context context, Food food) {
            tvName.setText(food.getName());
            tvPrice.setText(new DecimalFormat("#,###").format(food.getPrice()) + " VND");
            Glide.with(context)
                    .load(AppConstant.BASE_URL + food.getImg())
                    .placeholder(R.drawable.image_logo)
                    .into(img);
        }
    }
}

