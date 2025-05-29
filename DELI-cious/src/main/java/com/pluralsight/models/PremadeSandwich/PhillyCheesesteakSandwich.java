package com.pluralsight.models.PremadeSandwich;

import com.pluralsight.data.Menu;
import com.pluralsight.models.BreadType;
import com.pluralsight.models.SignatureSandwich;
import com.pluralsight.models.Size;

public class PhillyCheesesteakSandwich extends SignatureSandwich {

    public PhillyCheesesteakSandwich(Size size, boolean toasted) {

        super(BreadType.WHITE, size, toasted, "Philly Cheesesteak");
        buildToppings();
    }

    @Override
    protected void buildToppings() {

        // Use Menu.getToppingByName() to get the actual Topping objects from the centralized list
        Menu.getToppingByName("Steak").ifPresent(this::addTopping);

        Menu.getToppingByName("Provolone Cheese").ifPresent(this::addTopping);

        Menu.getToppingByName("Peppers").ifPresent(this::addTopping);
        Menu.getToppingByName("Onions").ifPresent(this::addTopping);

        Menu.getToppingByName("Mayonnaise").ifPresent(this::addTopping);
    }
}