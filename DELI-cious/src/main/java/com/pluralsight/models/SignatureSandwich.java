package com.pluralsight.models;

public abstract class SignatureSandwich extends Sandwich {
    protected String signatureName; // To hold the specific name like "Philly Cheesesteak"

    /**
     * Constructor for a SignatureSandwich.
     * @param breadType The default bread type for this signature sandwich.
     * @param size The size of the sandwich.
     * @param toasted Whether the sandwich should be toasted by default.
     * @param name The unique name of this signature sandwich (e.g., "Philly Cheesesteak").
     */
    public SignatureSandwich(BreadType breadType, Size size, boolean toasted, String name) {
        super(breadType, size, toasted);
        this.signatureName = name;
        // The buildToppings() method will be called by the concrete subclass's constructor
    }

    /**
     * Abstract method to be implemented by concrete signature sandwich classes.
     * This method will add the default toppings for that specific signature sandwich.
     */
    protected abstract void buildToppings();

    /**
     * Overrides the getName() method from OrderableItem to return the signature name.
     * @return The specific name of the signature sandwich.
     */
    public String getSignatureName() { // <--- ADD THIS NEW METHOD
        return signatureName;
    }

    @Override
    public String getName() {
        // ... (existing getName method) ...
        return String.format("%s %s", this.getSize().toString(), this.signatureName);
    }
}
