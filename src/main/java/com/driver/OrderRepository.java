package com.driver;

import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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


}