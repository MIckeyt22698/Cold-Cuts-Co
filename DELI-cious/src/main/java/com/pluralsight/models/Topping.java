package com.pluralsight.models;

public class Topping implements OrderableItems {
    private String name;
    private double price;
    private boolean isPremium; // Optional: to distinguish between regular and premium toppings

    // Constructor for regular toppings (e.g., lettuce, mayo)
    public Topping(String name) {
        this.name = name;
        this.price = 0.00; // Regular toppings often have no extra charge
        this.isPremium = false;
    }

    // Constructor for premium toppings (e.g., meats, cheeses, avocado)
    public Topping(String name, double price) {
        this.name = name;
        this.price = price;
        this.isPremium = (price > 0.00); // If price is > 0, consider it premium
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public boolean isPremium() {
        return isPremium;
    }

    @Override
    public String toString() {
        if (price > 0) {
            return String.format("%s ($%.2f)", name, price);
        } else {
            return name;
        }
    }
}

