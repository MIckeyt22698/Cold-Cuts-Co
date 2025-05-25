package com.pluralsight.models;

public class Drink implements OrderableItems {
    private Size size;
    private String flavor;
    private double price;

    public Drink(Size size, String flavor) {
        this.size = size;
        this.flavor = flavor;
        this.price = calculatePrice(size);
    }
    /**
     * Constructor for a Drink. The price is calculated based on the size.
     *
     * @param size The size of the drink (SMALL, MEDIUM, LARGE).
     * @param flavor The flavor of the drink (e.g., "Cola", "Sprite").
     */

    // Getters
    public Size getSize() {
        return size;
    }

    public String getFlavor() {
        return flavor;
    }

    @Override // From OrderableItem
    public double getPrice() {
        return price;
    }

    @Override // From OrderableItem
    public String getName() {
        return size.toString().toLowerCase() + " " + flavor + " Drink";
    }

    private double calculatePrice(Size size) {
        switch (size) {
            case SMALL:
                return 2.00;
            case MEDIUM:
                return 2.50;
            case LARGE:
                return 3.00;
            default:
                return 0.00; // Should not happen with enum
        }
    }

    @Override
    public String toString() {
        return String.format("%s - %s ($%.2f)", size.toString(), flavor, price);
    }
}
