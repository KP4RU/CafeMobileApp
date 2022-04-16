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

public class OrderingBasketActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listOrderItems;

    private EditText subTotal;

    private EditText salesTax;

    private EditText orderTotal;

    private ArrayAdapter<String> adapter;

    private ArrayList<String> orderMenuItems;

    private static final double SALES_TAX = 0.06625;

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

    public void placeOrder(View view) {
        MainActivity.allStoreOrders.add(MainActivity.currentOrder);
        MainActivity.currentOrder = new Order(MainActivity.currentOrder.getOrderNumber() + 1);
        listOrderItems.setAdapter(null);
        subTotal.setText("$0.00");
        salesTax.setText("$0.00");
        orderTotal.setText("$0.00");
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Demo the alert dialog.");
        alert.setMessage(adapterView.getAdapter().getItem(i).toString());
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
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
            }
        }).setNegativeButton("No", null);
        AlertDialog dialog = alert.create();
        dialog.show();
    }
}