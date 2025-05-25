package com.pluralsight.ui;

import com.pluralsight.models.*;
import com.pluralsight.utilities.InputHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddSandwichScreen {
    private Order order;

    // A centralized list of all available toppings with their prices
    // In a real application, this might be loaded from a database or configuration file
    private static final List<Topping> ALL_AVAILABLE_TOPPINGS = new ArrayList<>();
    static {
        // Meats (premium toppings)
        ALL_AVAILABLE_TOPPINGS.add(new Topping("Steak", 1.50));
        ALL_AVAILABLE_TOPPINGS.add(new Topping("Ham", 1.00));
        ALL_AVAILABLE_TOPPINGS.add(new Topping("Turkey", 1.00));
        ALL_AVAILABLE_TOPPINGS.add(new Topping("Roast Beef", 1.50));
        ALL_AVAILABLE_TOPPINGS.add(new Topping("Salami", 1.00));
        ALL_AVAILABLE_TOPPINGS.add(new Topping("Chicken", 1.25));

        // Cheeses (premium toppings)
        ALL_AVAILABLE_TOPPINGS.add(new Topping("Cheddar Cheese", 0.75));
        ALL_AVAILABLE_TOPPINGS.add(new Topping("Provolone Cheese", 0.75));
        ALL_AVAILABLE_TOPPINGS.add(new Topping("Swiss Cheese", 0.75));
        ALL_AVAILABLE_TOPPINGS.add(new Topping("American Cheese", 0.75));

        // Regular Toppings (no extra cost)
        ALL_AVAILABLE_TOPPINGS.add(new Topping("Lettuce")); // Uses constructor with no price
        ALL_AVAILABLE_TOPPINGS.add(new Topping("Tomatoes"));
        ALL_AVAILABLE_TOPPINGS.add(new Topping("Onions"));
        ALL_AVAILABLE_TOPPINGS.add(new Topping("Jalapenos"));
        ALL_AVAILABLE_TOPPINGS.add(new Topping("Pickles"));
        ALL_AVAILABLE_TOPPINGS.add(new Topping("Olives"));
        ALL_AVAILABLE_TOPPINGS.add(new Topping("Peppers"));

        // Sauces (no extra cost)
        ALL_AVAILABLE_TOPPINGS.add(new Topping("Mayonnaise"));
        ALL_AVAILABLE_TOPPINGS.add(new Topping("Mustard"));
        ALL_AVAILABLE_TOPPINGS.add(new Topping("Ketchup"));
        ALL_AVAILABLE_TOPPINGS.add(new Topping("Ranch"));
        ALL_AVAILABLE_TOPPINGS.add(new Topping("Thousand Island"));
        ALL_AVAILABLE_TOPPINGS.add(new Topping("Vinaigrette"));
    }

    public AddSandwichScreen(Order order) {
        this.order = order;
    }

    public void display() {
        System.out.println("\n--- Build Your Sandwich ---");

        BreadType selectedBread = getBreadTypeFromUser();
        if (selectedBread == null) {
            System.out.println("Sandwich creation cancelled.");
            return;
        }

        Size selectedSize = getSandwichSizeFromUser();
        if (selectedSize == null) {
            System.out.println("Sandwich creation cancelled.");
            return;
        }

        // Initialize Sandwich. Toasted is false by default, will be set later.
        Sandwich sandwich = new Sandwich(selectedBread, selectedSize, false);

        addIngredientsToSandwich(sandwich); // Combined method for all ingredients

        String toastedInput = InputHelper.getString("Do you want your sandwich toasted? (yes/no): ").trim().toLowerCase();
        sandwich.setToasted(toastedInput.equals("yes"));

        order.addSandwich(sandwich);
        System.out.println("Sandwich added to order!");
    }

    private BreadType getBreadTypeFromUser() {
        while (true) {
            System.out.println("Select Bread Type:");
            for (BreadType type : BreadType.values()) {
                System.out.println("- " + type.toString());
            }
            String input = InputHelper.getString("Enter bread type: ").trim().toUpperCase();
            if (input.equalsIgnoreCase("cancel")) return null; // Allow cancellation
            try {
                return BreadType.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid bread type. Please choose from the list or type 'cancel'.");
            }
        }
    }

    private Size getSandwichSizeFromUser() {
        while (true) {
            System.out.println("Select Sandwich Size:");
            for (Size size : Size.values()) {
                System.out.println("- " + size.toString());
            }
            String input = InputHelper.getString("Enter size (SMALL, MEDIUM, LARGE): ").trim().toUpperCase();
            if (input.equalsIgnoreCase("cancel")) return null; // Allow cancellation
            try {
                return Size.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid size. Please choose SMALL, MEDIUM, or LARGE or type 'cancel'.");
            }
        }
    }

    private void addIngredientsToSandwich(Sandwich sandwich) {
        System.out.println("\n--- Add Ingredients (Meats, Cheeses, Toppings, Sauces) ---");
        System.out.println("Type an ingredient name to add it, or 'done' to finish.");
        System.out.println("Available ingredients (type name exactly):");
        ALL_AVAILABLE_TOPPINGS.forEach(topping -> System.out.println("- " + topping.getName() + (topping.getPrice() > 0 ? " ($" + String.format("%.2f", topping.getPrice()) + ")" : "")));

        while (true) {
            String input = InputHelper.getString("Add ingredient (or 'done'): ").trim();
            if (input.equalsIgnoreCase("done")) {
                break;
            }
            if (input.equalsIgnoreCase("cancel")) { // Allow cancellation of sandwich building
                System.out.println("Ingredient selection cancelled.");
                break;
            }

            Topping selectedTopping = ALL_AVAILABLE_TOPPINGS.stream()
                    .filter(t -> t.getName().equalsIgnoreCase(input))
                    .findFirst()
                    .orElse(null);

            if (selectedTopping != null) {
                sandwich.addTopping(selectedTopping);
                System.out.println(selectedTopping.getName() + " added.");
            } else {
                System.out.println("Ingredient not found. Please try again or type 'done' to finish.");
            }
        }
    }
}