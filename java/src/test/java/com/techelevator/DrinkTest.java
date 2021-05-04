package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class DrinkTest {

    @Test
    public void when_getSound_is_called_return_glug_glug_yum() {
        Product drink = new Drink("Cola", new BigDecimal(1.25), "C1");
        String expected = "Glug Glug, Yum!";
        String actual = drink.getSound();
        Assert.assertEquals(expected, actual);
    }
}