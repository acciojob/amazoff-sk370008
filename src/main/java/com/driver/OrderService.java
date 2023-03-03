package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    void addOrder(Order order){
        orderRepository.addOrder(order);
    }

    void addPartner(String partnerId){
        orderRepository.addPartner(partnerId);
    }

    void addOrderPartnerPair(String orderId,String partnerId){
        orderRepository.addOrderPartnerPair(orderId,partnerId);
    }

    Order getOrderById(String orderId){
        return orderRepository.getOrderById(orderId);
    }

    DeliveryPartner getPartnerById(String partnerId){
        return orderRepository.getPartnerById(partnerId);
    }

    public Integer getOrderCountByPartnerId(String partnerId) {
        return orderRepository.getOrderCountByPartnerId(partnerId);
    }

    public List<String> getOrdersByPartnerId(String partnerId) {
        return orderRepository.getOrdersByPartnerId(partnerId);
    }

    public List<String> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    public Integer getCountOfUnassignedOrders() {
        return orderRepository.getCountOfUnassignedOrders();
    }

    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {
        String[] splitTime = time.split(":");
        int givenTime = Integer.parseInt(splitTime[0]) * 60 + Integer.parseInt(splitTime[1]);
        return orderRepository.getOrdersLeftAfterGivenTimeByPartnerId(givenTime,partnerId);
    }

}
