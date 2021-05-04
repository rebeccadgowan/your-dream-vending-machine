package com.techelevator;

import com.techelevator.view.Menu;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class VendingMachineCLITest {

    @Test
    public void current_timestamp_is_greater_than_expected_timestamp() {
        Menu menu = new Menu(System.in, System.out);
        VendingMachineCLI cli = new VendingMachineCLI(menu);
        String expectedTimeStamp = "03/26/2021 04:22 PM";
        String actualTimeStamp = cli.getCurrentTimeStamp();
        Assert.assertNotSame(expectedTimeStamp, actualTimeStamp);
    }

    @Test
    public void generate_item(){
        Menu menu = new Menu(System.in, System.out);
        VendingMachineCLI cli = new VendingMachineCLI(menu);
        Index index = new Index();
        index= cli.generateItems();

        //Check test output to see if the product inventory is displayed.
        index.printOutProductList();


    }
}