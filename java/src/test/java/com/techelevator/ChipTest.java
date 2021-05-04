package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ChipTest {

    @Test
    public void when_getSound_is_called_return_crunch_crunch_yum() {
        Product chip = new Chip("Stackers", new BigDecimal(1.45), "A2");
        String expected = "Crunch Crunch, Yum!";
        String actual = chip.getSound();
        Assert.assertEquals(expected, actual);

    }
}