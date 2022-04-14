package com.example.project5cafeapp;

import java.text.DecimalFormat;
import java.util.ArrayList;


/**
 This class extends the MenuItem class, implements the Customizable interface, and is a blueprint for the coffee menu
 item. It includes data such as the size of the cup, quantity, and add-ins for a coffee order. It has functions that
 return the price of the coffee and a toString method. Along with this the class has the capacity to add and remove
 add-ins from the coffee object.
 @author Azaan Siddiqi, Karan Patel
 */
public class Coffee extends MenuItem implements Customizable {

    private ArrayList<String> totalAddIns;
    private String sizeOfCoffee;
    private int quantity;

    private static final double SHORT_BLACK_COFFEE = 1.69;
    private static final double TALL_BLACK_COFFEE = 2.09;
    private static final double GRANDE_BLACK_COFFEE = 2.49;
    private static final double VENTI_BLACK_COFFEE = 2.89;
    private static final double ONE_ADD_IN_COST = 0.30;


    /**
     Coffee object constructor taking in specified parameters denoting add-ins, size, and quantity.
     @param totalAddIns an arraylist of strings that contains all the add-ins of a coffee object.
     @param sizeOfCoffee the size of the cup of the coffee.
     @param quantity the number of coffees being ordered.
     */
    public Coffee(ArrayList<String> totalAddIns, String sizeOfCoffee, int quantity) {
        this.totalAddIns = totalAddIns;
        this.sizeOfCoffee = sizeOfCoffee;
        this.quantity = quantity;
    }


    /**
     Adds a specified add-in to the Coffee item totalAddIns ArrayList.
     @param obj the add-in to be added to the totalAddIns ArrayList.
     @return true if the object is a valid add-in and it was successfully added, false otherwise.
     */
    public boolean add(Object obj) {
        if (obj instanceof String) {
            String addInName = (String) obj;
            if ((addInName.equals("Cream") || addInName.equals("Syrup") || addInName.equals("Milk") ||
                    addInName.equals("Caramel") || addInName.equals("Whipped Cream"))
                    && !totalAddIns.contains(addInName)) {
                totalAddIns.add(addInName);
                return true;
            }
        }
        return false;
    }


    /**
     Removes a specified add-in from the Coffee item totalAddIns ArrayList.
     @param obj the add-in to be removed from the totalAddIns ArrayList.
     @return true if the object is a valid add-in and it was successfully removed, false otherwise.
     */
    public boolean remove(Object obj) {
        if (obj instanceof String) {
            String addInName = (String) obj;
            if ((addInName.equals("Cream") || addInName.equals("Syrup") || addInName.equals("Milk") ||
                    addInName.equals("Caramel") || addInName.equals("Whipped Cream"))
                    && totalAddIns.contains(addInName)) {
                totalAddIns.remove(addInName);
                return true;
            }
        }
        return false;
    }


    /**
     Returns the price of a Coffee object, calculated based on size, number of add-ins, and quantity.
     @return the price of a Coffee object.
     */
    @Override
    public double itemPrice() {
        double price;
        if (this.sizeOfCoffee.equals("Short")) {
            price = ((totalAddIns.size() * ONE_ADD_IN_COST) + SHORT_BLACK_COFFEE) * this.quantity;
        } else if (this.sizeOfCoffee.equals("Tall")) {
            price = ((totalAddIns.size() * ONE_ADD_IN_COST) + TALL_BLACK_COFFEE) * this.quantity;
        } else if (this.sizeOfCoffee.equals("Grande")) {
            price = ((totalAddIns.size() * ONE_ADD_IN_COST) + GRANDE_BLACK_COFFEE) * this.quantity;
        } else {
            price = ((totalAddIns.size() * ONE_ADD_IN_COST) + VENTI_BLACK_COFFEE) * this.quantity;
        }
        DecimalFormat paddingZeroes = new DecimalFormat("#,##0.00");
        return Double.parseDouble(paddingZeroes.format(price));
    }


    /**
     Returns a string representation of a coffee object that includes its quantity, size of the cup, and add-ins.
     @return string representing a coffee item.
     */
    @Override
    public String toString() {
        return "Coffee: (" + this.quantity + ") " + this.sizeOfCoffee + " " + totalAddIns.toString();
    }
}
