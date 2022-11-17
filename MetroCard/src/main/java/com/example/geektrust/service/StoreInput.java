package com.example.geektrust.service;

import com.example.geektrust.model.CheckInDetails;
import com.example.geektrust.model.MetroCardDetails;

import java.util.ArrayList;
import java.util.HashMap;

public class StoreInput {
    private static HashMap<String, MetroCardDetails> metroCardDetails = new HashMap<>();
    private static ArrayList<CheckInDetails> checkInDetails = new ArrayList<>();

    public void storeMetroCardDetails(String[] arguments) {
        String metroCardNo = arguments[1];
        metroCardDetails.put(metroCardNo, new MetroCardDetails(arguments));
    }

    public MetroCardDetails getMetroCardDetails(String metroCardNo) {
        MetroCardDetails metroCardObject = metroCardDetails.get(metroCardNo);
        return  metroCardObject;
    }

    public void storeCheckInDetails(String[] arguments) {
        checkInDetails.add(new CheckInDetails(arguments));
    }

    public ArrayList<CheckInDetails> getCheckInDetails() {
        return checkInDetails;
    }
}