package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CandyTest {

    @Test
    public void when_getSound_is_called_return_munch_munch_yum() {
        Product candy = new Candy("Moonpie", new BigDecimal(1.80), "B1");
        String expected = "Munch Munch, Yum!";
        String actual = candy.getSound();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void when_getSlotIdentifier_is_called_return_slotIdentifier() {
        Product candy = new Candy("Cowtales", new BigDecimal(1.50), "B2");
        String expected = "B2";
        String actual = candy.getSlotIdentifier();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void when_getName_is_called_return_name() {
        Product candy = new Candy("Wonka Bar", new BigDecimal(1.50), "B3");
        String expected = "Wonka Bar";
        String actual = candy.getName();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void when_getPrice_is_called_return_price() {
        Product candy = new Candy("Crunchie", new BigDecimal(1.75), "B4");
        BigDecimal expected = new BigDecimal(1.75);
        BigDecimal actual = candy.getPrice();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void when_getCount_is_called_when_starting_the_program_return_count_of_5() {
        Product candy = new Candy("Moonpie", new BigDecimal(1.80), "B1");
        int expected = 5;
        int actual = candy.getCount();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void if_product_is_in_stock_return_true() {
        Product candy = new Candy("Cowtales", new BigDecimal(1.50), "B2");
        boolean expected = true;
        boolean actual = candy.productInStock();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void if_count_is_zero_return_false() {
        Product candy = new Candy("Wonka Bar", new BigDecimal(1.50), "B3");
        int count = 5;
        for(int i = 0; i < 5; i++) {
            candy.decreaseCount();
        }
        boolean actualResult = candy.productInStock();
        boolean expectedResult = false;
        Assert.assertEquals(expectedResult, actualResult);
    }
}