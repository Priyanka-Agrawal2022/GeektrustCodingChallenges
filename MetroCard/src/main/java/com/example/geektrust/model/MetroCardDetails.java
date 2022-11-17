package com.example.geektrust.model;

public class MetroCardDetails {
    private final String metroCardNo;
    private int balance;
    private boolean isRoundTrip;

    public MetroCardDetails(String[] arguments) {
        this.metroCardNo = arguments[1];
        this.balance = Integer.parseInt(arguments[2]);
        isRoundTrip = false;
    }

    public int getBalance() {
        return balance;
    }

    public boolean getIsRoundTrip() {
        return isRoundTrip;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setIsRoundTrip(boolean isRoundTrip) {
        this.isRoundTrip = isRoundTrip;
    }
}