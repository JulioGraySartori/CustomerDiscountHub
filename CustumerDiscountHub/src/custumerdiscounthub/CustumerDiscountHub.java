/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package custumerdiscounthub;

/**
 *
 * @author grays
 */
public class CustumerDiscountHub {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Path to the input file
        String filePath = "customers.txt";
        String outputFilePath = "customerdiscount.txt";

        try {
            File inputFile = new File(filePath);
            Scanner reader = new Scanner(inputFile);
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));
            
            // Reading customers from the file
            while (reader.hasNextLine()) {
                // Read customer information
                String fullName = reader.nextLine();
                double totalPurchase = Double.parseDouble(reader.nextLine());
                int classType = Integer.parseInt(reader.nextLine());
                int lastPurchaseYear = Integer.parseInt(reader.nextLine());

                // Split the full name
                String[] nameParts = fullName.split(" ");
                String firstName = nameParts[0];
                String secondName = nameParts[1];
                
                // Validate the data
                if (validateData(firstName, secondName, totalPurchase, classType, lastPurchaseYear)) {
                    // Calculate the discount
                    double finalValue = calculateDiscount(totalPurchase, classType, lastPurchaseYear);

                    // Write to output file
                    writer.write(firstName + " - " + secondName + "\n" + finalValue + "\n");
                } else {
                    System.out.println("Invalid data for customer: " + fullName);
                }
            }

            writer.close();
            reader.close();

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    // Data validation method
    public static boolean validateData(String firstName, String secondName, double totalPurchase, int classType, int lastPurchaseYear) {
        if (!firstName.matches("[a-zA-Z]+")) {
            System.out.println("Invalid first name: " + firstName);
            return false;
        }
        if (!secondName.matches("[a-zA-Z0-9]+")) {
            System.out.println("Invalid second name: " + secondName);
            return false;
        }
        if (classType < 1 || classType > 3) {
            System.out.println("Invalid class type: " + classType);
            return false;
        }
        if (lastPurchaseYear < 1900 || lastPurchaseYear > 2024) {
            System.out.println("Invalid purchase year: " + lastPurchaseYear);
            return false;
        }
        return true;
    }

    // Discount calculation method
    public static double calculateDiscount(double purchaseValue, int classType, int lastPurchaseYear) {
        double discount = 0;
        // Add conditions as per your rules for classes and years
        if (classType == 1 && lastPurchaseYear == 2024) {
            discount = 0.30;
        } else if (classType == 1 && lastPurchaseYear < 2024) {
            discount = 0.20;
        } else if (classType == 1 && (2024 - lastPurchaseYear) > 5) {
            discount = 0.10;
        }
        // Additional conditions for classType 2 and 3
        return purchaseValue - (purchaseValue * discount);
    }
}
