package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Wallet {
    private BigDecimal balance = new BigDecimal(0);

    public BigDecimal getBalance() {

        return balance;
    }

    public BigDecimal resetBalance() {
        this.balance = new BigDecimal(0);
        return balance;
    }

    public BigDecimal feedMoney(BigDecimal currentMoneyProvided){
        this.balance =  this.balance.add(currentMoneyProvided);
        return this.balance;
    }

    public void decreaseBalance(BigDecimal productPrice){
        this.balance =  this.balance.subtract(productPrice);
    }

    public String getChange() {
        String changeToReturn = "";
        BigDecimal bd = getBalance().setScale(2, RoundingMode.HALF_UP);

        double balanceInCentsDouble = getBalance().doubleValue() * 100;
        int balanceInCents = (int) balanceInCentsDouble;
        int changeQuarters = 0 ;
        int changeDimes = 0 ;
        int changeNickles = 0 ;

        if (balanceInCents>0) {
            changeQuarters = balanceInCents / 25 ;
            balanceInCents = balanceInCents % 25 ;
        }

        if (balanceInCents>0) {
            changeDimes = balanceInCents / 10;
            balanceInCents = balanceInCents % 10 ;
        }

        if (balanceInCents>0) {
            changeNickles = balanceInCents / 5;
        }
        //write to file
        String timeStamp = getCurrentTimeStamp();
        String changeDetails  = timeStamp + " " + "GIVE CHANGE" + " $" + bd +  " $0.00" + System.lineSeparator();
        fileWriting(changeDetails);
        return ("Your Change is: " +  changeQuarters + " Quarters, " + changeDimes + " Dime(s)" + " and " + changeNickles + " Nickel(s) ") ;
    }
    public void fileWriting(String str) {
        File file = new File("Log.txt");

        try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(file, true))) {
            printWriter.append(str);
            printWriter.flush();
            printWriter.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public String getCurrentTimeStamp() {
        DateTimeFormatter targetFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");
        LocalDateTime now = LocalDateTime.now();
        String currTimeStamp = targetFormat.format(now);
        //System.out.println(currTimeStamp);
        return currTimeStamp;
    }

}
