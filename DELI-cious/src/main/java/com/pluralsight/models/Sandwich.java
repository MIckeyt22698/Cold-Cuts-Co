package com.pluralsight.models;

import java.util.ArrayList;
import java.util.List;

    public class Sandwich {
        private String breadType;
        private String size;
        private List<String> meats;
        private List<String> cheeses;
        private List<String> toppings;
        private List<String> sauces;
        private boolean toasted;

        public Sandwich(String breadType, String size, boolean toasted) {
            this.breadType = breadType;
            this.size = size;
            this.toasted = toasted;
            this.meats = new ArrayList<>();
            this.cheeses = new ArrayList<>();
            this.toppings = new ArrayList<>();
            this.sauces = new ArrayList<>();
        }

        public String getBreadType() {
            return breadType;
        }

        public String getSize() {
            return size;
        }

        public List<String> getMeats() {
            return meats;
        }

        public List<String> getCheeses() {
            return cheeses;
        }

        public List<String> getToppings() {
            return toppings;
        }

        public List<String> getSauces() {
            return sauces;
        }

        public boolean isToasted() {
            return toasted;
        }

        public void addMeat(String meat) {
            meats.add(meat);
        }

        public void addCheese(String cheese) {
            cheeses.add(cheese);
        }

        public void addTopping(String topping) {
            toppings.add(topping);
        }

        public void addSauce(String sauce) {
            sauces.add(sauce);
        }

        @Override
        public String toString() {
            return size + " sandwich on " + breadType + (toasted ? " (toasted)" : "") +
                    ", Meats: " + meats + ", Cheeses: " + cheeses +
                    ", Toppings: " + toppings + ", Sauces: " + sauces;
        }
    }
