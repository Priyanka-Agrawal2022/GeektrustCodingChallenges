package com.example.geektrust.service;

import com.example.geektrust.model.CheckInDetails;
import com.example.geektrust.model.MetroCardDetails;
import com.example.geektrust.model.TotalCollectionAirport;
import com.example.geektrust.model.TotalCollectionCentral;

import java.util.ArrayList;

public class CalculateTotalCollection {
    private final int ADULT_CHARGE = 200;
    private final int SENIOR_CITIZEN_CHARGE = 100;
    private final int KID_CHARGE = 50;
    private final float RECHARGE_FACTOR = 0.02f;
    private final int TWO = 2;
    private final String ADULT = "ADULT";
    private final String SENIOR_CITIZEN = "SENIOR_CITIZEN";
    private final String KID = "KID";
    private final String CENTRAL = "CENTRAL";
    private final String AIRPORT = "AIRPORT";
    private StoreInput storeInput = new StoreInput();
    private ArrayList<CheckInDetails> checkInDetails = storeInput.getCheckInDetails();
    private static TotalCollectionCentral central = new TotalCollectionCentral();
    private static TotalCollectionAirport airport = new TotalCollectionAirport();

    public void calculateTotalCollection() {

        for(CheckInDetails checkIn: checkInDetails) {
            String metroCardNo = checkIn.getMetroCardNo();
            String passengerType = checkIn.getPassengerType();
            String fromStation = checkIn.getFromStation();

            MetroCardDetails metroCardObject = storeInput.getMetroCardDetails(metroCardNo);

            callUpdateMethod(passengerType, fromStation, metroCardObject);
        }
    }

    //This method recharges MetroCard balance with the required amount
    //and returns the service charge
    private int rechargeMetroCard(MetroCardDetails metroCardObject, int requiredAmount) {
        int metroCardBalance = metroCardObject.getBalance();
        int transcationAmount = requiredAmount - metroCardBalance;
        int serviceCharge = (int)(RECHARGE_FACTOR*transcationAmount);

        metroCardObject.setBalance(metroCardBalance + transcationAmount);

        return serviceCharge;
    }

    private void callUpdateMethod(String passengerType, String fromStation, MetroCardDetails metroCardObject) {
        if (passengerType.equals(ADULT)) {
            if (fromStation.equals(CENTRAL)) {
                updateAdultCentral(metroCardObject);
            }
            else if (fromStation.equals(AIRPORT)) {
                updateAdultAirport(metroCardObject);
            }
        }

        if (passengerType.equals(SENIOR_CITIZEN)) {
            if (fromStation.equals(CENTRAL)) {
                updateSeniorCitizenCentral(metroCardObject);
            }
            else if (fromStation.equals(AIRPORT)) {
                updateSeniorCitizenAirport(metroCardObject);
            }
        }

        if (passengerType.equals(KID)) {
            if (fromStation.equals(CENTRAL)) {
                updateKidCentral(metroCardObject);
            }
            else if (fromStation.equals(AIRPORT)) {
                updateKidAirport(metroCardObject);
            }
        }
    }

    private void updateMetroCardObject(MetroCardDetails metroCardObject, int charge) {
        int metroCardBalance = metroCardObject.getBalance();

        metroCardObject.setBalance(metroCardBalance - charge);

        if (metroCardObject.getIsRoundTrip())
            metroCardObject.setIsRoundTrip(false);
        else
            metroCardObject.setIsRoundTrip(true);
    }

    private void updateAdultCentral(MetroCardDetails metroCardObject) {
        int metroCardBalance = metroCardObject.getBalance();
        int serviceCharge = 0;
        if (!metroCardObject.getIsRoundTrip()) {
            if (metroCardBalance < ADULT_CHARGE) {
                serviceCharge = rechargeMetroCard(metroCardObject, ADULT_CHARGE);
            }
            central.setAmount(ADULT_CHARGE + serviceCharge);
            updateMetroCardObject(metroCardObject, ADULT_CHARGE);
        }
        else {
            if (metroCardBalance < ADULT_CHARGE/TWO) {
                serviceCharge = rechargeMetroCard(metroCardObject, ADULT_CHARGE/TWO);
            }
            central.setAmount(ADULT_CHARGE/TWO + serviceCharge);
            central.setDiscount(ADULT_CHARGE/TWO);
            updateMetroCardObject(metroCardObject, ADULT_CHARGE/TWO);
        }
        central.updateAdultCount();

    }

    private void updateAdultAirport(MetroCardDetails metroCardObject) {
        int metroCardBalance = metroCardObject.getBalance();
        int serviceCharge = 0;
        if (!metroCardObject.getIsRoundTrip()) {
            if (metroCardBalance < ADULT_CHARGE) {
                serviceCharge = rechargeMetroCard(metroCardObject, ADULT_CHARGE);
            }
            airport.setAmount(ADULT_CHARGE + serviceCharge);
            updateMetroCardObject(metroCardObject, ADULT_CHARGE);
        }
        else {
            if (metroCardBalance < ADULT_CHARGE/TWO) {
                serviceCharge = rechargeMetroCard(metroCardObject, ADULT_CHARGE/TWO);
            }
            airport.setAmount(ADULT_CHARGE/TWO + serviceCharge);
            airport.setDiscount(ADULT_CHARGE/TWO);
            updateMetroCardObject(metroCardObject, ADULT_CHARGE/TWO);
        }
        airport.updateAdultCount();
    }

