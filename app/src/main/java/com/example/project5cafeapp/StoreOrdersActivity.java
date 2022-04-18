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
 This class maintains all the store's orders in a ListView object and can cancel any of the orders. These orders are
 displayed in the "Store Orders" GUI.
 @author Karan Patel, Azaan Siddiqi
 */
public class StoreOrdersActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listOrderItems;

    private ArrayAdapter<String> adapter;

    private ArrayList<String> ordersDescription;

    private static final double SALES_TAX = 0.06625;


    /**
     After the "Store Orders" button is pressed in the main menu, this method fills the listOrderItems ListView object
     with the order number, order detail, and total cost of every order in the MainActivity's allStoreOrders instance
     variable.
     @param savedInstanceState A Bundle object that is used to store the data of the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);
        listOrderItems = findViewById(R.id.listOrderItemsTwo);
        ordersDescription = new ArrayList<>();
        DecimalFormat paddingZeroes = new DecimalFormat("#,##0.00");
        for (int i = 0; i < MainActivity.allStoreOrders.getTotalOrders().size(); i++) {
            double calculatedSubTotal = MainActivity.allStoreOrders.getTotalOrders().get(i).subTotalCalculation();
            ordersDescription.add("Order #" + MainActivity.allStoreOrders.getTotalOrders().get(i).getOrderNumber() + "\n\n" + MainActivity.allStoreOrders.getTotalOrders().get(i).toString() + "\nTotal: " + paddingZeroes.format((calculatedSubTotal + calculatedSubTotal * SALES_TAX)));
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ordersDescription);
        listOrderItems.setAdapter(adapter);
        listOrderItems.setOnItemClickListener(this);
    }


    /**
     Activates when an order is clicked on the ListView object. It prompts the user to decide if they want to delete
     the order or not.
     @param adapterView the AdapterView object that is associated with the Adapter object related to the listOrderItems
     ListView object.
     @param view A View object that represents the listOrderItems ListView object.
     @param i the index position of the item in the ListView object.
     @param l the row id that corresponds to the item that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Are you sure you want to delete the following order?");
        alert.setMessage(adapterView.getAdapter().getItem(i).toString());
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {


            /**
             If the user presses "Yes" in the alert, it cancels the current order that is being displayed on the
             "Store Orders" GUI by removing it from the allStoreOrders instance variable in the MainActivity class, and
             clears any information related to that order from the GUI.
             @param dialog represents the DialogInterface object that received the click.
             @param which an integer representing the position of the item that was clicked.
             */
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.allStoreOrders.remove(MainActivity.allStoreOrders.getTotalOrders().get(i));
                ordersDescription.remove(i);
                adapter.notifyDataSetChanged();
                Toast.makeText(view.getContext(), "Order has been removed", Toast.LENGTH_LONG).show();

            }
        }).setNegativeButton("No", null);
        AlertDialog dialog = alert.create();
        dialog.show();
    }
}