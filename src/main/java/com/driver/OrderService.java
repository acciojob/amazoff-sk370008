//package com.driver;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.List;
//
//@Service
//public class OrderService {
//    @Autowired
//    OrderRepository orderRepository;
//
//    void addOrder(Order order){
//        orderRepository.addOrder(order);
//    }
//
//    void addPartner(String partnerId){
//        orderRepository.addPartner(partnerId);
//    }
//
//    void addOrderPartnerPair(String orderId,String partnerId){
//        orderRepository.addOrderPartnerPair(orderId,partnerId);
//    }
//
//    Order getOrderById(String orderId){
//        return orderRepository.getOrderById(orderId);
//    }
//
//    DeliveryPartner getPartnerById(String partnerId){
//        return orderRepository.getPartnerById(partnerId);
//    }
//
//    public Integer getOrderCountByPartnerId(String partnerId) {
//        return orderRepository.getOrderCountByPartnerId(partnerId);
//    }
//
//    public List<String> getOrdersByPartnerId(String partnerId) {
//        return orderRepository.getOrdersByPartnerId(partnerId);
//    }
//
//    public List<String> getAllOrders() {
//        return orderRepository.getAllOrders();
//    }
//
//    public Integer getCountOfUnassignedOrders() {
//        return orderRepository.getCountOfUnassignedOrders();
//    }
//
//    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {
//        String[] splitTime = time.split(":");
//        int givenTime = Integer.parseInt(splitTime[0]) * 60 + Integer.parseInt(splitTime[1]);
//        return orderRepository.getOrdersLeftAfterGivenTimeByPartnerId(givenTime,partnerId);
//    }
//
//    public String getLastDeliveryTimeByPartnerId(String partnerId) {
//        int time = orderRepository.getLastDeliveryTimeByPartnerId(partnerId);
//        String HH = String.valueOf(time/60);
//        String MM = String.valueOf(time%60);
//        if(HH.length()<2){
//            HH = '0' + HH;
//        }
//        if (MM.length()<2){
//            MM = '0' + MM;
//        }
//        return  HH + ":" + MM;
//    }
//
//    public void deletePartnerById(String partnerId) {
//        orderRepository.deletePartnerById(partnerId);
//    }
//
//    public void deleteOrderById(String orderId) {
//        orderRepository.deleteOrderById(orderId);
//    }
//
//    public HashMap<String, Order> check() {
//        return orderRepository.check();
//    }
//}



package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class OrderService {

    OrderRepository orderRepository = new OrderRepository();

    public void addOrder(Order order){                                                                  // 1st API
        orderRepository.addorder(order);
    }

    public void addPartner(String partnerId){                                                           // 2nd API
        orderRepository.addPartner(partnerId);
    }

    public void addOrderPartnerPair(String orderId,String partnerId){                                    // 3rd API
        orderRepository.addOrderPartnerPair(orderId,partnerId);
    }

    public Order getOrderById(String orderId){                                                          // 4th API
        Order order = orderRepository.getOrderById(orderId);
        return order;
    }

    public DeliveryPartner getPartnerById(String partnerId){                                            // 5th API
        DeliveryPartner deliveryPartner = orderRepository.getPartnerById(partnerId);
        return deliveryPartner;
    }

    public Integer getOrderCountByPartnerId(String partnerId){                                          // 6th API
        int orderCount = orderRepository.getOrderCountByPartnerId(partnerId);
        return orderCount;
    }

    public List<String> getOrdersByPartnerId(String partnerId){                                         // 7th API
        List<String> orders = orderRepository.getOrdersByPartnerId(partnerId);
        return orders;
    }

    public List<String> getAllOrders(){                                                                  // 8th API
        List<String> orders = orderRepository.getAllOrders();
        return orders;
    }

    public Integer getCountOfUnassignedOrders(){                                                         // 9th API
        Integer countOfOrders = orderRepository.getCountOfUnassignedOrders();
        return countOfOrders;
    }

    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time,String partnerId){                // 10th API
        int countOfOrders = orderRepository.getOrdersLeftAfterGivenTimeByPartnerId(time,partnerId);
        return countOfOrders;
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId) {                                    // 11th API
        String time = orderRepository.getLastDeliveryTimeByPartnerId(partnerId);
        return time;
    }

    public void deletePartnerById(String partnerId){                                                    // 12th API                                                                                  // 12th API
        orderRepository.deletePartnerById(partnerId);
    }

    public void deleteOrderById(String orderId){                                                        // 13th API                                                                                            // 13th API
        orderRepository.deleteOrderById(orderId);
    }
}