package com.driver;

public class Order {

    private String id;

    private int deliveryTime;

//    public Order(){
//
//    }

    public Order(String id, String deliveryTime) {
        this.id = id;
        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
        String[] splitted = deliveryTime.split(":");
        this.deliveryTime = Integer.parseInt(splitted[0]) * 60 + Integer.parseInt(splitted[1]);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}
