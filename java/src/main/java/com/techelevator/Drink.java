package com.techelevator;

import java.math.BigDecimal;

public class Drink extends Product {
    public Drink(String name, BigDecimal price, String slotIdentifier) {

        super(name, price, slotIdentifier);
    }

    @Override
    public String getSound() {
        return "Glug Glug, Yum!";
    }
}
