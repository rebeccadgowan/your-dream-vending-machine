package com.techelevator;

import com.techelevator.view.Menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_EXIT_OPTION = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_EXIT_OPTION};
	private static final String[] PURCHASE_MENU_OPTIONS = {"Feed Money", "Select Product", "Finish Transaction"};
	private static final String[] MONEY_OPTIONS = {"$1", "$2", "$5", "$10", "Back"};

	Wallet wallet = new Wallet();

	Index fang = new Index();
	private Menu menu;
	Index productList;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
		this.fang = generateItems();
	}

	public void run() {
		System.out.println("\n---------------------------------------------------------- ");
		System.out.println("========       V E N D O M A T I C -  8 0 0      ========= ");

		while (true) {
			System.out.println("---------------------------------------------------------- ");
			System.out.println("**                  MAIN MENU                           **");
			System.out.println("---------------------------------------------------------- ");

			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				displayVendingMachineItems();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				handlePurchaseOptions();
			} else if (choice.equals(MAIN_MENU_EXIT_OPTION)) {
				System.out.println("\n--------------------------------------------------------------------- ");
				System.out.println("**               THANK YOU FOR SHOPPING WITH US !                 **");
				System.out.println("**                      HAVE A GREAT DAY                          **");
				System.out.println("--------------------------------------------------------------------- ");
				System.exit(0);
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}

	public void displayVendingMachineItems() {
		productList = getFang();
		productList.printOutProductList();
		System.out.println(" AVAILABLE BALANCE : $" + wallet.getBalance());
		System.out.println("---------------------------------------------------------- ");
	}

	public Index generateItems() {
		File inputFile = new File("vendingmachine.csv");
		try (Scanner scanner = new Scanner(inputFile)) {
			while (scanner.hasNext()) {
				String lineOfData = scanner.nextLine();
				String[] arr = lineOfData.split("\\|");
				String slotNumber = arr[0];
				String productName = arr[1];
				BigDecimal price = new BigDecimal(arr[2]);
				String productType = arr[3];

				if (arr[3].equals("Chip")) {
					Product product = new Chip(productName, price, slotNumber);
					fang.addProduct(product);
				} else if (arr[3].equals("Candy")) {
					Product product1 = new Candy(productName, price, slotNumber);
					fang.addProduct(product1);
				} else if (arr[3].equals("Drink")) {
					Product product2 = new Drink(productName, price, slotNumber);
					fang.addProduct(product2);
				} else if (arr[3].equals("Gum")) {
					Product product3 = new Gum(productName, price, slotNumber);
					fang.addProduct(product3);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("**********************************");
			System.out.println("VENDING MACHINE IS CURRENTLY DOWN");
			System.out.println("**********************************");

			System.exit(404);
		}
		return fang;
	}

	public void handlePurchaseOptions() {
		boolean stay = true;

		while (stay) {
			System.out.println("---------------------------------- ");
			System.out.println("**       PURCHASE MENU          **");
			System.out.println("---------------------------------- ");
			System.out.println("Current Balance: $" + wallet.getBalance().setScale(2, RoundingMode.HALF_UP));
			String choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
			if (choice.equals("Feed Money")) {
				handleFeedMoney();
			} else if (choice.equals("Select Product")) {
				handleSelection();
			} else if (choice.equals("Finish Transaction")) {
				System.out.println("---------------------------------------------------- ");
				System.out.println(wallet.getChange());
				wallet.resetBalance();
				System.out.println("---------------------------------------------------- ");
				stay = false;
			}
		}
	}

	public void handleFeedMoney() {
		boolean stay = true;

		System.out.println("---------------------------------- ");
		System.out.println("**       FEED MONEY            **");
		System.out.println("---------------------------------- ");

		while (stay) {
			String choice = (String) menu.getChoiceFromOptions(MONEY_OPTIONS);
			BigDecimal newBalance = new BigDecimal(0);
			BigDecimal oldBalance = wallet.getBalance();
			String timeStamp = getCurrentTimeStamp();
			if (choice.equals("$1")) {
				newBalance = wallet.feedMoney(new BigDecimal(1));
				System.out.println("Current Money Provided: $" + newBalance);
				String balanceDetails = timeStamp + " " + "FEED MONEY" + " $" + oldBalance + " $" + newBalance+ System.lineSeparator();
				fileWriting(balanceDetails);

			} else if (choice.equals("$2")) {
				newBalance = wallet.feedMoney(new BigDecimal(2));
				System.out.println("Current Money Provided: $" + newBalance);
				String balanceDetails = timeStamp + " " + "FEED MONEY" + " $" + oldBalance + " $" + newBalance+ System.lineSeparator();
				fileWriting(balanceDetails);

			} else if (choice.equals("$5")) {
				newBalance = wallet.feedMoney(new BigDecimal(5));
				System.out.println("Current Money Provided: $" + newBalance);
				String balanceDetails = timeStamp + " " + "FEED MONEY" + " $" + oldBalance + " $" + newBalance+ System.lineSeparator();
				fileWriting(balanceDetails);

			} else if (choice.equals("$10")) {
				newBalance = wallet.feedMoney(new BigDecimal(10));
				System.out.println("Current Money Provided: $" + newBalance);
				String balanceDetails = timeStamp + " " + "FEED MONEY" + " $" + oldBalance + " $" + newBalance + System.lineSeparator();
				fileWriting(balanceDetails);

			} else if (choice.equals("Back")) {
				stay = false;
			}
		}
	}

	// Product selected
	public void handleSelection() {
		displayVendingMachineItems();
		Scanner input = new Scanner(System.in);
		System.out.println(" ");
		System.out.println("Enter the CODE");
		String slotIdentifier = input.nextLine();
		getFang().handleProductPurchase(slotIdentifier, wallet);
	}

	public String getCurrentTimeStamp() {
		DateTimeFormatter targetFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");
		LocalDateTime now = LocalDateTime.now();
		String currTimeStamp = targetFormat.format(now);
		//System.out.println(currTimeStamp);
		return currTimeStamp;
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
		public void setFang (Index fang){
			this.fang = fang;
		}

		public Index getFang () {
			return fang;
		}

	}


