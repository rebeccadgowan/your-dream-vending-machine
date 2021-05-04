package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Index {

    private List<Product> productList = new ArrayList<>();

    public void addProduct(Product product) {
        productList.add(product);
    }

    public void fileWriting(String str) {
        File file = new File("Log.txt");

        try(PrintWriter printWriter = new PrintWriter(new FileOutputStream(file, true))) {
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

    public void handleProductPurchase(String slotIdentifier, Wallet wallet) {
        boolean productFound = false;

            for(int i=0 ; i< productList.size();i++) {
                if (productList.get(i).getSlotIdentifier().equalsIgnoreCase(slotIdentifier)) {
                    if (productList.get(i).productInStock()) {
                        if (wallet.getBalance().compareTo(productList.get(i).getPrice())>=0){
                            System.out.println("--------------------------------------------------");
                            System.out.println("Product Found - " + productList.get(i).getName());
                            System.out.println(productList.get(i).getSound());
                            BigDecimal originalBalance = wallet.getBalance();
                            wallet.decreaseBalance(productList.get(i).getPrice());
                            //---------------
                            String timeStamp = getCurrentTimeStamp();
                            //fileWriting(timeStamp);
                            String purchaseDetails = timeStamp + " " + productList.get(i).getName() + " " +  productList.get(i).getSlotIdentifier() + "  $" + originalBalance + "  $"+ wallet.getBalance()+ System.lineSeparator();
                            fileWriting(purchaseDetails);

                            //----------------
                            System.out.println("Your remaining balance is: $" + wallet.getBalance());
                            
                            productList.get(i).decreaseCount();
                            System.out.println("--------------------------------------------------");


                        } else {
                            System.out.println("****** ~~ YOU DO NOT HAVE ENOUGH MONEY TO BUY THIS  ~~ ****** !");
                        }
                    } else {
                        System.out.println("****** ~~ SOLD OUT ~~ ****** !");
                    }
                    productFound = true ;
                    break;
                }
            }
            if (!productFound) System.out.println("****** ~~ WRONG CODE. PLEASE TRY AGAIN ~~ ****** !");
    }

    public void printOutProductList() {
        System.out.println("--------------------------------------------------------- ");
        System.out.println("****               ITEMS AVAILABLE                   **** ");
        System.out.println("--------------------------------------------------------- ");
        System.out.printf("%-10s %-25s %-15s %-15s\n","CODE","PRODUCT","PRICE","QTY");
        System.out.printf("%-10s %-25s %-15s %-15s\n","----","-------","-----","---");
        for(Product product : productList) {
            System.out.printf("%-10s %-25s %-15s %-15s\n",product.getSlotIdentifier(),product.getName(),product.getPrice(),product.getCount());
        }
        System.out.println("---------------------------------------------------------- ");
    }
}

