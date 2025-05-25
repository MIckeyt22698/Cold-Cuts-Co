package com.pluralsight.models;

public class Chips implements OrderableItems { // <--- ADD "implements OrderableItem" HERE
    private String flavor;
    private double price;

    public Chips(String flavor) {
        this.flavor = flavor;
        this.price = 1.50; // Example price for chips
    }

    @Override
    public String getName() {
        return flavor + " Chips";
    }

    @Override
    public double getPrice() {
        return price;
    }

    // Existing getter
    public String getFlavor() {
        return flavor;
    }

    @Override
    public String toString() {
        return String.format("%s ($%.2f)", getName(), price);
    }
}