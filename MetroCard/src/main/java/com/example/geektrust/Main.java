package com.example.geektrust;

import com.example.geektrust.service.CalculateTotalCollection;
import com.example.geektrust.service.PrintSummary;
import com.example.geektrust.service.StoreInput;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        final String BALANCE = "BALANCE";
        final String CHECK_IN = "CHECK_IN";
        final String PRINT_SUMMARY = "PRINT_SUMMARY";

        StoreInput storeInput = new StoreInput();
        CalculateTotalCollection calculateTotalCollection = new CalculateTotalCollection();
        PrintSummary printSummary = new PrintSummary();

        //Sample code to read from file passed as command line argument
        try {
            // the file to be opened for reading
            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis); // file to be scanned

            // returns true if there is another line to read
            while (sc.hasNextLine()) {
               //Add your code here to process input commands
                String command = sc.nextLine();
                String[] arguments = command.split(" ");

                if(arguments[0].equals(BALANCE))
                    storeInput.storeMetroCardDetails(arguments);
                else if(arguments[0].equals(CHECK_IN))
                    storeInput.storeCheckInDetails(arguments);
                else if (arguments[0].equals(PRINT_SUMMARY))
                    break;
            }
            sc.close(); // closes the scanner
        } catch (IOException e) {
            e.printStackTrace();
        }

        calculateTotalCollection.calculateTotalCollection();
        printSummary.printCentralSummary();
        printSummary.printAirportSummary();
    }
}