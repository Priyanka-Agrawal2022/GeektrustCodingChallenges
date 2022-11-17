/*
package com.example.geektrust;

import junit.framework.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;


import java.io.*;
import java.nio.file.Path;

public class MainTest {
    */
/*The folder & the files created in it will be deleted after
    tests are run, even in the event of failures or exceptions.*//*



    @TempDir
    static Path folder;
    //public Tempor folder = new TemporaryFolder();

    static Path file1, file2;

    private static final PrintStream standardOut = System.out;
    private static final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    //executed before every test: create temporary files
    @BeforeAll
    public static void setUp() {
        try {
            file1 = folder.resolve( "testfile1.txt" );
            file2 = folder.resolve( "testfile2.txt" );

            //reassign the standard output stream to a new PrintStream with a ByteArrayOutputStream
            System.setOut(new PrintStream(outputStreamCaptor));
        }
        catch( Exception e ) {
            e.printStackTrace();
        }
    }


    //Write to the two temporary files and run some asserts.
    @Test
    public void test1() {
        //write out data to the test file
        try {
            FileInputStream fis1 = new FileInputStream(file1.toFile());
            FileWriter fw1 = new FileWriter(file2.toFile());
            BufferedWriter bw1 = new BufferedWriter(fw1);
            bw1.write( "PRINT_RENEWAL_DETAILS");
            bw1.close();

            Logic l = new Logic();
            //l.storeInput(fis1);
            l.printRenewalDetails();

            Assert.assertEquals("SUBSCRIPTIONS_NOT_FOUND", outputStreamCaptor.toString()
                    .trim());
        }
        catch( IOException e ) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        //write out data to the test file
        try {
            FileInputStream fis2 = new FileInputStream(file2.toFile());
            FileWriter fw2 = new FileWriter(file2.toFile());
            BufferedWriter bw2 = new BufferedWriter(fw2);
            bw2.write( "START_SUBSCRIPTION 07-01-2022\n" +
                    "ADD_TOPUP TEN_DEVICE 2\n" +
                    "PRINT_RENEWAL_DETAILS" );
            bw2.close();

            Logic l = new Logic();
            //l.storeInput(fis2);
            l.printRenewalDetails();

            Assert.assertEquals("ADD_TOPUP_FAILED SUBSCRIPTIONS_NOT_FOUND\nSUBSCRIPTIONS_NOT_FOUND", outputStreamCaptor.toString().trim());

        }
        catch( IOException e ) {
            e.printStackTrace();
        }
    }

    //restoring the standard output stream to its original state when our test terminates
    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}*/
