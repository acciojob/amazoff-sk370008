package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Service
public class OrderService {

    OrderRepository orderRepository = new OrderRepository();

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

    public String getLastDeliveryTimeByPartnerId(String partnerId) {
        int time = orderRepository.getLastDeliveryTimeByPartnerId(partnerId);
        String HH = String.valueOf(time/60);
        String MM = String.valueOf(time%60);
        if(HH.length()<2){
            HH = '0' + HH;
        }
        if (MM.length()<2){
            MM = '0' + MM;
        }
        return  HH + ":" + MM;
    }

    public void deletePartnerById(String partnerId) {
        orderRepository.deletePartnerById(partnerId);
    }

    public void deleteOrderById(String orderId) {
        orderRepository.deleteOrderById(orderId);
    }

    public HashMap<String, Order> check1() {
        return orderRepository.check1();
    }

    public HashMap<String, DeliveryPartner> check2() {
        return orderRepository.check2();
    }

    public HashMap<String,String> check3(){
        return orderRepository.check3();
    }

    public HashMap<String, HashSet<String>> check4() {
        return orderRepository.check4();
    }
}
