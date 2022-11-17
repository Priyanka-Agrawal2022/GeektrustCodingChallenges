package com.example.geektrust;

import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Logic {
    private ArrayList<String> commands;
    private ArrayList<String> arguments;
    private ArrayList<Calendar> renewalReminderDates;
    private int renewalAmount;

    public Logic() {
        commands = new ArrayList<>();
        arguments = new ArrayList<>();
        renewalReminderDates = new ArrayList<>();
        renewalAmount = 0;
    }

    public void storeInput(FileInputStream fis) {
        // the file to be opened for reading
        Scanner sc = new Scanner(fis); // file to be scanned

        // returns true if there is another line to read
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("PRINT_RENEWAL_DETAILS"))
                break;
            String[] arr = line.split(" ");
            //stores all input commands
            commands.add(arr[0]);
            //stores all arguments
            for (int i = 1; i < arr.length; i++) {
                arguments.add(arr[i]);
            }
        }

        //closes the scanner
        sc.close();
    }

    public void printRenewalDetails() {
        try {
            //Error Scenario #1: SUBSCRIPTIONS_NOT_FOUND
            if (commands.size() == 0) {
                System.out.println("SUBSCRIPTIONS_NOT_FOUND");
                return;
            } else if (commands.get(1).equals("ADD_TOPUP")) {
                System.out.println("ADD_TOPUP_FAILED SUBSCRIPTIONS_NOT_FOUND\nSUBSCRIPTIONS_NOT_FOUND");
                return;
            }

            String startSubscriptionDate = arguments.get(0);

            //Error Scenario #2: INVALID_DATE
            int day = Integer.parseInt(startSubscriptionDate.substring(0, 2));
            int month = Integer.parseInt(startSubscriptionDate.substring(3, 5));

            if (month > 12 || day > 31 || (month == 2 && day > 28)) {
                System.out.println("INVALID_DATE");
                for(int i=1; i<commands.size(); i++) {
                    if(commands.get(i).equals("ADD_SUBSCRIPTION"))
                        System.out.println("ADD_SUBSCRIPTION_FAILED INVALID_DATE");
                    if(commands.get(i).equals("ADD_TOPUP"))
                        System.out.println("ADD_TOPUP_FAILED INVALID_DATE");
                }
                System.out.println("SUBSCRIPTIONS_NOT_FOUND");
                return;
            }

            //converts String to Date
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            Date START_SUBSCRIPTION = formatter.parse(startSubscriptionDate);

            //Error Scenario #3: DUPLICATE_CATEGORY
            for(int j=3; j<arguments.size(); j+=2) {
                if (j == 3) {
                    if (arguments.get(j).equals(arguments.get(1))) {
                        System.out.println("ADD_SUBSCRIPTION_FAILED DUPLICATE_CATEGORY");
                        commands.set(2, "null");
                    }
                }
                else if (j == 5) {
                    if (arguments.get(j).equals(arguments.get(1)) || arguments.get(j).equals(arguments.get(3))) {
                        System.out.println("ADD_SUBSCRIPTION_FAILED DUPLICATE_CATEGORY");
                        commands.set(3, "null");
                    }
                }
            }

            //Error Scenario #4: DUPLICATE_TOPUP
            int len = commands.size();
            if (commands.get(len-1).equals("ADD_TOPUP") && commands.get(len-2).equals("ADD_TOPUP")) {
                System.out.println("ADD_TOPUP_FAILED DUPLICATE_TOPUP");
                commands.remove(len-1);
            }

            //i iterates over commands, j over arguments & k over renewalReminderDates
            int i = 1, j = 1, k = 0;

            //iterator tracks if next element exists in commands
            //points the iterator to index 1
            Iterator<String> it = commands.iterator();
            it.next();

            while (it.hasNext() && commands.get(i).equals("ADD_SUBSCRIPTION")) {
                //converts Date to Calendar
                //creates a new Calendar instance & initializes with START_SUBSCRIPTION everytime we enter loop
                Calendar c = Calendar.getInstance();
                c.setTime(START_SUBSCRIPTION);

                //adds a new renewal date for every subscription bought
                renewalReminderDates.add(c);

                if (arguments.get(j).equals("MUSIC")) {
                    if (arguments.get(j + 1).equals("FREE")) {
                        renewalAmount += 0;
                        //add 1 month & subtract 10 days
                        (renewalReminderDates.get(k)).add(Calendar.MONTH, 1);
                        (renewalReminderDates.get(k)).add(Calendar.DATE, -10);
                    } else if (arguments.get(j + 1).equals("PERSONAL")) {
                        renewalAmount += 100;
                        //add 1 month & subtract 10 days
                        (renewalReminderDates.get(k)).add(Calendar.MONTH, 1);
                        (renewalReminderDates.get(k)).add(Calendar.DATE, -10);
                    } else if (arguments.get(j + 1).equals("PREMIUM")) {
                        renewalAmount += 250;
                        //add 3 months & subtract 10 days
                        (renewalReminderDates.get(k)).add(Calendar.MONTH, 3);
                        (renewalReminderDates.get(k)).add(Calendar.DATE, -10);
                    }
                } else if (arguments.get(j).equals("VIDEO")) {
                    if (arguments.get(j + 1).equals("FREE")) {
                        renewalAmount += 0;
                        //add 1 month & subtract 10 days
                        (renewalReminderDates.get(k)).add(Calendar.MONTH, 1);
                        (renewalReminderDates.get(k)).add(Calendar.DATE, -10);
                    } else if (arguments.get(j + 1).equals("PERSONAL")) {
                        renewalAmount += 200;
                        //add 1 month & subtract 10 days
                        (renewalReminderDates.get(k)).add(Calendar.MONTH, 1);
                        (renewalReminderDates.get(k)).add(Calendar.DATE, -10);
                    } else if (arguments.get(j + 1).equals("PREMIUM")) {
                        renewalAmount += 500;
                        //add 3 months & subtract 10 days
                        (renewalReminderDates.get(k)).add(Calendar.MONTH, 3);
                        (renewalReminderDates.get(k)).add(Calendar.DATE, -10);
                    }
                } else if (arguments.get(j).equals("PODCAST")) {
                    if (arguments.get(j + 1).equals("FREE")) {
                        renewalAmount += 0;
                        //add 1 month & subtract 10 days
                        (renewalReminderDates.get(k)).add(Calendar.MONTH, 1);
                        (renewalReminderDates.get(k)).add(Calendar.DATE, -10);
                    } else if (arguments.get(j + 1).equals("PERSONAL")) {
                        renewalAmount += 100;
                        //add 1 month & subtract 10 days
                        (renewalReminderDates.get(k)).add(Calendar.MONTH, 1);
                        (renewalReminderDates.get(k)).add(Calendar.DATE, -10);
                    } else if (arguments.get(j + 1).equals("PREMIUM")) {
                        renewalAmount += 300;
                        //add 3 months & subtract 10 days
                        (renewalReminderDates.get(k)).add(Calendar.MONTH, 3);
                        (renewalReminderDates.get(k)).add(Calendar.DATE, -10);
                    }
                }

                it.next();
                i++;
                j += 2;
                k++;
            }

            //if loop terminates because of DUPLICATE_CATEGORY error scenario, update i, it & j
            if(it.hasNext() && commands.get(i).equals("null")) {
                i++;
                j+=2;
                it.next();
            }
            //calculates top up amount
            if (it.hasNext()) {
                int TOPUP_AMOUNT = 0;
                String TOP_UP_NAME = arguments.get(j);
                int NO_OF_MONTHS = Integer.parseInt(arguments.get(j + 1));

                if (TOP_UP_NAME.equals("FOUR_DEVICE"))
                    TOPUP_AMOUNT = 50 * NO_OF_MONTHS;
                else if (TOP_UP_NAME.equals("TEN_DEVICE"))
                    TOPUP_AMOUNT = 100 * NO_OF_MONTHS;

                //calculates total renewal amount
                renewalAmount += TOPUP_AMOUNT;
            }

            //prints output to console
            for (i = 0, j = 1; i < renewalReminderDates.size(); i++, j += 2) {
                //converts Calendar to Date & prints as String
                Date d = renewalReminderDates.get(i).getTime();
                System.out.println("RENEWAL_REMINDER " + arguments.get(j) + " " + formatter.format(d));
            }

            System.out.println("RENEWAL_AMOUNT " + renewalAmount);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
