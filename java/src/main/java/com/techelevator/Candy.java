package com.techelevator;

import java.math.BigDecimal;

public class Candy extends Product {
    public Candy(String name, BigDecimal price, String slotIdentifier) {

        super(name, price, slotIdentifier);
    }

    @Override
    public String getSound() {
        return "Munch Munch, Yum!";
    }
}
