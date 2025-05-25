package com.pluralsight.services;

import com.pluralsight.models.Order;

public class OrderService {

    public Order createNewOrder() {
        System.out.println("OrderService: Creating a new order...");
        return new Order();
    }

    public boolean processOrder(Order order) {
        if (order == null) {
            System.err.println("OrderService: Cannot process a null order.");
            return false;
        }
        if (order.getItems().isEmpty()) {
            System.out.println("OrderService: Processing an empty order. No items to save.");
            return true;
        }

        System.out.println("OrderService: Processing order with total: $" + String.format("%.2f", order.calculateTotal()));
        return true;
    }
}
