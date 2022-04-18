package com.example.project5cafeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


/**
 This class acts as the main menu of the GUI that stores and communicates information between the other activities.
 From the MainActivity's GUI, you can open the "Ordering Donuts" GUI, the "Ordering Coffee" GUI, the "Your Order" GUI,
 and the "Store Orders" GUI.
 @author Karan Patel, Azaan Siddiqi
 */
public class MainActivity extends AppCompatActivity {

    public static StoreOrders allStoreOrders = new StoreOrders();
    public static Order currentOrder = new Order(1);


    /**
     This class displays the main menu of the GUI by setting the activity content.
     @param savedInstanceState A Bundle object that is used to store the data of the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     Helps activate and display the "Your Order" GUI by creating an Intent object using the OrderingBasketActivity
     class, and starting an activity using this object.
     @param view A View object that represents the "Your Order" button on the main menu.
     */
    public void openOrderingBasketView(View view) {
        Intent intent = new Intent(this, OrderingBasketActivity.class);
        startActivity(intent);
    }


    /**
     Helps activate and display the "Ordering Coffee" GUI by creating an Intent object using the OrderingCoffeeActivity
     class, and starting an activity using this object.
     @param view A View object that represents the "Order Coffee" button on the main menu.
     */
    public void openOrderingCoffeeView(View view) {
        Intent intent = new Intent(this, OrderingCoffeeActivity.class);
        startActivity(intent);
    }


    /**
     Helps activate and display the "Ordering Donuts" GUI by creating an Intent object using the OrderingDonutsActivity
     class, and starting an activity using this object.
     @param view A View object that represents the "Order Donuts" button on the main menu.
     */
    public void openOrderingDonutsView(View view) {
        Intent intent = new Intent(this, OrderingDonutsActivity.class);
        startActivity(intent);
    }


    /**
     Helps activate and display the "Store Orders" GUI by creating an Intent object using the StoreOrdersActivity class,
     and starting an activity using this object.
     @param view A View object that represents the "Store Orders" button on the main menu.
     */
    public void openStoreOrdersView(View view) {
        Intent intent = new Intent(this, StoreOrdersActivity.class);
        startActivity(intent);
    }
}