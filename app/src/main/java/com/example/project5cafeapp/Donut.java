package com.example.project5cafeapp;

import java.text.DecimalFormat;


/**
 This class extends the MenuItem class and is a blueprint for the donut menu item. It includes data such as the
 quantity, flavor, and type of the donuts. It has functions that return the price of the donut and a toString method.
 Along with this the class has getter methods for the donut type and flavor.
 @author Azaan Siddiqi, Karan Patel
 */
public class Donut extends MenuItem {

    private int quantity;
    private String flavor;
    private String donutType;
    private int image;
    private static final double YEAST_DONUT_PRICE = 1.59;
    private static final double CAKE_DONUT_PRICE = 1.79;
    private static final double DONUT_HOLE_PRICE = 0.39;


    /**
     Donut object constructor taking in quantity, flavor, and donut type as parameters.
     @param quantity amount of donuts being ordered.
     @param flavor String denoting the flavor of the donut.
     @param donutType String denoting the type of donut being ordered.
     */
    public Donut(int quantity, String flavor, String donutType, int image) {
        this.quantity = quantity;
        this.flavor = flavor;
        this.donutType = donutType;
        this.image = image;
    }


    /**
     Returns the flavor of a specific donut object.
     @return flavor instance variable of donut object.
     */
    public String getFlavor() {
        return this.flavor;
    }


    /**
     Returns the type for a specific donut object.
     @return Donut type instance variable of donut object.
     */
    public String getDonutType() {
        return this.donutType;
    }


    public int getImage() {
        return this.image;
    }


    /**
     Returns the price of a Donut object, calculated based on donut type and quantity.
     @return the price of a Donut object.
     */
    @Override
    public double itemPrice() {
        DecimalFormat paddingZeroes = new DecimalFormat("#,##0.00");
        if (donutType.equals("Yeast Donut")) {
            return Double.parseDouble(paddingZeroes.format(YEAST_DONUT_PRICE * this.quantity));
        } else if (donutType.equals("Cake Donut")) {
            return Double.parseDouble(paddingZeroes.format(CAKE_DONUT_PRICE * this.quantity));
        } else {
            return Double.parseDouble(paddingZeroes.format(DONUT_HOLE_PRICE * this.quantity));
        }
    }


    /**
     Returns a string representation of a Donut object that its type, flavor, and quantity.
     @return string representing a donut item.
     */
    @Override
    public String toString() {
        return this.donutType + ": " + this.flavor + " (" + this.quantity + ")";
    }
}
