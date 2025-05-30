package com.pluralsight.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Menu {
    private static final List<Topping> allAvailableToppings = new ArrayList<>();
    private static final List<String> allAvailableDrinks = new ArrayList<>();
    private static final List<String> allAvailableChips = new ArrayList<>();


    static {
        // --- Initialize Toppings ---
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
        allAvailableToppings.add(new Topping("Cucumbers")); // Adding one more for variety
        allAvailableToppings.add(new Topping("Spinach")); // Adding one more for variety

        // Sauces (no extra cost)
        allAvailableToppings.add(new Topping("Mayonnaise"));
        allAvailableToppings.add(new Topping("Mustard"));
        allAvailableToppings.add(new Topping("Ketchup"));
        allAvailableToppings.add(new Topping("Ranch"));
        allAvailableToppings.add(new Topping("Thousand Island"));
        allAvailableToppings.add(new Topping("Vinaigrette"));
        allAvailableToppings.add(new Topping("Pesto")); // Adding one more for variety
        allAvailableToppings.add(new Topping("Marinara")); // Adding one more for variety

        // --- Initialize Drinks ---
        allAvailableDrinks.add("Cola");
        allAvailableDrinks.add("Sprite");
        allAvailableDrinks.add("Orange Soda");
        allAvailableDrinks.add("Root Beer");
        allAvailableDrinks.add("Water");

        // --- Initialize Chips ---
        allAvailableChips.add("Potato Chips");
        allAvailableChips.add("Doritos");
        allAvailableChips.add("Cheetos");
        allAvailableChips.add("Pretzels");
    }

    /**
     * Returns an unmodifiable list of all available toppings.
     * @return List of Topping objects.
     */
    public static List<Topping> getAllAvailableToppings() {
        return Collections.unmodifiableList(allAvailableToppings);
    }

    /**
     * Returns an unmodifiable list of all available drinks.
     * @return List of String (drink names).
     */
    public static List<String> getAllAvailableDrinks() {
        return Collections.unmodifiableList(allAvailableDrinks);
    }

    /**
     * Returns an unmodifiable list of all available chips.
     * @return List of String (chip names).
     */
    public static List<String> getAllAvailableChips() {
        return Collections.unmodifiableList(allAvailableChips);
    }

    /**
     * Finds a topping by name from the list of all available toppings.
     * @param name The name of the topping to find.
     * @return An Optional containing the Topping if found, or empty if not.
     */
    public static Optional<Topping> getToppingByName(String name) {
        return allAvailableToppings.stream()
                .filter(t -> t.getName().equalsIgnoreCase(name))
                .findFirst();
    }
}