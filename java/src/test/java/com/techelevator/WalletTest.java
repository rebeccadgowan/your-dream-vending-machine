package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class WalletTest {

    @Test
    public void balance_at_start_of_application_is_zero() {
        Wallet wallet = new Wallet();
        BigDecimal expected = new BigDecimal(0);
        BigDecimal actual = wallet.getBalance();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void when_resetBalance_is_called_balance_is_returned_to_zero() {
        Wallet wallet = new Wallet();
        BigDecimal expected = new BigDecimal(0);
        BigDecimal actual = wallet.resetBalance();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void if_two_dollars_are_added_increase_balance_by_two_dollars() {
        Wallet wallet = new Wallet();
        BigDecimal actual = wallet.feedMoney(new BigDecimal(2));
        BigDecimal expected = new BigDecimal(2);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void when_balance_is_two_dollars_give_8_quarters_in_change() {
        Wallet wallet = new Wallet();
        wallet.feedMoney(new BigDecimal(2));
        String actualResult = wallet.getChange();
        String expectedResult = "Your Change is: " +  "8" + " Quarters, " + "0" + " Dime(s)" + " and " + "0" + " Nickel(s) ";
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void test_all_3_denominations_of_coins_are_dispensed() {
        Wallet wallet = new Wallet();
        wallet.feedMoney(new BigDecimal(2));
        wallet.decreaseBalance(new BigDecimal(0.6));
        String actualResult = wallet.getChange();
        String expectedResult = "Your Change is: " +  "5" + " Quarters, " + "1" + " Dime(s)" + " and " + "1" + " Nickel(s) ";
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void current_timestamp_is_greater_than_expected_timestamp() {
        Wallet wallet = new Wallet();
        String expectedTimeStamp = "03/26/2021 04:22 PM";
        String actualTimeStamp = wallet.getCurrentTimeStamp();
        Assert.assertNotSame(expectedTimeStamp, actualTimeStamp);
    }
}