package com.pluralsight.ui;

import com.pluralsight.models.*;
import com.pluralsight.utilities.InputHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AddSandwichScreen {
    private Order order;

    // A centralized list of all available toppings with their prices
    private static final List<Topping> allAvailableToppings = new ArrayList<>();
    static {
        // Meats (premium toppings)
        allAvailableToppings.add(new Topping("Steak", 1.50));
        allAvailableToppings.add(new Topping("Ham", 1.00));
        allAvailableToppings.add(new Topping("Turkey", 1.00));
        allAvailableToppings.add(new Topping("Roast Beef", 1.50));
        allAvailableToppings.add(new Topping("Salami", 1.00));
        allAvailableToppings.add(new Topping("Chicken", 1.25));

        // Cheeses (premium toppings)
        allAvailableToppings.add(new Topping("Cheddar Cheese", 0.75));
        allAvailableToppings.add(new Topping("Provolone Cheese", 0.75));
        allAvailableToppings.add(new Topping("Swiss Cheese", 0.75));
        allAvailableToppings.add(new Topping("American Cheese", 0.75));

        // Regular Toppings (no extra cost)
        allAvailableToppings.add(new Topping("Lettuce"));
        allAvailableToppings.add(new Topping("Tomatoes"));
        allAvailableToppings.add(new Topping("Onions"));
        allAvailableToppings.add(new Topping("Jalapenos"));
        allAvailableToppings.add(new Topping("Pickles"));
        allAvailableToppings.add(new Topping("Olives"));
        allAvailableToppings.add(new Topping("Peppers"));

        // Sauces (no extra cost)
        allAvailableToppings.add(new Topping("Mayonnaise"));
        allAvailableToppings.add(new Topping("Mustard"));
        allAvailableToppings.add(new Topping("Ketchup"));
        allAvailableToppings.add(new Topping("Ranch"));
        allAvailableToppings.add(new Topping("Thousand Island"));
        allAvailableToppings.add(new Topping("Vinaigrette"));
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

        Sandwich sandwich = new Sandwich(selectedBread, selectedSize, false); // Toasted is false by default

        // --- Step-by-step topping selection ---
        selectMeats(sandwich);
        selectCheeses(sandwich);
        selectRegularToppings(sandwich); // For veggies and other non-meat/cheese toppings
        selectSauces(sandwich);

        // --- Toasted option ---
        String toastedInput = InputHelper.getString("Do you want your sandwich toasted? (yes/no): ").trim().toLowerCase();
        sandwich.setToasted(toastedInput.equals("yes"));

        order.addSandwich(sandwich);
        System.out.println("\n" + ConsoleColors.BRIGHT_GREEN + "Sandwich successfully added to your order!" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BRIGHT_BLUE + "Current Order Summary:" + ConsoleColors.RESET);
        System.out.println(order.toString()); // Display updated order summary
    }

    private BreadType getBreadTypeFromUser() {
        while (true) {
            System.out.println(ConsoleColors.CYAN + "\n--- Select Bread Type ---" + ConsoleColors.RESET);
            for (BreadType type : BreadType.values()) {
                System.out.println(ConsoleColors.CYAN + "- " + type.toString() + ConsoleColors.RESET);
            }
            String input = InputHelper.getString(ConsoleColors.CYAN + "Enter bread type (or 'cancel'): " + ConsoleColors.RESET).trim().toUpperCase();
            if (input.equalsIgnoreCase("cancel")) return null;
            try {
                return BreadType.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println(ConsoleColors.RED + "Invalid bread type. Please choose from the list or type 'cancel'." + ConsoleColors.RESET);
            }
        }
    }

    private Size getSandwichSizeFromUser() {
        while (true) {
            System.out.println(ConsoleColors.CYAN + "\n--- Select Sandwich Size ---" + ConsoleColors.RESET);
            for (Size size : Size.values()) {
                System.out.println(ConsoleColors.CYAN + "- " + size.toString() + ConsoleColors.RESET);
            }
            String input = InputHelper.getString(ConsoleColors.CYAN + "Enter size (SMALL, MEDIUM, LARGE, or 'cancel'): " + ConsoleColors.RESET).trim().toUpperCase();
            if (input.equalsIgnoreCase("cancel")) return null;
            try {
                return Size.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println(ConsoleColors.RED + "Invalid size. Please choose SMALL, MEDIUM, or LARGE or type 'cancel'." + ConsoleColors.RESET);
            }
        }
    }

    // --- NEW SELECTION METHODS FOR TOPPINGS ---

    private void selectMeats(Sandwich sandwich) {
        List<Topping> availableMeats = allAvailableToppings.stream()
                .filter(t -> t.getName().toLowerCase().contains("steak") || t.getName().toLowerCase().contains("ham") ||
                        t.getName().toLowerCase().contains("turkey") || t.getName().toLowerCase().contains("roast beef") ||
                        t.getName().toLowerCase().contains("salami") || t.getName().toLowerCase().contains("chicken"))
                .collect(Collectors.toList());

        System.out.println(ConsoleColors.BRIGHT_RED + "\n--- Add Meats (type name to add, 'done' to finish) ---" + ConsoleColors.RESET);
        while (true) {
            displayToppingOptions(availableMeats, ConsoleColors.BRIGHT_RED); // Pass list and color
            String input = InputHelper.getString(ConsoleColors.BRIGHT_RED + "Add meat (or 'done'): " + ConsoleColors.RESET).trim();
            if (input.equalsIgnoreCase("done")) {
                break;
            }
            if (input.equalsIgnoreCase("cancel")) {
                System.out.println(ConsoleColors.RED + "Meat selection cancelled. No more meats will be added." + ConsoleColors.RESET);
                return; // Exit this selection phase
            }

            Topping selectedTopping = availableMeats.stream()
                    .filter(t -> t.getName().equalsIgnoreCase(input))
                    .findFirst()
                    .orElse(null);

            if (selectedTopping != null) {
                sandwich.addTopping(selectedTopping);
                System.out.println(ConsoleColors.BRIGHT_GREEN + selectedTopping.getName() + " added." + ConsoleColors.RESET);
            } else {
                System.out.println(ConsoleColors.RED + "Meat not found. Please try again or type 'done' to finish." + ConsoleColors.RESET);
            }
        }
    }

    private void selectCheeses(Sandwich sandwich) {
        List<Topping> availableCheeses = allAvailableToppings.stream()
                .filter(t -> t.getName().toLowerCase().contains("cheese"))
                .collect(Collectors.toList());

        System.out.println(ConsoleColors.BRIGHT_YELLOW + "\n--- Add Cheeses (type name to add, 'done' to finish) ---" + ConsoleColors.RESET);
        while (true) {
            displayToppingOptions(availableCheeses, ConsoleColors.BRIGHT_YELLOW); // Pass list and color
            String input = InputHelper.getString(ConsoleColors.BRIGHT_YELLOW + "Add cheese (or 'done'): " + ConsoleColors.RESET).trim();
            if (input.equalsIgnoreCase("done")) {
                break;
            }
            if (input.equalsIgnoreCase("cancel")) {
                System.out.println(ConsoleColors.RED + "Cheese selection cancelled. No more cheeses will be added." + ConsoleColors.RESET);
                return;
            }

            Topping selectedTopping = availableCheeses.stream()
                    .filter(t -> t.getName().equalsIgnoreCase(input))
                    .findFirst()
                    .orElse(null);

            if (selectedTopping != null) {
                sandwich.addTopping(selectedTopping);
                System.out.println(ConsoleColors.BRIGHT_GREEN + selectedTopping.getName() + " added." + ConsoleColors.RESET);
            } else {
                System.out.println(ConsoleColors.RED + "Cheese not found. Please try again or type 'done' to finish." + ConsoleColors.RESET);
            }
        }
    }

    private void selectRegularToppings(Sandwich sandwich) {
        // Filter out meats, cheeses, and sauces to get only "regular" toppings (veggies, etc.)
        List<Topping> availableRegularToppings = allAvailableToppings.stream()
                .filter(t -> {
                    String nameLower = t.getName().toLowerCase();
                    return !(nameLower.contains("steak") || nameLower.contains("ham") || nameLower.contains("turkey") ||
                            nameLower.contains("roast beef") || nameLower.contains("salami") || nameLower.contains("chicken") ||
                            nameLower.contains("cheese") ||
                            nameLower.contains("mayonnaise") || nameLower.contains("mustard") || nameLower.contains("ketchup") ||
                            nameLower.contains("ranch") || nameLower.contains("thousand island") || nameLower.contains("vinaigrette"));
                })
                .collect(Collectors.toList());

        System.out.println(ConsoleColors.BRIGHT_GREEN + "\n--- Add Other Toppings (type name to add, 'done' to finish) ---" + ConsoleColors.RESET);
        while (true) {
            displayToppingOptions(availableRegularToppings, ConsoleColors.BRIGHT_GREEN); // Pass list and color
            String input = InputHelper.getString(ConsoleColors.BRIGHT_GREEN + "Add topping (or 'done'): " + ConsoleColors.RESET).trim();
            if (input.equalsIgnoreCase("done")) {
                break;
            }
            if (input.equalsIgnoreCase("cancel")) {
                System.out.println(ConsoleColors.RED + "Topping selection cancelled. No more regular toppings will be added." + ConsoleColors.RESET);
                return;
            }

            Topping selectedTopping = availableRegularToppings.stream()
                    .filter(t -> t.getName().equalsIgnoreCase(input))
                    .findFirst()
                    .orElse(null);

            if (selectedTopping != null) {
                sandwich.addTopping(selectedTopping);
                System.out.println(ConsoleColors.BRIGHT_GREEN + selectedTopping.getName() + " added." + ConsoleColors.RESET);
            } else {
                System.out.println(ConsoleColors.RED + "Topping not found. Please try again or type 'done' to finish." + ConsoleColors.RESET);
            }
        }
    }

    private void selectSauces(Sandwich sandwich) {
        List<Topping> availableSauces = allAvailableToppings.stream()
                .filter(t -> t.getName().toLowerCase().contains("mayonnaise") || t.getName().toLowerCase().contains("mustard") ||
                        t.getName().toLowerCase().contains("ketchup") || t.getName().toLowerCase().contains("ranch") ||
                        t.getName().toLowerCase().contains("thousand island") || t.getName().toLowerCase().contains("vinaigrette"))
                .collect(Collectors.toList());

        System.out.println(ConsoleColors.BRIGHT_CYAN + "\n--- Add Sauces (type name to add, 'done' to finish) ---" + ConsoleColors.RESET);
        while (true) {
            displayToppingOptions(availableSauces, ConsoleColors.BRIGHT_CYAN); // Pass list and color
            String input = InputHelper.getString(ConsoleColors.BRIGHT_CYAN + "Add sauce (or 'done'): " + ConsoleColors.RESET).trim();
            if (input.equalsIgnoreCase("done")) {
                break;
            }
            if (input.equalsIgnoreCase("cancel")) {
                System.out.println(ConsoleColors.RED + "Sauce selection cancelled. No more sauces will be added." + ConsoleColors.RESET);
                return;
            }

            Topping selectedTopping = availableSauces.stream()
                    .filter(t -> t.getName().equalsIgnoreCase(input))
                    .findFirst()
                    .orElse(null);

            if (selectedTopping != null) {
                sandwich.addTopping(selectedTopping);
                System.out.println(ConsoleColors.BRIGHT_GREEN + selectedTopping.getName() + " added." + ConsoleColors.RESET);
            } else {
                System.out.println(ConsoleColors.RED + "Sauce not found. Please try again or type 'done' to finish." + ConsoleColors.RESET);
            }
        }
    }

    // --- Helper method to display categorized topping lists with color ---
    private void displayToppingOptions(List<Topping> toppings, String color) {
        if (toppings.isEmpty()) {
            System.out.println(color + "  (No options available in this category)" + ConsoleColors.RESET);
            return;
        }
        System.out.println(color + "Available options:" + ConsoleColors.RESET);
        toppings.forEach(topping ->
                System.out.println(color + "  - " + topping.getName() + (topping.getPrice() > 0 ? " ($" + String.format("%.2f", topping.getPrice()) + ")" : "") + ConsoleColors.RESET)
        );
        System.out.println(); // Spacing after options
    }
}