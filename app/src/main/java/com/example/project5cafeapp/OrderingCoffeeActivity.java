package com.example.project5cafeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderingCoffeeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView subTotal;

    private Spinner listCoffeeSizes;

    private Spinner listQuantity;

    private CheckBox caramel;

    private CheckBox cream;

    private CheckBox milk;

    private CheckBox syrup;

    private CheckBox whippedCream;

    private ArrayAdapter<String> adapter;

    private String[] quantity = {"1", "2", "3", "4", "5"};

    private String[] coffeeSizes = {"Short", "Tall", "Grande", "Venti"};

    private ArrayList<String> totalAddIns;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordering_coffee);
        totalAddIns = new ArrayList<>();
        subTotal = findViewById(R.id.subTotal);
        caramel = findViewById(R.id.caramel);
        cream = findViewById(R.id.cream);
        milk = findViewById(R.id.milk);
        syrup = findViewById(R.id.syrup);
        whippedCream = findViewById(R.id.whippedCream);
        listCoffeeSizes = findViewById(R.id.listCoffeeSizes);
        listQuantity = findViewById(R.id.listQuantity);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, coffeeSizes);
        listCoffeeSizes.setAdapter(adapter);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, quantity);
        listQuantity.setAdapter(adapter);
        listCoffeeSizes.setOnItemSelectedListener(this);
        listQuantity.setOnItemSelectedListener(this);
    }

    public void addToOrder(View view) {
        Coffee addCoffee = new Coffee(totalAddIns, listCoffeeSizes.getSelectedItem().toString(), Integer.parseInt(listQuantity.getSelectedItem().toString()));
        MainActivity.currentOrder.add(addCoffee);
        cream.setChecked(false);
        syrup.setChecked(false);
        milk.setChecked(false);
        caramel.setChecked(false);
        whippedCream.setChecked(false);
        totalAddIns = new ArrayList<>();
        listCoffeeSizes.setSelection(0);
        listQuantity.setSelection(0);
        subTotal.setText("$1.69");
    }

    public void updateSubTotal(View view) {
        Coffee currentCoffee = new Coffee(totalAddIns, listCoffeeSizes.getSelectedItem().toString(), Integer.parseInt(listQuantity.getSelectedItem().toString()));
        if (view instanceof CheckBox) {
            CheckBox item = (CheckBox) view;
            System.out.println(item.getText().toString());
            if (item.isChecked() == true) {
                currentCoffee.add(item.getText().toString());
            } else if (item.isChecked() == false) {
                currentCoffee.remove(item.getText().toString());
            }
        }
        DecimalFormat paddingZeroes = new DecimalFormat("#,##0.00");
        subTotal.setText("$" + paddingZeroes.format(currentCoffee.itemPrice()));
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        updateSubTotal(view);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) { } //can leave it empty

}