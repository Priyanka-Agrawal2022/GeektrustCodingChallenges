package com.example.geektrust.model;

public class TotalCollectionAirport {
    private int amount;
    private int discount;
    private int adultCount;
    private int seniorCitizenCount;
    private int kidCount;

    public int getAmount() {
        return amount;
    }

    public int getDiscount() {
        return discount;
    }

    public int getAdultCount() {
        return adultCount;
    }

    public int getSeniorCitizenCount() {
        return seniorCitizenCount;
    }

    public int getKidCount() {
        return kidCount;
    }

    public void setAmount(int amount) {
        this.amount += amount;
    }

    public void setDiscount(int discount) {
        this.discount += discount;
    }

    public void updateAdultCount() {
        this.adultCount++;
    }

    public void updateSeniorCitizenCount() {
        this.seniorCitizenCount++;
    }

    public void updateKidCount() {
        this.kidCount++;
    }
}