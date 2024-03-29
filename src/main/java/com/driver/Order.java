package com.driver;

public class Order {

    private String id;

    private int deliveryTime;


    public Order(String id, String deliveryTime) {
        this.id = id;
        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
        String[] splitted = deliveryTime.split(":");
        this.deliveryTime = Integer.parseInt(splitted[0]) * 60 + Integer.parseInt(splitted[1]);
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}

