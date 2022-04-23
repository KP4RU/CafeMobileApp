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

    private int [] images = {R.drawable.strawberryyeastdonut, R.drawable.vanillayeastdonut, R.drawable.chocolateyeastdonut,
            R.drawable.glazedyeastdonut, R.drawable.mintyeastdonut, R.drawable.frostedcakedonut, R.drawable.blueberrycakedonut,
            R.drawable.sugarycakedonut, R.drawable.peachcakedonut, R.drawable.peanutcakedonut, R.drawable.mangodonutholes,
            R.drawable.cherrydonutholes, R.drawable.crunchydonutholes, R.drawable.powdereddonutholes, R.drawable.appledonutholes};


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
            donuts.add(new Donut(1, flavors[i], "Yeast Donut", images[i]));
        }
        for (int i = 5; i < 10; i++) {
            donuts.add(new Donut(1, flavors[i], "Cake Donut", images[i]));
        }
        for (int i = 10; i < 15; i++) {
            donuts.add(new Donut(1, flavors[i], "Donut Hole", images[i]));
        }
        DonutAdapter adapter = new DonutAdapter(this, donuts);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}