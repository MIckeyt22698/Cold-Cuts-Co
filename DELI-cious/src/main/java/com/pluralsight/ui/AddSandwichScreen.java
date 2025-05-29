package com.pluralsight.ui;

import com.pluralsight.data.Menu;
import com.pluralsight.models.*;
import com.pluralsight.models.PremadeSandwich.ItalianSandwich;
import com.pluralsight.models.PremadeSandwich.PhillyCheesesteakSandwich;
import com.pluralsight.models.PremadeSandwich.VeggieDelightSandwich;
import com.pluralsight.utilities.InputHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

    // A centralized list of all available toppings with their prices
    public class AddSandwichScreen {
        private Order order;

        public AddSandwichScreen(Order order) {
            this.order = order;
        }

        public void display() {
            System.out.println(ConsoleColors.CYAN + "\n--- Build Your Sandwich ---" + ConsoleColors.RESET);

            Sandwich sandwich = null;

            while (true) {
                System.out.println(ConsoleColors.CYAN + "\nDo you want to build a:" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.CYAN + "  1) Custom Sandwich" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.CYAN + "  2) Signature Sandwich" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.CYAN + "  0) Cancel Sandwich Creation" + ConsoleColors.RESET);
                System.out.print(ConsoleColors.YELLOW + "Enter your choice: " + ConsoleColors.RESET);

                String choice = InputHelper.getString("").trim();

                if (choice.equals("1")) {
                    sandwich = createCustomSandwich();
                    if (sandwich != null) {
                        break;
                    }
                } else if (choice.equals("2")) {
                    sandwich = selectSignatureSandwich();
                    if (sandwich != null) {
                        break;
                    }
                } else if (choice.equals("0")) {
                    System.out.println(ConsoleColors.YELLOW + "Sandwich creation cancelled." + ConsoleColors.RESET);
                    return;
                } else {
                    System.out.println(ConsoleColors.RED + "Invalid choice. Please enter 1, 2, or 0." + ConsoleColors.RESET);
                }
            }

            if (sandwich == null) {
                System.out.println(ConsoleColors.RED + "Sandwich creation failed or was cancelled." + ConsoleColors.RESET);
                return;
            }

            customizeSandwich(sandwich);

            String toastedInput = InputHelper.getString(ConsoleColors.CYAN + "Do you want your sandwich toasted? (yes/no): " + ConsoleColors.RESET).trim().toLowerCase();
            sandwich.setToasted(toastedInput.equals("yes"));

            order.addSandwich(sandwich);
            System.out.println("\n" + ConsoleColors.BRIGHT_GREEN + "Sandwich successfully added to your order!" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.BRIGHT_BLUE + "Current Order Summary:" + ConsoleColors.RESET);
            System.out.println(order.toString());
        }

        private Sandwich createCustomSandwich() {
            BreadType selectedBread = getBreadTypeFromUser();
            if (selectedBread == null) {
                return null;
            }

            Size selectedSize = getSandwichSizeFromUser();
            if (selectedSize == null) {
                return null;
            }
            return new Sandwich(selectedBread, selectedSize, false);
        }

        private SignatureSandwich selectSignatureSandwich() {
            List<SignatureSandwich> signatureTemplates = new ArrayList<>();
            signatureTemplates.add(new PhillyCheesesteakSandwich(Size.MEDIUM, false));
            signatureTemplates.add(new ItalianSandwich(Size.MEDIUM, false));
            signatureTemplates.add(new VeggieDelightSandwich(Size.MEDIUM, false));

            while (true) {
                System.out.println(ConsoleColors.BRIGHT_GREEN + "\n--- Select a Signature Sandwich ---" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.BRIGHT_GREEN + "Available Signature Sandwiches:" + ConsoleColors.RESET);
                for (int i = 0; i < signatureTemplates.size(); i++) {
                    System.out.println(ConsoleColors.BRIGHT_GREEN + String.format("  %d) %s (default: %s bread)",
                            i + 1, signatureTemplates.get(i).getSignatureName(), signatureTemplates.get(i).getBreadType().toString()) + ConsoleColors.RESET);
                }
                System.out.println(ConsoleColors.BRIGHT_GREEN + "  0) Go Back" + ConsoleColors.RESET);
                System.out.print(ConsoleColors.YELLOW + "Enter your choice: " + ConsoleColors.RESET);

                int choice = InputHelper.getInt("");

                if (choice == 0) {
                    return null;
                } else if (choice > 0 && choice <= signatureTemplates.size()) {
                    Size selectedSize = getSandwichSizeFromUser();
                    if (selectedSize == null) {
                        return null;
                    }

                    SignatureSandwich selectedSignature = null;
                    String chosenTemplateName = signatureTemplates.get(choice - 1).getSignatureName();

                    if (chosenTemplateName.equalsIgnoreCase("Philly Cheesesteak")) {
                        selectedSignature = new PhillyCheesesteakSandwich(selectedSize, false);
                    } else if (chosenTemplateName.equalsIgnoreCase("Italian")) {
                        selectedSignature = new ItalianSandwich(selectedSize, false);
                    } else if (chosenTemplateName.equalsIgnoreCase("Veggie Delight")) {
                        selectedSignature = new VeggieDelightSandwich(selectedSize, false);
                    }

                    if (selectedSignature != null) {
                        System.out.println(ConsoleColors.BRIGHT_GREEN + "\nYou've selected: " + selectedSignature.getName() + ConsoleColors.RESET);
                        System.out.println(ConsoleColors.BRIGHT_GREEN + "Default toppings included. You can customize next." + ConsoleColors.RESET);
                        return selectedSignature;
                    } else {
                        System.out.println(ConsoleColors.RED + "Error creating signature sandwich. Please try again." + ConsoleColors.RESET);
                    }
                } else {
                    System.out.println(ConsoleColors.RED + "Invalid choice. Please enter a valid number." + ConsoleColors.RESET);
                }
            }
        }

        private void customizeSandwich(Sandwich sandwich) {
            System.out.println(ConsoleColors.CYAN + "\n--- Customize Your Sandwich ---" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "Current Sandwich: " + sandwich.toString() + ConsoleColors.RESET);

            while (true) {
                System.out.println(ConsoleColors.CYAN + "\nCustomize Options:" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.CYAN + "  1) Add Topping" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.CYAN + "  2) Remove Topping" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.CYAN + "  0) Done Customizing" + ConsoleColors.RESET);
                System.out.print(ConsoleColors.YELLOW + "Enter your choice: " + ConsoleColors.RESET);

                String choice = InputHelper.getString("").trim();

                switch (choice) {
                    case "1":
                        addToppingToSandwich(sandwich);
                        System.out.println(ConsoleColors.CYAN + "\nUpdated Sandwich: " + sandwich.toString() + ConsoleColors.RESET);
                        break;
                    case "2":
                        removeToppingFromSandwich(sandwich);
                        System.out.println(ConsoleColors.CYAN + "\nUpdated Sandwich: " + sandwich.toString() + ConsoleColors.RESET);
                        break;
                    case "0":
                        System.out.println(ConsoleColors.BRIGHT_GREEN + "Finished customizing." + ConsoleColors.RESET);
                        return;
                    default:
                        System.out.println(ConsoleColors.RED + "Invalid choice. Please enter 1, 2, or 0." + ConsoleColors.RESET);
                }
            }
        }

        private void addToppingToSandwich(Sandwich sandwich) {
            System.out.println(ConsoleColors.CYAN + "\n--- Add Topping ---" + ConsoleColors.RESET);
            // Updated initial instruction
            System.out.println(ConsoleColors.CYAN + "Enter ingredient name, press ENTER for next category, or type 'done' to finish." + ConsoleColors.RESET);

            List<Topping> allToppings = Menu.getAllAvailableToppings();

            final String meatRegex = "^(steak|ham|turkey|roast beef|salami|chicken|bacon)$";
            final String sauceRegex = "^(mayonnaise|mustard|ketchup|ranch|thousand island|vinaigrette|pesto|marinara|au jus)$";

            List<Topping> meats = allToppings.stream()
                    .filter(t -> t.getName().toLowerCase().matches(meatRegex))
                    .collect(Collectors.toList());
            List<Topping> cheeses = allToppings.stream()
                    .filter(t -> t.getName().toLowerCase().contains("cheese"))
                    .collect(Collectors.toList());
            List<Topping> sauces = allToppings.stream()
                    .filter(t -> t.getName().toLowerCase().matches(sauceRegex))
                    .collect(Collectors.toList());
            List<Topping> regularToppings = allToppings.stream()
                    .filter(t -> !t.getName().toLowerCase().matches(meatRegex) &&
                            !t.getName().toLowerCase().contains("cheese") &&
                            !t.getName().toLowerCase().matches(sauceRegex))
                    .collect(Collectors.toList());

            String[] categoryNames = {"MEATS", "CHEESES", "VEGGIES & OTHER TOPPINGS", "SAUCES"};

            for (String categoryName : categoryNames) {
                final List<Topping> displayToppings;
                final String displayColor;

                if (categoryName.equals("MEATS")) {
                    displayToppings = meats;
                    displayColor = ConsoleColors.BRIGHT_RED;
                } else if (categoryName.equals("CHEESES")) {
                    displayToppings = cheeses;
                    displayColor = ConsoleColors.BRIGHT_YELLOW;
                } else if (categoryName.equals("VEGGIES & OTHER TOPPINGS")) {
                    displayToppings = regularToppings;
                    displayColor = ConsoleColors.BRIGHT_GREEN;
                } else if (categoryName.equals("SAUCES")) {
                    displayToppings = sauces;
                    displayColor = ConsoleColors.BRIGHT_CYAN;
                } else {
                    displayToppings = new ArrayList<>();
                    displayColor = ConsoleColors.RESET;
                }

                System.out.println(displayColor + "\n--- " + categoryName + " ---" + ConsoleColors.RESET);
                if (displayToppings.isEmpty()) {
                    System.out.println(ConsoleColors.YELLOW + "  (No items in this category)" + ConsoleColors.RESET);
                } else {
                    displayToppings.forEach(topping ->
                            System.out.println(displayColor + "  - " + topping.getName() + (topping.getPrice() > 0 ? " ($" + String.format("%.2f", topping.getPrice()) + ")" : "") + ConsoleColors.RESET));
                }
                System.out.println(); // Spacing

                // Call the updated helper method
                if (handleToppingCategoryInput(sandwich, categoryName.toLowerCase())) {
                    return;
                }
            }
            System.out.println(ConsoleColors.BRIGHT_GREEN + "All topping categories reviewed. Finished adding toppings." + ConsoleColors.RESET);
        }

        // --- UPDATED HELPER METHOD: handleToppingCategoryInput ---
        private boolean handleToppingCategoryInput(Sandwich sandwich, String categoryDisplay) {
            while (true) {
                // Updated prompt
                String prompt = String.format(ConsoleColors.YELLOW + "Add %s (or press ENTER for next category, 'done' to finish all): " + ConsoleColors.RESET, categoryDisplay);
                String input = InputHelper.getString(prompt).trim();

                if (input.equalsIgnoreCase("done")) {
                    System.out.println(ConsoleColors.BRIGHT_GREEN + "Finished adding toppings." + ConsoleColors.RESET);
                    return true; // User wants to stop all topping addition
                } else if (input.isEmpty()) { // Check for empty input (just pressing Enter)
                    return false; // User wants to move to the next category
                }

                Topping selectedTopping = Menu.getToppingByName(input).orElse(null);

                if (selectedTopping != null) {
                    sandwich.addTopping(selectedTopping);
                    System.out.println(ConsoleColors.BRIGHT_GREEN + selectedTopping.getName() + " added." + ConsoleColors.RESET);
                } else {
                    System.out.println(ConsoleColors.RED + "Ingredient not found. Please try again. Or press ENTER or type 'done'." + ConsoleColors.RESET);
                }
            }
        }

        private void removeToppingFromSandwich(Sandwich sandwich) {
            if (sandwich.getToppings().isEmpty()) {
                System.out.println(ConsoleColors.YELLOW + "This sandwich has no toppings to remove." + ConsoleColors.RESET);
                return;
            }

            System.out.println(ConsoleColors.BRIGHT_RED + "\n--- Remove Topping ---" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.BRIGHT_RED + "Current Toppings:" + ConsoleColors.RESET);
            for (int i = 0; i < sandwich.getToppings().size(); i++) {
                Topping t = sandwich.getToppings().get(i);
                System.out.println(ConsoleColors.BRIGHT_RED + String.format("  %d) %s", (i + 1), t.getName()) + ConsoleColors.RESET);
            }

            System.out.print(ConsoleColors.BRIGHT_RED + "Enter the number of the topping to remove (or '0' to cancel): " + ConsoleColors.RESET);
            int choice = InputHelper.getInt("");

            if (choice > 0 && choice <= sandwich.getToppings().size()) {
                Topping removedTopping = sandwich.getToppings().remove(choice - 1);
                System.out.println(ConsoleColors.BRIGHT_GREEN + removedTopping.getName() + " removed." + ConsoleColors.RESET);
            } else if (choice == 0) {
                System.out.println(ConsoleColors.YELLOW + "Topping removal cancelled." + ConsoleColors.RESET);
            } else {
                System.out.println(ConsoleColors.RED + "Invalid number. Please try again." + ConsoleColors.RESET);
            }
        }

        private BreadType getBreadTypeFromUser() {
            while (true) {
                System.out.println(ConsoleColors.CYAN + "\n--- Select Bread Type ---" + ConsoleColors.RESET);
                for (BreadType type : BreadType.values()) {
                    System.out.println(ConsoleColors.CYAN + "- " + type.toString() + ConsoleColors.RESET);
                }
                String input = InputHelper.getString(ConsoleColors.CYAN + "Enter bread type (or 'cancel'): " + ConsoleColors.RESET).trim().toUpperCase();
                if (input.equalsIgnoreCase("cancel")) return null;
                try {
                    return BreadType.valueOf(input);
                } catch (IllegalArgumentException e) {
                    System.out.println(ConsoleColors.RED + "Invalid bread type. Please choose from the list or type 'cancel'." + ConsoleColors.RESET);
                }
            }
        }

        private Size getSandwichSizeFromUser() {
            while (true) {
                System.out.println(ConsoleColors.CYAN + "\n--- Select Sandwich Size ---" + ConsoleColors.RESET);
                for (Size size : Size.values()) {
                    System.out.println(ConsoleColors.CYAN + "- " + size.toString() + ConsoleColors.RESET);
                }
                String input = InputHelper.getString(ConsoleColors.CYAN + "Enter size (SMALL, MEDIUM, LARGE, or 'cancel'): " + ConsoleColors.RESET).trim().toUpperCase();
                if (input.equalsIgnoreCase("cancel")) return null;
                try {
                    return Size.valueOf(input);
                } catch (IllegalArgumentException e) {
                    System.out.println(ConsoleColors.RED + "Invalid size. Please choose SMALL, MEDIUM, or LARGE or type 'cancel'." + ConsoleColors.RESET);
                }
            }
        }
    }