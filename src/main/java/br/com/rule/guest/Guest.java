package br.com.rule.guest;

import javax.persistence.*;

public class Guest {

    private String document;

    private String name;

    private int phoneNumber;

    Guest() {
    }

    public Guest(String document, String name, int phoneNumber) {
        this.document = document;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
