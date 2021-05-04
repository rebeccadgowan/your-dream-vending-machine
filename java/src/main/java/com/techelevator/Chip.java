package com.techelevator;

import java.math.BigDecimal;

public class Chip extends Product {

    public Chip(String name, BigDecimal price, String slotIdentifier) {

        super(name, price, slotIdentifier);
    }

    @Override
    public String getSound() {
        return "Crunch Crunch, Yum!";
    }
}
