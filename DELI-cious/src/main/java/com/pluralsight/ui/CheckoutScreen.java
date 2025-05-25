package com.pluralsight.ui;

import com.pluralsight.models.Order;
import com.pluralsight.services.ReceiptService;
import com.pluralsight.utilities.InputHelper;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class CheckoutScreen {
    private Order order; // Instance variable
    private ReceiptService receiptService; // Instance variable

    // Constructor to receive the order
    public CheckoutScreen(Order order) {
        this.order = order;
        this.receiptService = new ReceiptService(); // Initialize your ReceiptService
    }

    // Non-static display method
    public void display() {
        System.out.println("\n===== CHECKOUT =====");
        System.out.println(order); // This will use the Order's toString()

        System.out.println("\n1) Confirm Order");
        System.out.println("0) Cancel Order");
        System.out.print("Enter your choice: ");

        String input = InputHelper.getString(""); // Use InputHelper

        switch (input) {
            case "1":
                receiptService.saveReceipt(order); // Call method on instance of receiptService
                System.out.println("Order confirmed and receipt saved. Returning to Home Screen.");
                break;
            case "0":
                System.out.println("Order canceled. Returning to Home Screen.");
                break;
            default:
                System.out.println("Invalid option. Returning to Home Screen.");
        }
    }
}