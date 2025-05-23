package com.pluralsight.models;

import java.util.ArrayList;
import java.util.List;

class Order {
    private List<Sandwich> sandwiches;
    private List<Drink> drinks;
    private List<Chips> chips;

    public Order() {
        this.sandwiches = new ArrayList<>();
        this.drinks = new ArrayList<>();
        this.chips = new ArrayList<>();
    }

    public void addSandwich(Sandwich sandwich) {
        sandwiches.add(0, sandwich);
    }

    public void addDrink(Drink drink) {
        drinks.add(0, drink);
    }

    public void addChips(Chips chip) {
        chips.add(0, chip);
    }

    public List<Sandwich> getSandwiches() {
        return sandwiches;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public List<Chips> getChips() {
        return chips;
    }

    public double calculateTotal() {
        double total = 0;
        for (Sandwich s : sandwiches) {
            total += 5.0; // base price
            total += s.getMeats().size() * 1.5;
            total += s.getCheeses().size() * 1.0;
            total += s.getToppings().size() * 0.5;
        }
        total += drinks.size() * 1.75;
        total += chips.size() * 1.25;
        return total;
    }

    @Override
    public String toString() {
        return "Order:\n" +
                "Sandwiches: " + sandwiches + "\n" +
                "Drinks: " + drinks + "\n" +
                "Chips: " + chips + "\n" +
                "Total: $" + calculateTotal();
    }
}
