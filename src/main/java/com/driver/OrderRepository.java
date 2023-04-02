package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class OrderRepository {

    //To keep track of orders
    HashMap<String,Order> orderHashMap = new HashMap<>();
    //To keep track of delivery partners
    HashMap<String,DeliveryPartner> deliveryPartnerHashMap = new HashMap<>();
    //To keep track of orders which are assigned to delivery partners
    HashMap<String,String> orderAssignedPartnerHashMap = new HashMap<>();
    //To keep the list of orders assigned to a delivery partner
    HashMap<String, List<String>> allAssignedOrdersToDeliveryPartnerHashMap = new HashMap<>();

    public void addOrder(Order order){
        //Effects:- orderHashMap

        if(order.getId() != null)
            orderHashMap.put(order.getId(),order);
    }

    public void addPartner(String partnerId){
        deliveryPartnerHashMap.put(partnerId,new DeliveryPartner(partnerId));
    }

    public void addOrderPartnerPair(String orderId,String partnerId){
        if (orderHashMap.containsKey(orderId) && deliveryPartnerHashMap.containsKey(partnerId)){
            orderAssignedPartnerHashMap.put(orderId,partnerId);

            List<String> currentOrder = new ArrayList<>();
            if (allAssignedOrdersToDeliveryPartnerHashMap.containsKey(partnerId)){
                currentOrder = allAssignedOrdersToDeliveryPartnerHashMap.get(partnerId);
            }
            currentOrder.add(orderId);
            allAssignedOrdersToDeliveryPartnerHashMap.put(partnerId,currentOrder);

            //Increase the number of orders of partner
            DeliveryPartner deliveryPartner = deliveryPartnerHashMap.get(partnerId);
            deliveryPartner.setNumberOfOrders(deliveryPartner.getNumberOfOrders() + 1);
        }
    }

    public Order getOrderById(String orderId){
        return orderHashMap.get(orderId);
    }

    public DeliveryPartner getPartnerById(String partnerId){
        return deliveryPartnerHashMap.get(partnerId);
    }

    public Integer getOrderCountByPartnerId(String partnerId) {
        return allAssignedOrdersToDeliveryPartnerHashMap.get(partnerId).size();
    }

    public List<String> getOrdersByPartnerId(String partnerId) {
        return allAssignedOrdersToDeliveryPartnerHashMap.get(partnerId);
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
        List<String> orders = allAssignedOrdersToDeliveryPartnerHashMap.get(partnerId);
        for (String order:orders){
            int deliveryTime = orderHashMap.get(order).getDeliveryTime();
            if (deliveryTime>givenTime){
                count++;
            }
        }
        return count;
    }


    public int getLastDeliveryTimeByPartnerId(String partnerId) {
        int maxTime =  0;
        List<String> orders = allAssignedOrdersToDeliveryPartnerHashMap.get(partnerId);
        for (String order:orders){
            int deliveryTime = orderHashMap.get(order).getDeliveryTime();
            maxTime = Math.max(maxTime,deliveryTime);
        }

        return maxTime;

    }

    public void deletePartnerById(String partnerId) {
        List<String> allOrders = allAssignedOrdersToDeliveryPartnerHashMap.get(partnerId);
        deliveryPartnerHashMap.remove(partnerId);
        allAssignedOrdersToDeliveryPartnerHashMap.remove(partnerId);
        for (String order:allOrders){
            orderAssignedPartnerHashMap.remove(order);
        }
    }

    public void deleteOrderById(String orderId) {
        orderHashMap.remove(orderId);
        String partnerId = orderAssignedPartnerHashMap.get(orderId);
        orderAssignedPartnerHashMap.remove(orderId);
        allAssignedOrdersToDeliveryPartnerHashMap.get(partnerId).remove(orderId);
        deliveryPartnerHashMap.get(partnerId).setNumberOfOrders(allAssignedOrdersToDeliveryPartnerHashMap.get(partnerId).size());

    }

    public HashMap<String, Order> check() {
        return orderHashMap;
    }
}
