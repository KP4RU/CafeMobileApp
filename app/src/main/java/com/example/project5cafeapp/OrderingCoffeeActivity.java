package com.example.project5cafeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class OrderingCoffeeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText subTotal;

    private Spinner listCoffeeSizes;

    private Spinner listQuantity;

    private ArrayAdapter<String> quantityAdapter;

    private ArrayAdapter<String> coffeeSizesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordering_coffee);
        listCoffeeSizes = findViewById(R.id.listCoffeeSizes);
        listCoffeeSizes.setOnItemSelectedListener(this);
        listQuantity = findViewById(R.id.listQuantity);
        listQuantity.setOnItemSelectedListener(this);
        String[] quantity = {"1", "2", "3", "4", "5"};
        String[] coffeeSizes = {"Short", "Tall", "Grande", "Venti"};
        quantityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, quantity);
        listQuantity.setAdapter(quantityAdapter);
        coffeeSizesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, coffeeSizes);
        listCoffeeSizes.setAdapter(coffeeSizesAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { }
    @Override
    public void onNothingSelected(AdapterView<?> parent) { } //can leave it empty

}