    private void updateSeniorCitizenCentral(MetroCardDetails metroCardObject) {
        int metroCardBalance = metroCardObject.getBalance();
        int serviceCharge = 0;
        if (!metroCardObject.getIsRoundTrip()) {
            if (metroCardBalance < SENIOR_CITIZEN_CHARGE) {
                serviceCharge = rechargeMetroCard(metroCardObject, SENIOR_CITIZEN_CHARGE);
            }
            central.setAmount(SENIOR_CITIZEN_CHARGE + serviceCharge);
            updateMetroCardObject(metroCardObject, SENIOR_CITIZEN_CHARGE);
        }
        else {
            if (metroCardBalance < SENIOR_CITIZEN_CHARGE/TWO) {
                serviceCharge = rechargeMetroCard(metroCardObject, SENIOR_CITIZEN_CHARGE/ TWO);
            }
            central.setAmount(SENIOR_CITIZEN_CHARGE/TWO + serviceCharge);
            central.setDiscount(SENIOR_CITIZEN_CHARGE/TWO);
            updateMetroCardObject(metroCardObject, SENIOR_CITIZEN_CHARGE/TWO);
        }
        central.updateSeniorCitizenCount();
    }

    private void updateSeniorCitizenAirport(MetroCardDetails metroCardObject) {
        int metroCardBalance = metroCardObject.getBalance();
        int serviceCharge = 0;
        if (!metroCardObject.getIsRoundTrip()) {
            if (metroCardBalance < SENIOR_CITIZEN_CHARGE) {
                serviceCharge = rechargeMetroCard(metroCardObject, SENIOR_CITIZEN_CHARGE);
            }
            airport.setAmount(SENIOR_CITIZEN_CHARGE + serviceCharge);
            updateMetroCardObject(metroCardObject, SENIOR_CITIZEN_CHARGE);
        }
        else {
            if (metroCardBalance < SENIOR_CITIZEN_CHARGE/TWO) {
                serviceCharge = rechargeMetroCard(metroCardObject, SENIOR_CITIZEN_CHARGE/TWO);
            }
            airport.setAmount(SENIOR_CITIZEN_CHARGE/TWO + serviceCharge);
            airport.setDiscount(SENIOR_CITIZEN_CHARGE/TWO);
            updateMetroCardObject(metroCardObject, SENIOR_CITIZEN_CHARGE/TWO);
        }
        airport.updateSeniorCitizenCount();
    }

    private void updateKidCentral(MetroCardDetails metroCardObject) {
        int metroCardBalance = metroCardObject.getBalance();
        int serviceCharge = 0;
        if (!metroCardObject.getIsRoundTrip()) {
            if (metroCardBalance < KID_CHARGE) {
                serviceCharge = rechargeMetroCard(metroCardObject, KID_CHARGE);
            }
            central.setAmount(KID_CHARGE + serviceCharge);
            updateMetroCardObject(metroCardObject, KID_CHARGE);
        }
        else {
            if (metroCardBalance < KID_CHARGE/TWO) {
                serviceCharge = rechargeMetroCard(metroCardObject, KID_CHARGE/TWO);
            }
            central.setAmount(KID_CHARGE/TWO + serviceCharge);
            central.setDiscount(KID_CHARGE/TWO);
            updateMetroCardObject(metroCardObject, KID_CHARGE/TWO);
        }
        central.updateKidCount();
    }

    private void updateKidAirport(MetroCardDetails metroCardObject) {
        int metroCardBalance = metroCardObject.getBalance();
        int serviceCharge = 0;
        if (!metroCardObject.getIsRoundTrip()) {
            if (metroCardBalance < KID_CHARGE) {
                serviceCharge = rechargeMetroCard(metroCardObject, KID_CHARGE);
            }
            airport.setAmount(KID_CHARGE + serviceCharge);
            updateMetroCardObject(metroCardObject, KID_CHARGE);
        }
        else {
            if (metroCardBalance < KID_CHARGE/TWO) {
                serviceCharge = rechargeMetroCard(metroCardObject, KID_CHARGE/TWO);
            }
            airport.setAmount(KID_CHARGE/TWO + serviceCharge);
            airport.setDiscount(KID_CHARGE/ TWO);
            updateMetroCardObject(metroCardObject, KID_CHARGE/TWO);
        }
        airport.updateKidCount();
    }

    public TotalCollectionCentral getCentral() {
        return central;
    }

    public TotalCollectionAirport getAirport() {
        return airport;
    }
}