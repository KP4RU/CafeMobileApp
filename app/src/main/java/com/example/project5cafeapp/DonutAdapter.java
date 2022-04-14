package com.example.project5cafeapp;

import android.content.Context;
import android.icu.text.DecimalFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
        String[] quantity = {"1", "2", "3", "4", "5"};
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

        public DonutHolder(@NonNull View itemView) {
            super(itemView);
            donutType = itemView.findViewById(R.id.donutType);
            donutFlavor = itemView.findViewById(R.id.donutFlavor);
            subTotal = itemView.findViewById(R.id.subTotalTwo);
            listQuantity = itemView.findViewById(R.id.listQuantityTwo);
            donutImage = itemView.findViewById(R.id.donutImage);
        }
    }
}
