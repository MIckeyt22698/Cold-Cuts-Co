package com.pluralsight.models;

import java.util.ArrayList;
import java.util.List;

public class Sandwich implements OrderableItems {
    private BreadType breadType;
    private Size size;
    private List<Topping> toppings; // Now holds Topping objects
    private boolean toasted;
    private double basePrice; // Base price determined by size

    public Sandwich(BreadType breadType, Size size, boolean toasted) {
        this.breadType = breadType;
        this.size = size;
        this.toasted = toasted;
        this.toppings = new ArrayList<>();
        this.basePrice = calculateBasePrice(size);
    }

    private double calculateBasePrice(Size size) {
        switch (size) {
            case SMALL:
                return 5.00; // Example: 4 inch
            case MEDIUM:
                return 7.00; // Example: 8 inch
            case LARGE:
                return 9.00; // Example: 12 inch
            default:
                return 0.00;
        }
    }

    // Getters
    public BreadType getBreadType() {
        return breadType;
    }

    public Size getSize() {
        return size;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public boolean isToasted() {
        return toasted;
    }

    // Setter for toasted status
    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }

    // Method to add a Topping object
    public void addTopping(Topping topping) {
        this.toppings.add(topping);
    }

    // Optional: Method to remove a Topping
    public void removeTopping(Topping topping) {
        this.toppings.remove(topping);
    }

    @Override // From OrderableItem
    public String getName() {
        String toastedStr = toasted ? " (toasted)" : "";
        return String.format("%s %s Sandwich%s", size.toString(), breadType.toString(), toastedStr);
    }

    @Override // From OrderableItem
    public double getPrice() {
        double totalPrice = basePrice;
        for (Topping topping : toppings) {
            totalPrice += topping.getPrice(); // Each topping contributes its price
        }
        return totalPrice;
    }

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

        // Display toppings nicely grouped
        List<Topping> meats = new ArrayList<>();
        List<Topping> cheeses = new ArrayList<>();
        List<Topping> regularToppings = new ArrayList<>();
        List<Topping> sauces = new ArrayList<>();

        // Categorize toppings (you could enhance this with a 'category' enum in Topping)
        for (Topping t : toppings) {
            String name = t.getName().toLowerCase();
            if (name.contains("steak") || name.contains("ham") || name.contains("turkey") || name.contains("roast beef") || name.contains("salami") || name.contains("chicken")) {
                meats.add(t);
            } else if (name.contains("cheese")) {
                cheeses.add(t);
            } else if (name.contains("mayonnaise") || name.contains("mustard") || name.contains("ketchup") || name.contains("ranch") || name.contains("thousand island") || name.contains("vinaigrette")) {
                sauces.add(t);
            } else {
                regularToppings.add(t);
            }
        }

        if (!meats.isEmpty()) {
            sb.append("      Meats: ");
            meats.forEach(m -> sb.append(m.getName()).append(", "));
            sb.setLength(sb.length() - 2);
            sb.append("\n");
        }
        if (!cheeses.isEmpty()) {
            sb.append("      Cheeses: ");
            cheeses.forEach(c -> sb.append(c.getName()).append(", "));
            sb.setLength(sb.length() - 2);
            sb.append("\n");
        }
        if (!regularToppings.isEmpty()) {
            sb.append("      Toppings: ");
            regularToppings.forEach(t -> sb.append(t.getName()).append(", "));
            sb.setLength(sb.length() - 2);
            sb.append("\n");
        }
        if (!sauces.isEmpty()) {
            sb.append("      Sauces: ");
            sauces.forEach(s -> sb.append(s.getName()).append(", "));
            sb.setLength(sb.length() - 2);
            sb.append("\n");
        }
        return sb.toString();
    }
}