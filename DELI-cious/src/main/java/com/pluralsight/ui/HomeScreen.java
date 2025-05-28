package com.pluralsight.ui;

import com.pluralsight.models.ConsoleColors;
import com.pluralsight.models.Order;
import com.pluralsight.services.OrderService;
import com.pluralsight.utilities.InputHelper;



public class HomeScreen {
    private OrderService orderService; // Dependency on OrderService to create new orders

    /**
     * Constructor for the HomeScreen. Initializes the OrderService.
     */
    public HomeScreen() {
        this.orderService = new OrderService();
    }

    /**
     * Displays the home screen menu and handles user input for navigation.
     * This method contains the main application loop.
     */
    public void display() {
        // Optional: Display a welcome banner or ASCII art only once at the very start
        printWelcomeBanner();

        while (true) { // Loop to keep the home screen active until exit
            // Display main menu with improved formatting
            System.out.println("\n" + "=".repeat(40)); // Top border
            System.out.println(centerString(40, "MAIN MENU"));
            System.out.println("=".repeat(40)); // Bottom border
            System.out.println(); // Spacing

            // Menu options with simple alignment
            System.out.println(String.format("  %-4s %s", "1)", "Start New Order"));
            System.out.println(String.format("  %-4s %s", "0)", "Exit Application"));
            System.out.println(); // Spacing

            System.out.println("----------------------------------------"); // Separator
            System.out.print("Enter your choice: ");

            String input = InputHelper.getString(""); // Get user input using InputHelper

            switch (input) {
                case "1":
                    System.out.println("\nInitiating a new order...");
                    // Create a new Order through the OrderService
                    Order newOrder = orderService.createNewOrder();
                    // Navigate to the OrderScreen, passing the new order and a reference to HomeScreen
                    OrderScreen orderScreen = new OrderScreen(newOrder, this);
                    orderScreen.display(); // Call the OrderScreen's display method
                    break;
                case "0":
                    System.out.println("\nThank you for choosing the Deli! Goodbye!");
                    InputHelper.closeScanner(); // Close the global scanner to release resources
                    return; // Exit the display loop, ending the application
                default:
                    System.out.println("\nInvalid choice. Please enter '1' to start a new order or '0' to exit.");
            }
        }
    }

    /**
     * Prints a simple ASCII art welcome banner for the Deli.
     * This is called once when the application starts.
     */
    private void printWelcomeBanner() {
        // Consider uncommenting the ConsoleColors import and usage below
        // if you add the ConsoleColors class to your utilities package.

         System.out.println(ConsoleColors.CYAN); // Set color for the banner

        System.out.println("█████████████████████████████████████████████████████████████████████████████████████████████████████████████");

        System.out.println("_________        .__       .___        _________         __                   _________ _______       ");
        System.out.println("\\_   ___ \\  ____ |  |    __| _/        \\_   ___ \\ __ ___/  |_  ______         \\_   ___ \\\\   _  \\      ");
        System.out.println("/    \\  \\/ /  _ \\|  |   / __ |  ______ /    \\  \\/|  |  \\   __\\/  ___/  ______ /    \\  \\//  /_\\  \\     ");
        System.out.println("\\     \\___(  <_> )  |__/ /_/ | /_____/ \\     \\___|  |  /|  |  \\___ \\  /_____/ \\     \\___\\  \\_/   \\    ");
        System.out.println(" \\______  /\\____/|____/\\____ |          \\______  /____/ |__| /____  >          \\______  /\\_____  / /\\ ");
        System.out.println("        \\/                  \\/                 \\/                 \\/                  \\/       \\/  \\/ ");

        System.out.println("█████████████████████████████████████████████████████████████████████████████████████████████████████████████");
        System.out.println(centerString(75, "  Fresh Ingredients, Handcrafted Just For You!  "));
        System.out.println("-----------------------------------------------------------------------------");

        System.out.println(ConsoleColors.RESET); // Reset color
        System.out.println("\n"); // Extra spacing after the banner
    }

    /**
     * Helper method to center a string within a given width.
     * @param width The total width of the line.
     * @param s The string to be centered.
     * @return The centered string.
     */
    private String centerString(int width, String s) {
        return String.format("%-" + width + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }
}
