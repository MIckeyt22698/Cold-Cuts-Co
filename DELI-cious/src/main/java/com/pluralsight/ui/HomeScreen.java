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
            // Display a welcome banner or ASCII art only once at the very start
            printWelcomeBanner();

            int menuWidth = 89; // Set menu width to match the ASCII art banner's width

            while (true) { // Loop to keep the home screen active until exit
                // Display main menu with improved formatting and colors
                System.out.println("\n" + ConsoleColors.BRIGHT_PURPLE + "=".repeat(menuWidth) + ConsoleColors.RESET); // Top border
                System.out.println(ConsoleColors.BRIGHT_PURPLE + centerString(menuWidth, "MAIN MENU") + ConsoleColors.RESET);
                System.out.println(ConsoleColors.BRIGHT_PURPLE + "=".repeat(menuWidth) + ConsoleColors.RESET); // Bottom border
                System.out.println(); // Spacing

                // Menu options with simple alignment and color
                System.out.println(ConsoleColors.BRIGHT_BLUE + String.format("  %-4s %s", "1)", "Start New Order") + ConsoleColors.RESET);
                System.out.println(ConsoleColors.BRIGHT_BLUE + String.format("  %-4s %s", "0)", "Exit Application") + ConsoleColors.RESET);
                System.out.println(); // Spacing

                System.out.println(ConsoleColors.BRIGHT_PURPLE + "-".repeat(menuWidth) + ConsoleColors.RESET); // Separator
                System.out.print(ConsoleColors.YELLOW + "Enter your choice: " + ConsoleColors.RESET); // Prompt in Yellow

                String input = InputHelper.getString(""); // Get user input using InputHelper

                switch (input) {
                    case "1":
                        System.out.println(ConsoleColors.GREEN + "\nInitiating a new order..." + ConsoleColors.RESET);
                        // Create a new Order through the OrderService
                        Order newOrder = orderService.createNewOrder();
                        // Navigate to the OrderScreen, passing the new order and a reference to HomeScreen
                        OrderScreen orderScreen = new OrderScreen(newOrder, this);
                        orderScreen.display(); // Call the OrderScreen's display method
                        break;
                    case "0":
                        System.out.println(ConsoleColors.BRIGHT_WHITE + "\nThank you for choosing the Deli! Goodbye!" + ConsoleColors.RESET);
                        InputHelper.closeScanner(); // Close the global scanner to release resources
                        return; // Exit the display loop, ending the application
                    default:
                        System.out.println(ConsoleColors.RED + "\nInvalid choice. Please enter '1' to start a new order or '0' to exit." + ConsoleColors.RESET);
                }
            }
        }

        /**
         * Prints a stylized ASCII art welcome banner.
         * This is called once when the application starts.
         */
        private void printWelcomeBanner() {
            int bannerWidth = 89; // The exact width of your ASCII art

            System.out.println(ConsoleColors.CYAN); // Set color for the banner

            // Top border matching ASCII art width
            System.out.println("█".repeat(bannerWidth + 2)); // Add a bit of padding for the border

            System.out.println("_________        .__       .___        _________         __                   _________ _______       ");
            System.out.println("\\_   ___ \\  ____ |  |    __| _/        \\_   ___ \\ __ ___/  |_  ______         \\_   ___ \\\\   _  \\      ");
            System.out.println("/    \\  \\/ /  _ \\|  |   / __ |  ______ /    \\  \\/|  |  \\   __\\/  ___/  ______ /    \\  \\//  /_\\  \\     ");
            System.out.println("\\     \\___(  <_> )  |__/ /_/ | /_____/ \\     \\___|  |  /|  |  \\___ \\  /_____/ \\     \\___\\  \\_/   \\    ");
            System.out.println(" \\______  /\\____/|____/\\____ |          \\______  /____/ |__| /____  >          \\______  /\\_____  / /\\ ");
            System.out.println("        \\/                  \\/                 \\/                 \\/                  \\/       \\/  \\/ ");

            // Bottom border matching ASCII art width
            System.out.println("█".repeat(bannerWidth + 2)); // Add a bit of padding for the border
            // Tagline centered to the ASCII art width
            System.out.println(centerString(bannerWidth + 2, "Fresh Ingredients, Handcrafted Just For You!"));
            System.out.println("-".repeat(bannerWidth + 2)); // Separator line for tagline

            System.out.println(ConsoleColors.RESET); // Reset color after the banner
            System.out.println("\n"); // Extra spacing after the banner
        }

        /**
         * Helper method to center a string within a given width.
         * @param width The total width of the line.
         * @param s The string to be centered.
         * @return The centered string.
         */
        private String centerString(int width, String s) {
            if (s == null || s.length() >= width) {
                return s; // If string is null or too long, return as is
            }
            int padding = width - s.length();
            int leftPadding = padding / 2;
            int rightPadding = padding - leftPadding; // Ensure total padding is correct
            return " ".repeat(leftPadding) + s + " ".repeat(rightPadding);
        }
    }

