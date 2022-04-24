package com.example.project5cafeapp;

import java.util.ArrayList;


/**
 This class is used to create Order objects that each contain an arraylist of MenuItem objects and an order number.
 There is a constructor that initializes an Order object, and there are getter methods that return the arraylist of
 MenuItem objects and the order number. There is also a toString method. You can add a MenuItem object to the arraylist
 and remove a MenuItem object from the arraylist.
 @author Karan Patel, Azaan Siddiqi
 */
public class Order implements Customizable {

    private ArrayList<MenuItem> totalMenuItems;
    private int orderNumber;


    /**
     Creates an order object that initializes the arraylist of MenuItem objects and contains an order number.
     @param orderNumber the order number of the Order object that is being created.
     */
    public Order(int orderNumber) {
        this.totalMenuItems = new ArrayList<>();
        this.orderNumber = orderNumber;
    }


    /**
     Returns an arraylist containing all the MenuItem objects of a specific Order object.
     @return an arraylist containing all the MenuItem objects of a specific Order object.
     */
    public ArrayList<MenuItem> getTotalMenuItems() {
        return this.totalMenuItems;
    }


    /**
     Returns the order number of the specified Order object.
     @return the order number of the specified Order object.
     */
    public int getOrderNumber() {
        return this.orderNumber;
    }


    /**
     Returns the sub total price of a specific order by calculating it based on all the menu items of that order.
     @return the sub total price of a specific order.
     */
    public double subTotalCalculation() {
        double subTotal = 0;
        for (int i = 0; i < totalMenuItems.size(); i++) {
            subTotal = subTotal + totalMenuItems.get(i).itemPrice();
        }
        return subTotal;
    }


    /**
     Adds a MenuItem object to the arraylist of MenuItem objects for a specific Order object.
     @param obj the MenuItem object to be added to the arraylist of MenuItem objects.
     @return true if the object in the parameter is a MenuItem object and it was successfully added to the arraylist,
     false otherwise.
     */
    public boolean add(Object obj) {
        if (obj instanceof MenuItem) {
            MenuItem addItem = (MenuItem) obj;
            totalMenuItems.add(addItem);
            return true;
        }
        return false;
    }


    /**
     Removes a MenuItem object from the arraylist of MenuItem objects for a specific Order object.
     @param obj the MenuItem object to be removed from the arraylist of MenuItem objects.
     @return true if the object in the parameter is a MenuItem object and it was successfully removed from the
     arraylist, false otherwise.
     */
    public boolean remove(Object obj) {
        if (obj instanceof MenuItem) {
            MenuItem removeItem = (MenuItem) obj;
            totalMenuItems.remove(removeItem);
            return true;
        }
        return false;
    }


    /**
     Returns a string that contains a description of all the menu items in a given order.
     @return a string that contains a description of all the menu items in the order.
     */
    @Override
    public String toString() {
        String orderDescription = "";
        for (int i = 0; i < totalMenuItems.size(); i++) {
            if (totalMenuItems.get(i) instanceof Donut) {
                Donut currentDonut = (Donut) totalMenuItems.get(i);
                orderDescription = orderDescription + currentDonut.toString() + "\n";
            } else if (totalMenuItems.get(i) instanceof Coffee) {
                Coffee currentCoffee = (Coffee) totalMenuItems.get(i);
                orderDescription = orderDescription + currentCoffee.toString() + "\n";
            }
        }
        return orderDescription;
    }
}
