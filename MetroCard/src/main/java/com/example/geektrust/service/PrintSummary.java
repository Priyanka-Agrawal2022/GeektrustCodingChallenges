package com.example.geektrust.service;

import com.example.geektrust.model.TotalCollectionAirport;
import com.example.geektrust.model.TotalCollectionCentral;

import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;

public class PrintSummary {
    private final String ADULT = "ADULT";
    private final String SENIOR_CITIZEN = "SENIOR_CITIZEN";
    private final String KID = "KID";
    private CalculateTotalCollection calculateTotalCollection = new CalculateTotalCollection();
    private TotalCollectionCentral central = calculateTotalCollection.getCentral();
    private TotalCollectionAirport airport = calculateTotalCollection.getAirport();
    private HashMap<String, Integer> passengerTypeCountCentral = new HashMap<>();
    private HashMap<String, Integer> passengerTypeCountAirport = new HashMap<>();

    public void printCentralSummary() {
        int centralAdultCount = central.getAdultCount();
        int centralSeniorCitizenCount = central.getSeniorCitizenCount();
        int centralKidCount = central.getKidCount();

        if(centralAdultCount != 0)
            passengerTypeCountCentral.put(ADULT, centralAdultCount);
        if(centralSeniorCitizenCount != 0)
            passengerTypeCountCentral.put(SENIOR_CITIZEN, centralSeniorCitizenCount);
        if(centralKidCount != 0)
            passengerTypeCountCentral.put(KID, centralKidCount);

        System.out.println("TOTAL_COLLECTION CENTRAL " + central.getAmount() + " " + central.getDiscount());
        System.out.println("PASSENGER_TYPE_SUMMARY");

        ComparisonLogic logic = new ComparisonLogic(passengerTypeCountCentral);
        TreeMap<String, Integer> map = new TreeMap<>(logic);
        map.putAll(passengerTypeCountCentral);

        for(String type: map.keySet()) {
            int count = map.get(type);
            System.out.println(type + " " + count);
        }
    }

    public void printAirportSummary() {
        int airportAdultCount = airport.getAdultCount();
        int airportSeniorCitizenCount = airport.getSeniorCitizenCount();
        int airportKidCount = airport.getKidCount();

        if(airportAdultCount != 0)
            passengerTypeCountAirport.put(ADULT, airportAdultCount);
        if(airportSeniorCitizenCount != 0)
            passengerTypeCountAirport.put(SENIOR_CITIZEN, airportSeniorCitizenCount);
        if(airportKidCount != 0)
            passengerTypeCountAirport.put(KID, airportKidCount);

        System.out.println("TOTAL_COLLECTION AIRPORT " + airport.getAmount() + " " + airport.getDiscount());
        System.out.println("PASSENGER_TYPE_SUMMARY");

        ComparisonLogic logic = new ComparisonLogic(passengerTypeCountAirport);
        TreeMap<String, Integer> map = new TreeMap<>(logic);
        map.putAll(passengerTypeCountAirport);

        for(String type: map.keySet()) {
            int count = map.get(type);
            System.out.println(type + " " + count);
        }
    }
}