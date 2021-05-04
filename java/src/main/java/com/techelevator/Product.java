package com.techelevator;

import java.math.BigDecimal;

public abstract class Product {
    private String slotIdentifier;
    private String name;
    private BigDecimal price;
    private int count = 5;

    public Product(String name, BigDecimal price, String slotIdentifier) {
        this.name = name;
        this.price = price;
        this.slotIdentifier = slotIdentifier;
    }

    public abstract String getSound();

    public String getSlotIdentifier() {
        return slotIdentifier;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void decreaseCount() {
        this.count = count-1;
    }

    public boolean productInStock() {
        if (this.count >0)
            return true ;
        else
            return false;
    }

}
