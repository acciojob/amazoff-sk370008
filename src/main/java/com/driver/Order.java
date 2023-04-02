//package com.driver;
//
//public class Order {
//
//    private String id;
//
//    private int deliveryTime;
//
////    public Order(){
////
////    }
//
//    public Order(String id, String deliveryTime) {
//        this.id = id;
//        // The deliveryTime has to converted from string to int and then stored in the attribute
//        //deliveryTime  = HH*60 + MM
//        String[] splitted = deliveryTime.split(":");
//        this.deliveryTime = Integer.parseInt(splitted[0]) * 60 + Integer.parseInt(splitted[1]);
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public void setDeliveryTime(int deliveryTime) {
//        this.deliveryTime = deliveryTime;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public int getDeliveryTime() {return deliveryTime;}
//}


package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
        this.id = id;
        int hr = 0;
        int min = 0;
        for(int i=0; i<deliveryTime.length(); i++){
            char ch = deliveryTime.charAt(i);
            if(i==0 || i==1){
                hr = hr * 10 + (ch - '0');
            }
            if(i==3 || i==4){
                min = min * 10 + (ch - '0');
            }
        }

        int time = hr * 60 + min;
        this.deliveryTime = time;
    }


    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}