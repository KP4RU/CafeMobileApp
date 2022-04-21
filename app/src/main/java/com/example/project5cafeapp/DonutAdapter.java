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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


/**
 This is an Adapter class used to instantiate an Adapter for the RecyclerView that is specific to a Donut object. It includes
 a constructor for initializing a DonutAdapter object, as well as a getter method that returns the number of items in the
 adapter. There is also an inner class that is used to hold the information pertaining to a specific donut that will be displayed in
 one item slot of the RecyclerView object.
 @author Karan Patel, Azaan Siddiqi
 */
public class DonutAdapter extends RecyclerView.Adapter<DonutAdapter.DonutHolder> {

    private Context context;
    private ArrayList<Donut> donuts;


    /**
     DonutAdapter constructor that takes in in a Context object and an ArrayList of donut items.
     @param context A Context object that is used to inflate the layout.
     @param donutItems an ArrayList of Donut objects for the adapter to hold that is displayed through the RecyclerView.
     */
    public DonutAdapter(Context context, ArrayList<Donut> donutItems) {
        this.context = context;
        this.donuts = donutItems;
    }


    /**
     Using LayoutInflater and View objects, this method inflates the row layout for the items in the RecyclerView.
     @param parent The ViewGroup object that the new View object will be added to after it's connected to an Adapter
     position.
     @param viewType The new view's view type.
     @return A DonutHolder object that corresponds to an item in the RecyclerView.
     */
    @NonNull
    @Override
    public DonutHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.donut_recycler_design, parent, false);
        return new DonutHolder(view);
    }


    /**
     When an item in the RecyclerView is shown on the screen, it assigns values to the data fields for each row based on
     their index position.
     @param holder an instance of the DonutHolder object that is being displayed.
     @param position the index position of the item in the list of items.
     */
    @Override
    public void onBindViewHolder(@NonNull DonutHolder holder, int position) {
        holder.donutType.setText(donuts.get(position).getDonutType());
        holder.donutFlavor.setText(donuts.get(position).getFlavor());
        DecimalFormat paddingZeroes = new DecimalFormat("#,##0.00");
        holder.subTotal.setText("$" + paddingZeroes.format(donuts.get(position).itemPrice()));
        holder.donutImage.setImageResource(donuts.get(position).getImage());
        holder.listQuantity.setSelection(0);
    }


    /**
     Returns the size of the donuts ArrayList instance variable.
     @return the size of the donuts ArrayList instance variable.
     */
    @Override
    public int getItemCount() {
        return donuts.size();
    }


    /**
     This class is used to hold the information pertaining to a specific donut that will be displayed in
     one item slot of the RecyclerView object. It gets the views from the donut_recycle_design.xml file.
     @author Karan Patel, Azaan Siddiqi
     */
    public static class DonutHolder extends RecyclerView.ViewHolder {
        private TextView donutType;
        private TextView donutFlavor;
        private TextView subTotal;
        private Spinner listQuantity;
        private ImageView donutImage;
        private Button addToOrder;


        /**
         DonutHolder constructor that initializes the instance variables by assigning them to their respective views
         from the donut_recycle_design.xml file.
         @param itemView The View object that corresponds to the donut_recycler_design.xml file.
         */
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


        /**
         Sets the onClickListener for the "Add to Order" button on each row.
         @param itemView The View object that corresponds to the donut_recycler_design.xml file.
         */
        private void setAddToOrderOnClick(@NonNull View itemView) {
            addToOrder.setOnClickListener(new View.OnClickListener() {


                /**
                 When the "Add to Order" button is clicked, it adds the specified Donut object(s) to the MainActivity's
                 currentOrder instance variable. Then, it resets the "Ordering Donuts" GUI data fields to their default.
                 @param view The View object that corresponds to the donut_recycler_design.xml file.
                 */
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
                    Toast.makeText(itemView.getContext(), "Donut(s) added to order", Toast.LENGTH_LONG).show();
                }
            });
        }


        /**
         Sets the onItemSelectedListener for the listQuantity Spinner object on each row.
         @param itemView The View object that corresponds to the donut_recycler_design.xml file.
         */
        private void setPriceOnQuantityChanged(@NonNull View itemView) {
            listQuantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


                /**
                 When the quantity is changed in the listQuantity Spinner object, it updates and displays the subtotal
                 corresponding to that Donut object.
                 @param adapterView the AdapterView object that is associated with the Adapter object related to the
                 listQuantity Spinner object.
                 @param view The View object that corresponds to the donut_recycler_design.xml file.
                 @param i the index position of the item in the Spinner object.
                 @param l the row id that corresponds to the item that was clicked.
                 */
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    Donut updateDonut = new Donut(Integer.parseInt(listQuantity.getSelectedItem().toString()), donutFlavor.getText().toString(), donutType.getText().toString(), 0);
                    DecimalFormat paddingZeroes = new DecimalFormat("#,##0.00");
                    subTotal.setText("$" + paddingZeroes.format(updateDonut.itemPrice()));
                }


                /**
                 A empty method that is called when the selected item disappears from the listQuantity Spinner object.
                 @param adapterView the AdapterView object that is associated with the Adapter object related to the
                 listQuantity Spinner object.
                 */
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
    }
}
