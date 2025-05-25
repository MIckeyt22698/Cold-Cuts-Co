package com.pluralsight;

import com.pluralsight.ui.HomeScreen;
import com.pluralsight.utilities.InputHelper;

public class Program {
    public static void main(String[] args) {
        HomeScreen homeScreen = new HomeScreen();
        homeScreen.display(); // Start the application by displaying the home screen
        // InputHelper.closeScanner(); // This is now handled within HomeScreen's exit path
    }
}