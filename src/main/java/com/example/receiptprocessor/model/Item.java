package com.example.receiptprocessor.model;

public class Item {
    private String shortDescription;
    private String price;

    public String getShortDescription() {
        return shortDescription.trim();
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}