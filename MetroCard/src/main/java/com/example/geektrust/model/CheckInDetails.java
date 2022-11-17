package com.example.geektrust.model;

public class CheckInDetails {
    private String metroCardNo;
    private String passengerType;
    private String fromStation;

    public CheckInDetails(String[] arguments) {
        this.metroCardNo = arguments[1];
        this.passengerType = arguments[2];
        this.fromStation = arguments[3];
    }

    public String getMetroCardNo() {
        return metroCardNo;
    }

    public String getPassengerType() {
        return passengerType;
    }

    public String getFromStation() {
        return fromStation;
    }
}