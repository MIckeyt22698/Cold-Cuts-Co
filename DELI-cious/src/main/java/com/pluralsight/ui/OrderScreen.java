package com.pluralsight.ui;

import com.pluralsight.models.Order;
import com.pluralsight.utilities.InputHelper;

import java.util.Scanner;
public class OrderScreen {
    private Order currentOrder;
    private HomeScreen homeScreen;

    public OrderScreen(Order order, HomeScreen homeScreen) {
        this.currentOrder = order;
        this.homeScreen = homeScreen;
    }

    public void display() {
        while (true) {
            System.out.println("\n--- Order Menu ---");
            System.out.println("Current Order Details:");
            System.out.println(currentOrder);

            System.out.println("1) Add Sandwich");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Chips");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel Order");
            System.out.print("Enter your choice: ");

            String input = InputHelper.getString("");

            switch (input) {
                case "1":
                    new AddSandwichScreen(currentOrder).display();
                    break;
                case "2":
                    new AddDrinkScreen(currentOrder).display();
                    break;
                case "3":
                    new AddChipsScreen(currentOrder).display();
                    break;
                case "4":
                    if (currentOrder.getItems().isEmpty()) {
                        System.out.println("Cannot checkout an empty order. Please add items.");
                    } else {
                        // THIS IS THE CORRECT FIX for a non-static display() method:
                        new CheckoutScreen(currentOrder).display(); // Create instance, then call display()
                        return; // Exit OrderScreen after checkout
                    }
                    break;
                case "0":
                    System.out.println("Order canceled. Returning to Home Screen.");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}