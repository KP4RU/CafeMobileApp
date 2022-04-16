package com.example.project5cafeapp;

import android.content.Context;
import android.icu.text.DecimalFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DonutAdapter extends RecyclerView.Adapter<DonutAdapter.DonutHolder> {

    private Context context;
    private ArrayList<Donut> donuts;

    public DonutAdapter(Context context, ArrayList<Donut> donutItems) {
        this.context = context;
        this.donuts = donutItems;
    }

    @NonNull
    @Override
    public DonutHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.donut_recycler_design, parent, false);
        return new DonutHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonutHolder holder, int position) {
        holder.donutType.setText(donuts.get(position).getDonutType());
        holder.donutFlavor.setText(donuts.get(position).getFlavor());
        DecimalFormat paddingZeroes = new DecimalFormat("#,##0.00");
        holder.subTotal.setText(paddingZeroes.format(donuts.get(position).itemPrice()));
        holder.donutImage.setImageResource(donuts.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return donuts.size();
    }

    public static class DonutHolder extends RecyclerView.ViewHolder {
        private TextView donutType;
        private TextView donutFlavor;
        private TextView subTotal;
        private Spinner listQuantity;
        private ImageView donutImage;
        private Button addToOrder;

        public DonutHolder(@NonNull View itemView) {
            super(itemView);
            donutType = itemView.findViewById(R.id.donutType);
            donutFlavor = itemView.findViewById(R.id.donutFlavor);
            subTotal = itemView.findViewById(R.id.subTotalTwo);
            listQuantity = itemView.findViewById(R.id.listQuantityTwo);
            donutImage = itemView.findViewById(R.id.donutImage);
            addToOrder = itemView.findViewById(R.id.addToOrder);
            String[] quantity = {"1", "2", "3", "4", "5"};
            ArrayAdapter<String> adapter = new ArrayAdapter<>(itemView.getContext(), android.R.layout.simple_spinner_dropdown_item, quantity);
            listQuantity.setAdapter(adapter);
            setPriceOnQuantityChanged(itemView);
            setAddToOrderOnClick(itemView);

        }

        private void setAddToOrderOnClick(@NonNull View itemView) {
            addToOrder.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Donut addDonut = new Donut(Integer.parseInt(listQuantity.getSelectedItem().toString()), donutFlavor.getText().toString(), donutType.getText().toString(), 0);
                    MainActivity.currentOrder.add(addDonut);
                    listQuantity.setSelection(0);
                    if (donutType.getText().toString().equals("Yeast Donut")) {
                        subTotal.setText("$1.59");
                    } else if (donutType.getText().toString().equals("Cake Donut")) {
                        subTotal.setText("$1.79");
                    } else {
                        subTotal.setText("$0.39");
                    }
                }
            });
        }

        private void setPriceOnQuantityChanged(@NonNull View itemView) {
            listQuantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    Donut updateDonut = new Donut(Integer.parseInt(listQuantity.getSelectedItem().toString()), donutFlavor.getText().toString(), donutType.getText().toString(), 0);
                    DecimalFormat paddingZeroes = new DecimalFormat("#,##0.00");
                    subTotal.setText("$" + paddingZeroes.format(updateDonut.itemPrice()));
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
    }
}
