package com.example.project5cafeapp;

import java.util.ArrayList;


/**
 This class is used to create StoreOrders objects that each contain an arraylist of Order objects. There is a
 constructor that initializes a StoreOrders object, and there is a getter method that returns the arraylist of Order
 objects. You can add an Order object to the arraylist and remove an Order object from the arraylist.
 @author Karan Patel, Azaan Siddiqi
 */
public class StoreOrders implements Customizable {

    private ArrayList<Order> totalOrders;


    /**
     Creates a StoreOrders object that initializes the arraylist of Order objects.
     */
    public StoreOrders() {
        this.totalOrders = new ArrayList<>();
    }


    /**
     Returns an arraylist containing all the Order objects of a specific StoreOrders object.
     @return an arraylist containing all the Order objects of a specific StoreOrders object.
     */
    public ArrayList<Order> getTotalOrders() {
        return this.totalOrders;
    }


    /**
     Adds an Order object to the arraylist of Order objects for a specific StoreOrders object.
     @param obj the Order object to be added to the arraylist of Order objects.
     @return true if the object in the parameter is an Order object and it was successfully added to the arraylist,
     false otherwise.
     */
    public boolean add(Object obj) {
        if (obj instanceof Order) {
            Order addOrder = (Order) obj;
            totalOrders.add(addOrder);
            return true;
        }
        return false;
    }


    /**
     Removes an Order object from the arraylist of Order objects for a specific StoreOrders object.
     @param obj the Order object to be removed from the arraylist of Order objects.
     @return true if the object in the parameter is an Order object and it was successfully removed from the arraylist,
     false otherwise.
     */
    public boolean remove(Object obj) {
        if (obj instanceof Order) {
            Order removeOrder = (Order) obj;
            totalOrders.remove(removeOrder);
            return true;
        }
        return false;
    }
}
