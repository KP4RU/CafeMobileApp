package com.example.project5cafeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 This class takes the current order that is being processed, and it can either remove any items from the order or place
 the order all while displaying the current items and price of the order.
 @author Karan Patel, Azaan Siddiqi
 */
public class OrderingBasketActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listOrderItems;

    private EditText subTotal;

    private EditText salesTax;

    private EditText orderTotal;

    private ArrayAdapter<String> adapter;

    private ArrayList<String> orderMenuItems;

    private static final double SALES_TAX = 0.06625;


    /**
     After the "Your Order" button is pressed in the main menu, this method fills the opened window with information
     pertaining to the current order. It fills the listOrderItems ListView object with all the items in the current
     order, and it sets the sub total, sales tax, and order total.
     @param savedInstanceState A Bundle object that is used to store the data of the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordering_basket);
        listOrderItems = findViewById(R.id.listOrderItems);
        subTotal = findViewById(R.id.subTotal);
        salesTax = findViewById(R.id.salesTax);
        orderTotal = findViewById(R.id.orderTotal);
        orderMenuItems = new ArrayList<>();
        for (int i = 0; i < MainActivity.currentOrder.getTotalMenuItems().size(); i++) {
             orderMenuItems.add(MainActivity.currentOrder.getTotalMenuItems().get(i).toString());
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, orderMenuItems);
        listOrderItems.setAdapter(adapter);
        listOrderItems.setOnItemClickListener(this);
        double calculatedSubTotal = MainActivity.currentOrder.subTotalCalculation();
        DecimalFormat paddingZeroes = new DecimalFormat("#,##0.00");
        subTotal.setText("$" + paddingZeroes.format(calculatedSubTotal));
        salesTax.setText("$" + paddingZeroes.format(calculatedSubTotal * SALES_TAX));
        orderTotal.setText("$" + paddingZeroes.format(calculatedSubTotal + calculatedSubTotal * SALES_TAX));
    }


    /**
     Places an order by adding the current order to the allStoreOrders instance variable in the MainActivity class.
     Then, it resets the GUI and relevant data fields.
     @param view A View object that represents the "Place Order" button on the "Your Order" GUI.
     */
    public void placeOrder(View view) {
        MainActivity.allStoreOrders.add(MainActivity.currentOrder);
        MainActivity.currentOrder = new Order(MainActivity.currentOrder.getOrderNumber() + 1);
        listOrderItems.setAdapter(null);
        subTotal.setText("$0.00");
        salesTax.setText("$0.00");
        orderTotal.setText("$0.00");
        Toast.makeText(view.getContext(), "Your order has been placed", Toast.LENGTH_LONG).show();

    }


    /**
     Activates when a menu item is clicked on the ListView object. It prompts the user to decide if they want to delete
     the menu item or not.
     @param adapterView the AdapterView object that is associated with the Adapter object related to the listOrderItems
     ListView object.
     @param view A View object that represents the listOrderItems ListView object.
     @param i the index position of the item in the ListView object.
     @param l the row id that corresponds to the item that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Are you sure you want to delete the following menu item?");
        alert.setMessage(adapterView.getAdapter().getItem(i).toString());
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {


            /**
             Removes the selected MenuItem object from both the current order and from the ListView object if the user
             presses "Yes" in the alert. Then, it recalculates the price.
             @param dialog represents the DialogInterface object that received the click.
             @param which an integer representing the position of the item that was clicked.
             */
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.currentOrder.remove(MainActivity.currentOrder.getTotalMenuItems().get(i));
                orderMenuItems.remove(i);
                adapter.notifyDataSetChanged();
                double calculatedSubTotal = MainActivity.currentOrder.subTotalCalculation();
                DecimalFormat paddingZeroes = new DecimalFormat("#,##0.00");
                subTotal.setText("$" + paddingZeroes.format(calculatedSubTotal));
                salesTax.setText("$" + paddingZeroes.format(calculatedSubTotal * SALES_TAX));
                orderTotal.setText("$" + paddingZeroes.format(calculatedSubTotal + calculatedSubTotal * SALES_TAX));
                Toast.makeText(view.getContext(), "Menu item has been removed", Toast.LENGTH_LONG).show();

            }
        }).setNegativeButton("No", null);
        AlertDialog dialog = alert.create();
        dialog.show();
    }
}