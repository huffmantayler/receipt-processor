package com.example.receiptprocessor.service;

import com.example.receiptprocessor.model.Receipt;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class ReceiptService {
    private final Map<String, Integer> receiptStore = new HashMap<>();

    public String processReceipt(Receipt receipt) {
        // Create random id for the receipt
        String id = UUID.randomUUID().toString();
        // caluclate the points and save it to the hashmap
        int points = calculatePoints(receipt);
        receiptStore.put(id, points);
        return id;
    }

    // return the saved points from the hashmap
    public Integer getPoints(String id) {
        return receiptStore.get(id);
    }

    private int calculatePoints(Receipt receipt) {
        int points = 0;

        // Rule 1: One point for each alphanumeric character in the retailer name.
        points += receipt.getRetailer().replaceAll("[^a-zA-Z0-9]", "").length();

        // Rule 2: 50 points if the total is a round dollar amount.
        if (receipt.getTotal().matches("\\d+\\.00")) {
            points += 50;
        }

        // Rule 3: 25 points if the total is a multiple of 0.25.
        if (Double.parseDouble(receipt.getTotal()) % 0.25 == 0) {
            points += 25;
        }

        // Rule 4: 5 points for every two items on the receipt.
        points += (receipt.getItems().size() / 2) * 5;

        // Rule 5: Points for item descriptions that are multiples of 3.
        for (var item : receipt.getItems()) {
            int descLength = item.getShortDescription().length();
            if (descLength % 3 == 0) {
                points += Math.ceil(Double.parseDouble(item.getPrice()) * 0.2);
            }
        }

        // Rule 6: 6 points if the day in the purchase date is odd.
        int day = Integer.parseInt(receipt.getPurchaseDate().split("-")[2]);
        if (day % 2 != 0) {
            points += 6;
        }

        // Rule 7: 10 points if the time is between 2:00 PM and 4:00 PM.
        LocalTime time = LocalTime.parse(receipt.getPurchaseTime());
        if (time.isAfter(LocalTime.of(14, 0)) && time.isBefore(LocalTime.of(16, 0))) {
            points += 10;
        }

        return points;
    }
}