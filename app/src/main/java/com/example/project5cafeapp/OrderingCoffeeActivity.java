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
import android.widget.Toast;

import java.util.ArrayList;


/**
 This class allows the user to order coffee with specifications to the size, quantity, and add-ins of the coffee order.
 It has a method that adds the coffee order to the user's order. It also has a method that continuously updates and
 displays the subtotal of the user's current coffee order when changes are made to it. There is also an onCreate
 method that initializes the data.
 @author Karan Patel, Azaan Siddiqi
 */
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


    /**
     After the "Order Coffee" button is pressed in the main menu, this method initializes the data fields in the
     "Ordering Coffee" GUI. By default, the coffee size is set to "Short" and the quantity to 1, and no add-ins are
     selected. Then it displays the subtotal for this default coffee.
     @param savedInstanceState A Bundle object that is used to store the data of the activity.
     */
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
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, coffeeSizes);
        listCoffeeSizes.setAdapter(adapter);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, quantity);
        listQuantity.setAdapter(adapter);
        listCoffeeSizes.setOnItemSelectedListener(this);
        listQuantity.setOnItemSelectedListener(this);
    }


    /**
     Adds the specified Coffee object to the MainActivity's currentOrder instance variable, and then it resets the
     "Ordering Coffee" GUI data fields to their default.
     @param view A View object that represents the "Add to Order" button on the "Ordering Coffee" GUI.
     */
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
        Toast.makeText(view.getContext(), "Coffee(s) added to order", Toast.LENGTH_LONG).show();

    }


    /**
     This method concurrently updates the subtotal of the coffee order as changes are made to it, and displays
     the resulting total in the subTotal TextField.
     @param view A View object that represents a CheckBox object related to any add-in, or a Spinner object related to
     the quantity or size.
     */
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


    /**
     Calls the updateSubTotal method when a change is made to the quantity in the listQuantity Spinner object, or when
     a change is made to the size in the listCoffeeSizes Spinner object.
     @param parent the AdapterView object that is associated with the Adapter objects related to the listQuantity or
     listCoffeeSizes Spinner objects.
     @param view A View object that represents the listQuantity or listCoffeeSizes Spinner objects.
     @param position the index position of the item in the listQuantity or listCoffeeSizes Spinner objects.
     @param id the row id that corresponds to the item that was clicked.
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        updateSubTotal(view);
    }


    /**
     A empty method that is called when the selected item disappears from the listQuantity or listCoffeeSizes Spinner
     objects.
     @param parent the AdapterView object that is associated with the Adapter objects related to the listQuantity or
     listCoffeeSizes Spinner objects.
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) { }

}