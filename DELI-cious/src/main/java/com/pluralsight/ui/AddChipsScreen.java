package com.pluralsight.ui;

import com.pluralsight.models.Chips;
import com.pluralsight.models.Order;
import com.pluralsight.utilities.InputHelper;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AddChipsScreen {
    private Order order;

    // Example list of available chip flavors
    private static final List<String> AVAILABLE_CHIPS_FLAVORS = Arrays.asList(
            "Original", "BBQ", "Salt & Vinegar", "Cheddar & Sour Cream", "Spicy Nacho"
    );

    public AddChipsScreen(Order order) {
        this.order = order;
    }

    public void display() {
        System.out.println("\n--- Add Chips ---");

        String selectedFlavor = null;
        boolean validFlavor = false;

        while (!validFlavor) {
            System.out.println("Available chip flavors: " + String.join(", ", AVAILABLE_CHIPS_FLAVORS));
            System.out.print("Enter chip flavor (or 'cancel'): ");
            String flavorInput = InputHelper.getString("").trim();
            if (flavorInput.equalsIgnoreCase("cancel")) {
                System.out.println("Chips selection cancelled.");
                return;
            }
            if (AVAILABLE_CHIPS_FLAVORS.stream().anyMatch(f -> f.equalsIgnoreCase(flavorInput))) {
                selectedFlavor = flavorInput;
                validFlavor = true;
            } else {
                System.out.println("Invalid flavor. Please choose from the list.");
            }
        }

        order.addChips(new Chips(selectedFlavor));
        System.out.println(selectedFlavor + " chips added to order!");
    }
}
