package com.pluralsight.models;

import java.util.ArrayList;
import java.util.List;

public class Sandwich implements OrderableItems {
    private BreadType breadType;
    private Size size;
    private List<Topping> toppings; // A list to hold all types of toppings (meat, cheese, veggies, sauces)
    private boolean toasted;
    private double basePrice; // Base price determined by the sandwich size

    /**
     * Constructor for a Sandwich.
     *
     * @param breadType The type of bread for the sandwich.
     * @param size The size of the sandwich (SMALL, MEDIUM, LARGE).
     * @param toasted True if the sandwich should be toasted, false otherwise.
     */
    public Sandwich(BreadType breadType, Size size, boolean toasted) {
        this.breadType = breadType;
        this.size = size;
        this.toasted = toasted;
        this.toppings = new ArrayList<>(); // Initialize the list for toppings
        this.basePrice = calculateBasePrice(size); // Calculate base price upon creation
    }

    /**
     * Calculates the base price of the sandwich based on its size.
     * This is a private helper method as pricing logic is internal to the Sandwich.
     * @param size The size of the sandwich.
     * @return The determined base price.
     */
    private double calculateBasePrice(Size size) {
        switch (size) {
            case SMALL:
                return 5.00; // Example: 4 inch sandwich
            case MEDIUM:
                return 7.00; // Example: 8 inch sandwich
            case LARGE:
                return 9.00; // Example: 12 inch sandwich
            default:
                return 0.00; // Should not happen
        }
    }

    /**
     * Gets the bread type of the sandwich.
     * @return The BreadType enum value.
     */
    public BreadType getBreadType() {
        return breadType;
    }

    /**
     * Gets the size of the sandwich.
     * @return The Size enum value.
     */
    public Size getSize() {
        return size;
    }

    /**
     * Gets the list of toppings added to the sandwich.
     * @return A List of Topping objects.
     */
    public List<Topping> getToppings() {
        return toppings;
    }

    /**
     * Checks if the sandwich is toasted.
     * @return True if toasted, false otherwise.
     */
    public boolean isToasted() {
        return toasted;
    }

    /**
     * Sets whether the sandwich should be toasted.
     * @param toasted True if the sandwich should be toasted, false otherwise.
     */
    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }

    /**
     * Adds a topping to the sandwich's list of ingredients.
     * @param topping The Topping object to add.
     */
    public void addTopping(Topping topping) {
        this.toppings.add(topping);
    }

    /**
     * Removes a specific topping from the sandwich's list of ingredients.
     * @param topping The Topping object to remove.
     */
    public void removeTopping(Topping topping) {
        this.toppings.remove(topping);
    }

    /**
     * Gets a display name for the sandwich.
     * @return A formatted string like "Large White Sandwich (toasted)".
     */
    @Override // From OrderableItem
    public String getName() {
        String toastedStr = toasted ? " (toasted)" : "";
        return String.format("%s %s Sandwich%s", size.toString(), breadType.toString(), toastedStr);
    }

    /**
     * Calculates the total price of the sandwich, including base price and all topping costs.
     * @return The total price of the sandwich.
     */
    @Override // From OrderableItem
    public double getPrice() {
        double totalPrice = basePrice;
        for (Topping topping : toppings) {
            totalPrice += topping.getPrice(); // Add the price of each topping
        }
        return totalPrice;
    }

    /**
     * Returns a detailed string representation of the sandwich, including its price and toppings.
     * All topping categories are printed in distinct colors.
     * @return A multi-line formatted string describing the sandwich.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Sandwich: %s on %s bread (Size: %s, Total Price: $%.2f)\n",
                getName(), breadType.toString(), size.toString(), getPrice()));

        if (toasted) {
            sb.append("      Toasted: Yes\n");
        } else {
            sb.append("      Toasted: No\n");
        }

        // --- Categorize and display toppings for better readability ---
        List<Topping> meats = new ArrayList<>();
        List<Topping> cheeses = new ArrayList<>();
        List<Topping> regularToppings = new ArrayList<>(); // This will be for veggies/misc
        List<Topping> sauces = new ArrayList<>();

        // Basic categorization based on name (can be improved with a 'category' in Topping)
        // This logic checks for keywords in topping names to categorize them.
        for (Topping t : toppings) {
            String nameLower = t.getName().toLowerCase();
            if (nameLower.contains("steak") || nameLower.contains("ham") || nameLower.contains("turkey") ||
                    nameLower.contains("roast beef") || nameLower.contains("salami") || nameLower.contains("chicken")) {
                meats.add(t);
            } else if (nameLower.contains("cheese")) {
                cheeses.add(t);
            } else if (nameLower.contains("mayonnaise") || nameLower.contains("mustard") || nameLower.contains("ketchup") ||
                    nameLower.contains("ranch") || nameLower.contains("thousand island") || nameLower.contains("vinaigrette")) {
                sauces.add(t);
            } else { // Catch-all for other toppings, typically vegetables
                regularToppings.add(t);
            }
        }

        // Append categorized topping lists if not empty, with recommended colors
        if (!meats.isEmpty()) {
            sb.append(ConsoleColors.BRIGHT_RED + "      Meats: "); // Recommended: Bright Red
            meats.forEach(m -> sb.append(m.getName()).append(", "));
            sb.setLength(sb.length() - 2); // Remove trailing comma and space
            sb.append(ConsoleColors.RESET + "\n"); // Reset color
        }
        if (!cheeses.isEmpty()) {
            sb.append(ConsoleColors.BRIGHT_YELLOW + "      Cheeses: "); // Recommended: Bright Yellow
            cheeses.forEach(c -> sb.append(c.getName()).append(", "));
            sb.setLength(sb.length() - 2);
            sb.append(ConsoleColors.RESET + "\n"); // Reset color
        }
        if (!regularToppings.isEmpty()) {
            sb.append(ConsoleColors.BRIGHT_GREEN + "      Toppings: "); // Recommended: Bright Green (for veggies/misc)
            regularToppings.forEach(t -> sb.append(t.getName()).append(", "));
            sb.setLength(sb.length() - 2);
            sb.append(ConsoleColors.RESET + "\n"); // Reset color
        }
        if (!sauces.isEmpty()) {
            sb.append(ConsoleColors.BRIGHT_CYAN + "      Sauces: "); // Recommended: Bright Cyan
            sauces.forEach(s -> sb.append(s.getName()).append(", "));
            sb.setLength(sb.length() - 2);
            sb.append(ConsoleColors.RESET + "\n"); // Reset color
        }
        return sb.toString();
    }
}