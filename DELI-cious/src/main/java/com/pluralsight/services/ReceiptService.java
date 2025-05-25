package com.pluralsight.services;

import com.pluralsight.models.Order;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptService {
    public void saveReceipt(Order order) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
        String filename = "receipts/receipt_" + timestamp + ".txt"; // Save to a 'receipts' folder

        // Ensure the 'receipts' directory exists
        java.io.File directory = new java.io.File("receipts");
        if (!directory.exists()) {
            directory.mkdir();
        }

        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("----- ORDER RECEIPT -----\n");
            writer.write("Date: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\n");
            writer.write("-------------------------\n");
            writer.write(order.toString()); // Uses the Order's toString() method
            writer.write("-------------------------\n");
            writer.write("Thank you for your order!\n");
            System.out.println("Receipt saved successfully as: " + filename);
        } catch (IOException e) {
            System.err.println("Error saving receipt: " + e.getMessage());
        }
    }
}
