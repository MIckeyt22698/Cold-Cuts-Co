package com.pluralsight.models;

public interface OrderableItems {
    String getName();
    /**
     * Retrieves the display name of the orderable item.
     *
     * @return A String representing the name of the item (e.g., "Small Cola Drink", "BBQ Chips").
     */
    double getPrice();
    /**
     * Retrieves the price of the orderable item.
     *
     * @return A double representing the monetary price of the item.
     */
}
