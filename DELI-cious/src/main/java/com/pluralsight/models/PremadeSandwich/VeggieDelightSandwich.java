package com.pluralsight.models.PremadeSandwich;

import com.pluralsight.data.Menu;
import com.pluralsight.models.BreadType;
import com.pluralsight.models.SignatureSandwich;
import com.pluralsight.models.Size;

public class VeggieDelightSandwich extends SignatureSandwich {

    public VeggieDelightSandwich(Size size, boolean toasted) {
        // Veggie is good on Wheat or White, let's go with Wheat
        super(BreadType.WHEAT, size, toasted, "Veggie Delight");
        buildToppings();
    }

    @Override
    protected void buildToppings() {
        Menu.getToppingByName("Lettuce").ifPresent(this::addTopping);
        Menu.getToppingByName("Tomatoes").ifPresent(this::addTopping);
        Menu.getToppingByName("Onions").ifPresent(this::addTopping);
        Menu.getToppingByName("Pickles").ifPresent(this::addTopping);
        Menu.getToppingByName("Olives").ifPresent(this::addTopping);
        Menu.getToppingByName("Peppers").ifPresent(this::addTopping);
        Menu.getToppingByName("Cucumbers").ifPresent(this::addTopping);
        Menu.getToppingByName("Spinach").ifPresent(this::addTopping);
        Menu.getToppingByName("Swiss Cheese").ifPresent(this::addTopping); // Optional cheese
        Menu.getToppingByName("Vinaigrette").ifPresent(this::addTopping);
    }
}