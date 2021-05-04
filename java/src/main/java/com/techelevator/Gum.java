package com.techelevator;

import java.math.BigDecimal;

public class Gum extends Product {
    public Gum(String name, BigDecimal price, String slotIdentifier) {
        super(name, price, slotIdentifier);
    }

    @Override
    public String getSound() {
        return "Chew Chew, Yum!";
    }
}
