package com.example.project5cafeapp;


/**
 This abstract class is extended by the Donut class and the Coffee class, and it contains an abstract method called
 itemPrice() that returns the price of a Donut or Coffee.
 @author Karan Patel, Azaan Siddiqi
 */
public abstract class MenuItem {


    /**
     Returns the price of a Donut object or a Coffee object, which are both objects of subclasses of this class.
     @return the price of a Donut object or a Coffee object.
     */
    public abstract double itemPrice();
}
