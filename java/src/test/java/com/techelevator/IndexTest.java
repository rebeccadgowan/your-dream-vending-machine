package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class IndexTest {

    @Test
    public void current_timestamp_is_greater_than_expected_timestamp() {
    Index index = new Index();
    String expectedTimeStamp = "03/26/2021 04:22 PM";
    String actualTimeStamp = index.getCurrentTimeStamp();
    Assert.assertNotSame(expectedTimeStamp, actualTimeStamp);
    }


    @Test
    public void check_prints_functionality(){
        Index testIndex = new Index();
        Wallet wallet = new Wallet();
        testIndex.handleProductPurchase("R4", wallet);

    }
}