package br.com.rule.stay;

import java.util.Calendar;

public class Stay {
    private String document;

    private Calendar checkInDate;

    private Calendar checkOutDate;

    private byte usedCarParkingSpot;

    public Stay(String document, Calendar checkInDate, Calendar checkOutDate, byte usedCarParkingSpot) {
        this.document = document;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.usedCarParkingSpot = usedCarParkingSpot;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public Calendar getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Calendar checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Calendar getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Calendar checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public byte getUsedCarParkingSpot() {
        return usedCarParkingSpot;
    }

    public void setUsedCarParkingSpot(byte usedCarParkingSpot) {
        this.usedCarParkingSpot = usedCarParkingSpot;
    }
}
