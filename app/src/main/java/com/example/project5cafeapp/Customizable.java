package com.example.project5cafeapp;


/**
 This interface contains an add method for adding an object, and a remove method for removing an object. It is
 implemented by the Coffee Class, the Order Class, and the StoreOrder class.
 @author Karan Patel, Azaan Siddiqi
 */
public interface Customizable {


    /**
     This method is used to add an object to somewhere.
     @param obj the object to be added to somewhere.
     @return true if the object was successfully added, false otherwise.
     */
    boolean add(Object obj);


    /**
     This method is used to remove an object from somewhere.
     @param obj the object to be removed from somewhere.
     @return true if the object was successfully removed, false otherwise.
     */
    boolean remove(Object obj);
}