package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Repository
public class OrderRepository {

    //To keep track of orders
    HashMap<String,Order> orderHashMap;
    //To keep track of delivery partners
    HashMap<String,DeliveryPartner> deliveryPartnerHashMap;
    //To keep track of orders which are assigned to delivery partners
    HashMap<String,String> orderAssignedPartnerHashMap;
    //To keep the list of orders assigned to a delivery partner
    HashMap<String, HashSet<String>> allAssignedOrdersToDeliveryPartnerHashMap;

    public OrderRepository() {
        this.orderHashMap = new HashMap<String,Order>();
        this.deliveryPartnerHashMap = new HashMap<String, DeliveryPartner>();
        this.orderAssignedPartnerHashMap = new HashMap<String, String>();
        this.allAssignedOrdersToDeliveryPartnerHashMap = new HashMap<String, HashSet<String>>();
    }

    public void addOrder(Order order){
        //Effects:- orderHashMap
        String key = order.getId();
        if(key != null)
            orderHashMap.put(key,order);
    }

    public void addPartner(String partnerId){
        DeliveryPartner deliveryPartner = new DeliveryPartner(partnerId);
        partnerId = deliveryPartner.getId();
        deliveryPartnerHashMap.put(partnerId,deliveryPartner);
    }

    public void addOrderPartnerPair(String orderId,String partnerId){


        if (orderHashMap.containsKey(orderId) && deliveryPartnerHashMap.containsKey(partnerId)){
            //orderAssignedPartnerHashMap    ,    allAssignedOrdersToDeliveryPartnerHashMap

            HashSet<String> currentOrders = new HashSet<>();
           if (allAssignedOrdersToDeliveryPartnerHashMap.containsKey(partnerId)){
               currentOrders = allAssignedOrdersToDeliveryPartnerHashMap.get(partnerId);
           }
           currentOrders.add(orderId);

            allAssignedOrdersToDeliveryPartnerHashMap.put(partnerId,currentOrders);
            DeliveryPartner deliveryPartner = deliveryPartnerHashMap.get(partnerId);
            deliveryPartner.setNumberOfOrders(currentOrders.size());
            orderAssignedPartnerHashMap.put(orderId,partnerId);
        }

    }

    public Order getOrderById(String orderId){
        return orderHashMap.get(orderId);
    }

    public DeliveryPartner getPartnerById(String partnerId){
        DeliveryPartner deliveryPartner = null;
        if (deliveryPartnerHashMap.containsKey(partnerId)){
            deliveryPartner = deliveryPartnerHashMap.get(partnerId);
        }
        return deliveryPartner;
    }

    public Integer getOrderCountByPartnerId(String partnerId) {
        int orderCount = 0;
        if (allAssignedOrdersToDeliveryPartnerHashMap.containsKey(partnerId))
            orderCount = allAssignedOrdersToDeliveryPartnerHashMap.get(partnerId).size();

        return orderCount;
    }

    public List<String> getOrdersByPartnerId(String partnerId) {
        HashSet orderList = null;
        if (allAssignedOrdersToDeliveryPartnerHashMap.containsKey(partnerId)){
            orderList = allAssignedOrdersToDeliveryPartnerHashMap.get(partnerId);
        }

        List<String> orders = new ArrayList<>(orderList);
        return  orders;
    }


    public List<String> getAllOrders() {
        List<String> allOrders = new ArrayList<>();
        for (String order : orderHashMap.keySet()){
            allOrders.add(order);
        }
        return allOrders;
    }


    public Integer getCountOfUnassignedOrders() {
        return orderHashMap.size() - orderAssignedPartnerHashMap.size();
    }



    public Integer getOrdersLeftAfterGivenTimeByPartnerId(int givenTime, String partnerId) {
        int count = 0;
//        List<String> orders = allAssignedOrdersToDeliveryPartnerHashMap.get(partnerId);
//        for (String order:orders){
//            int deliveryTime = orderHashMap.get(order).getDeliveryTime();
//            if (deliveryTime>givenTime){
//                count++;
//            }
//        }

        HashSet<String> orders = null;
        if (allAssignedOrdersToDeliveryPartnerHashMap.containsKey(partnerId)){
            orders = allAssignedOrdersToDeliveryPartnerHashMap.get(partnerId);
        }
        for (String order :orders){
            int deliveryTime = orderHashMap.get(order).getDeliveryTime();
            if (deliveryTime>givenTime){
                count++;
            }
        }

        return count;
    }


    public int getLastDeliveryTimeByPartnerId(String partnerId) {
        int maxTime =  0;
//        List<String> orders = allAssignedOrdersToDeliveryPartnerHashMap.get(partnerId);
//        for (String order:orders){
//            int deliveryTime = orderHashMap.get(order).getDeliveryTime();
//            maxTime = Math.max(maxTime,deliveryTime);
//        }

        HashSet<String> orders = null;
        if (allAssignedOrdersToDeliveryPartnerHashMap.containsKey(partnerId)){
            orders = allAssignedOrdersToDeliveryPartnerHashMap.get(partnerId);
        }
        for (String order : orders){
            int deliveryTime = orderHashMap.get(order).getDeliveryTime();
            maxTime = Math.max(maxTime,deliveryTime);
        }
        return maxTime;

    }

    public void deletePartnerById(String partnerId) {
//        List<String> allOrders = allAssignedOrdersToDeliveryPartnerHashMap.get(partnerId);
//        deliveryPartnerHashMap.remove(partnerId);
//        allAssignedOrdersToDeliveryPartnerHashMap.remove(partnerId);
//        for (String order:allOrders){
//            orderAssignedPartnerHashMap.remove(order);
//        }

        deliveryPartnerHashMap.remove(partnerId);
        if (allAssignedOrdersToDeliveryPartnerHashMap.containsKey(partnerId))
            allAssignedOrdersToDeliveryPartnerHashMap.remove(partnerId);
    }

    public void deleteOrderById(String orderId) {
        orderHashMap.remove(orderId);
        String partnerId = orderAssignedPartnerHashMap.get(orderId);
        orderAssignedPartnerHashMap.remove(orderId);
        allAssignedOrdersToDeliveryPartnerHashMap.get(partnerId).remove(orderId);
        deliveryPartnerHashMap.get(partnerId).setNumberOfOrders(allAssignedOrdersToDeliveryPartnerHashMap.get(partnerId).size());

    }

    public HashMap<String, Order> check1() {
        return orderHashMap;
    }

    public HashMap<String, DeliveryPartner> check2() {
        return deliveryPartnerHashMap;
    }

    public HashMap<String,String> check3(){
        return orderAssignedPartnerHashMap;
    }

    public HashMap<String, HashSet<String>> check4() {
        return allAssignedOrdersToDeliveryPartnerHashMap;
    }
}

