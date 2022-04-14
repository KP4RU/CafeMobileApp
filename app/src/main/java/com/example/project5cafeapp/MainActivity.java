package com.example.project5cafeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static StoreOrders allStoreOrders = new StoreOrders();
    public static Order currentOrder = new Order(1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openOrderingBasketView(View view) {
        Intent intent = new Intent(this, OrderingBasketActivity.class);
        startActivity(intent);
    }

    public void openOrderingCoffeeView(View view) {
        Intent intent = new Intent(this, OrderingCoffeeActivity.class);
        startActivity(intent);
    }

    public void openOrderingDonutsView(View view) {
        Intent intent = new Intent(this, OrderingDonutsActivity.class);
        startActivity(intent);
    }

    public void openStoreOrdersView(View view) {
        Intent intent = new Intent(this, StoreOrdersActivity.class);
        startActivity(intent);
    }
}