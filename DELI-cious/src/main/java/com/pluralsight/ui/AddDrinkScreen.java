package com.pluralsight.ui;

import com.pluralsight.models.Drink;
import com.pluralsight.models.Order;
import com.pluralsight.models.Size;
import com.pluralsight.utilities.InputHelper; // <--- ADD THIS LINE

import java.util.Arrays;
import java.util.List;


public class AddDrinkScreen {
    private Order order;

    // list of available drink flavors
    private static final List<String> AVAILABLE_FLAVORS = Arrays.asList(
            "Cola", "Diet Cola", "Sprite", "Root Beer", "Orange Soda", "Sweet Tea", "Unsweet Tea", "Water"
    );

    public AddDrinkScreen(Order order) {
        this.order = order;
    }

    public void display() {
        System.out.println("\n--- Add Drink ---");

        Size selectedSize = null;
        String sizeInput;
        boolean validSize = false;

        while (!validSize) {
            System.out.println("Available sizes: SMALL, MEDIUM, LARGE");
            System.out.print("Enter drink size (or 'cancel'): ");
            sizeInput = InputHelper.getString("").trim().toUpperCase();
            if (sizeInput.equalsIgnoreCase("cancel")) {
                System.out.println("Drink selection cancelled.");
                return;
            }
            try {
                selectedSize = Size.valueOf(sizeInput);
                validSize = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid size. Please choose SMALL, MEDIUM, or LARGE.");
            }
        }

        String selectedFlavor = null;
        boolean validFlavor = false;
        while (!validFlavor) {
            System.out.println("Available flavors: " + String.join(", ", AVAILABLE_FLAVORS));
            System.out.print("Enter drink flavor (or 'cancel'): ");
            String flavorInput = InputHelper.getString("").trim();
            if (flavorInput.equalsIgnoreCase("cancel")) {
                System.out.println("Drink selection cancelled.");
                return;
            }
            // Simple check if flavor exists in our list
            if (AVAILABLE_FLAVORS.stream().anyMatch(f -> f.equalsIgnoreCase(flavorInput))) {
                selectedFlavor = flavorInput;
                validFlavor = true;
            } else {
                System.out.println("Invalid flavor. Please choose from the list.");
            }
        }

        order.addDrink(new Drink(selectedSize, selectedFlavor));
        System.out.println(selectedSize.toString().toLowerCase() + " " + selectedFlavor + " drink added to order!");
    }
}

