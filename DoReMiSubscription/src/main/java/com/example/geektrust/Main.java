package com.example.geektrust;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {

        //code to read from file passed as command line argument
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(args[0]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //creates object og Logic class to implement logic
        Logic l = new Logic();

        //reads input from a file
        l.storeInput(fis);

        //prints output to the console
        l.printRenewalDetails();
    }
}
