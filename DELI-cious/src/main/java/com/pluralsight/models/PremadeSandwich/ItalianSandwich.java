package com.pluralsight.models.PremadeSandwich;

import com.pluralsight.models.Menu;
import com.pluralsight.models.BreadType;
import com.pluralsight.models.Size;

public class ItalianSandwich extends SignatureSandwich {

    public ItalianSandwich(Size size, boolean toasted) {
        // Italian is often on White or Wheat, let's pick White as default
        super(BreadType.WHITE, size, toasted, "Italian");
        buildToppings(); // Add the default toppings
    }

    @Override
    protected void buildToppings() {
        // Add default toppings for an Italian Sandwich
        Menu.getToppingByName("Salami").ifPresent(this::addTopping);
        Menu.getToppingByName("Ham").ifPresent(this::addTopping);
        Menu.getToppingByName("Provolone Cheese").ifPresent(this::addTopping);
        Menu.getToppingByName("Lettuce").ifPresent(this::addTopping);
        Menu.getToppingByName("Tomatoes").ifPresent(this::addTopping);
        Menu.getToppingByName("Onions").ifPresent(this::addTopping);
        Menu.getToppingByName("Vinaigrette").ifPresent(this::addTopping);
        Menu.getToppingByName("Olives").ifPresent(this::addTopping);
        Menu.getToppingByName("Peppers").ifPresent(this::addTopping);
    }
}