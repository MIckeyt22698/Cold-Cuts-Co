package com.pluralsight.ui;

import com.pluralsight.models.Order;
import com.pluralsight.services.OrderService;
import com.pluralsight.utilities.InputHelper;

import java.util.Scanner;

public class HomeScreen {
    private OrderService orderService; // Declare an instance

    public HomeScreen() {
        this.orderService = new OrderService(); // Initialize in the constructor
    }

    public void display() {
        while (true) {
            System.out.println("\n--- Home Screen ---");
            System.out.println("1) New Order");
            System.out.println("0) Exit");
            System.out.print("Enter your choice: ");

            String input = InputHelper.getString("");

            switch (input) {
                case "1":
                    Order newOrder = orderService.createNewOrder(); // Use the service to create the order
                    OrderScreen orderScreen = new OrderScreen(newOrder, this);
                    orderScreen.display();
                    break;
                case "0":
                    System.out.println("Exiting application. Goodbye!");
                    InputHelper.closeScanner();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
