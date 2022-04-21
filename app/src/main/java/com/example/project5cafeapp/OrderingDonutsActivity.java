package com.example.project5cafeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;


/**
 This class is the blueprint for the "Ordering Donuts" GUI which allows for the ordering of donuts. It initializes the
 RecyclerView object and the DonutAdapter object corresponding to it, where each item in the RecyclerView object
 contains information related to a Donut object.
 @author Karan Patel, Azaan Siddiqi
 */
public class OrderingDonutsActivity extends AppCompatActivity {

    private ArrayList<Donut> donuts = new ArrayList<>();

    private RecyclerView recyclerView;


    /**
     After the "Order Donuts" button is pressed in the main menu, this method initializes the RecyclerView object in the
     "Ordering Donuts" GUI and the DonutAdapter object corresponding to it. The DonutAdapter object contains an
     ArrayList object of 5 yeast donuts, 5 cake donuts, and 5 donut holes.
     @param savedInstanceState A Bundle object that is used to store the data of the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordering_donuts);
        recyclerView = findViewById(R.id.donutRecycler);
        String[] flavors = {"Strawberry", "Vanilla", "Chocolate", "Glazed", "Mint", "Frosted", "Blueberry", "Sugary", "Peach", "Peanut", "Mango", "Cherry", "Crunchy", "Powdered", "Apple"};
        for (int i = 0; i < 5; i++) {
            donuts.add(new Donut(1, flavors[i], "Yeast Donut", R.drawable.yeast_donut));
        }
        for (int i = 5; i < 10; i++) {
            donuts.add(new Donut(1, flavors[i], "Cake Donut", R.drawable.cake_donut));
        }
        for (int i = 10; i < 15; i++) {
            donuts.add(new Donut(1, flavors[i], "Donut Hole", R.drawable.donut_holes));
        }
        DonutAdapter adapter = new DonutAdapter(this, donuts);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}