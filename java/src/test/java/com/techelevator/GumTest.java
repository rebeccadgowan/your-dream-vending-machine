package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class GumTest {

    @Test
    public void when_getSound_is_called_return_chew_chew_yum() {
        Product gum = new Gum("Little League Chew", new BigDecimal(0.95), "D2");
        String expected = "Chew Chew, Yum!";
        String actual = gum.getSound();
        Assert.assertEquals(expected, actual);
    }
}