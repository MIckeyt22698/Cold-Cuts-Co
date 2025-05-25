package com.pluralsight.models;

import java.util.ArrayList;
import java.util.List;

public class Order {
    // This list now generically holds any item that implements OrderableItem
    private List<OrderableItems> items;

    public Order() {
        this.items = new ArrayList<>();
    }

    // Generic method to add any item that can be ordered
    public void addItem(OrderableItems item) {
        this.items.add(item);
        System.out.println("Added to order: " + item.getName());
    }

    // Specific add methods for convenience, delegating to the generic addItem
    public void addDrink(Drink drink) {
        addItem(drink);
    }

    public void addSandwich(Sandwich sandwich) {
        addItem(sandwich);
    }

    public void addChips(Chips chips) {
        addItem(chips);
    }

    public List<OrderableItems> getItems() {
        return items;
    }

    public double calculateTotal() {
        double total = 0;
        for (OrderableItems item : items) {
            total += item.getPrice(); // Calculate total using getPrice() from OrderableItem
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Current Order Details ---\n");
        if (items.isEmpty()) {
            sb.append("  (No items in order yet)\n");
        } else {
            for (int i = 0; i < items.size(); i++) {
                OrderableItems item = items.get(i);
                sb.append(String.format("  %d. %s ($%.2f)\n", (i + 1), item.getName(), item.getPrice()));
                // If it's a Sandwich, print its detailed toString indented
                if (item instanceof Sandwich) {
                    String sandwichDetails = item.toString();
                    String[] lines = sandwichDetails.split("\n");
                    // Start from the second line of sandwichDetails to avoid duplicating the first line
                    for (int j = 1; j < lines.length; j++) {
                        sb.append("      ").append(lines[j]).append("\n");
                    }
                }
            }
        }
        sb.append("----------------------------------\n");
        sb.append(String.format("Subtotal: $%.2f\n", calculateTotal()));
        // Add tax, total logic here if needed
        return sb.toString();
    }
}