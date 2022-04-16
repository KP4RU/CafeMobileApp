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

import java.util.ArrayList;

public class StoreOrdersActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listOrderItems;

    private ArrayAdapter<String> adapter;

    private ArrayList<String> ordersDescription;

    private static final double SALES_TAX = 0.06625;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);
        listOrderItems = findViewById(R.id.listOrderItemsTwo);
        ordersDescription = new ArrayList<>();
        DecimalFormat paddingZeroes = new DecimalFormat("#,##0.00");
        for (int i = 0; i < MainActivity.allStoreOrders.getTotalOrders().size(); i++) {
            double calculatedSubTotal = MainActivity.allStoreOrders.getTotalOrders().get(i).subTotalCalculation();
            ordersDescription.add("Order #" + MainActivity.allStoreOrders.getTotalOrders().get(i).getOrderNumber() + "\n\n" + MainActivity.allStoreOrders.getTotalOrders().get(i).toString() + "\n Total: " + paddingZeroes.format((calculatedSubTotal + calculatedSubTotal * SALES_TAX)));
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ordersDescription);
        listOrderItems.setAdapter(adapter);
        listOrderItems.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Are you sure you want to delete the following order?");
        alert.setMessage(adapterView.getAdapter().getItem(i).toString());
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.allStoreOrders.remove(MainActivity.allStoreOrders.getTotalOrders().get(i));
                ordersDescription.remove(i);
                adapter.notifyDataSetChanged();
            }
        }).setNegativeButton("No", null);
        AlertDialog dialog = alert.create();
        dialog.show();
    }
}