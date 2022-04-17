package com.example.project5cafeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class OrderingDonutsActivity extends AppCompatActivity {

    private ArrayList<Donut> donuts = new ArrayList<>();

    private int[] images = {R.drawable.yeast_donut, R.drawable.yeast_donut, R.drawable.yeast_donut,
            R.drawable.yeast_donut, R.drawable.yeast_donut, R.drawable.cake_donut,
            R.drawable.cake_donut, R.drawable.cake_donut, R.drawable.cake_donut,
            R.drawable.cake_donut, R.drawable.donut_holes, R.drawable.donut_holes,
            R.drawable.donut_holes, R.drawable.donut_holes, R.drawable.donut_holes};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordering_donuts);
        RecyclerView recyclerView = findViewById(R.id.donutRecycler);
        String[] flavors = {"Strawberry", "Vanilla", "Chocolate", "Glazed", "Mint", "Frosted", "Blueberry", "Sugary", "Peach", "Peanut", "Mango", "Cherry", "Crunchy", "Powdered", "Apple"};
        for (int i = 0; i < 4; i++) {
            donuts.add(new Donut(1, flavors[i], "Yeast Donut", R.drawable.yeast_donut));
        }
        for (int i = 5; i < 9; i++) {
            donuts.add(new Donut(1, flavors[i], "Cake Donut", R.drawable.cake_donut));
        }
        for (int i = 10; i < 14; i++) {
            donuts.add(new Donut(1, flavors[i], "Donut Hole", R.drawable.donut_holes));
        }
        DonutAdapter adapter = new DonutAdapter(this, donuts);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}