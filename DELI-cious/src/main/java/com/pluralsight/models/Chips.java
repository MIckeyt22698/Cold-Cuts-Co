package com.pluralsight.models;

class Chips {
    private String type;

    public Chips(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type + " chips";
    }
}
