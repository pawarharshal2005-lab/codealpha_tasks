package com.codealpha.stocktrading;

import java.util.ArrayList;
import java.util.Scanner;

public class StockTradingPlatform {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Stock> stocks = new ArrayList<>();
        ArrayList<Integer> portfolio = new ArrayList<>();
        ArrayList<String> history = new ArrayList<>();
        ArrayList<Double> totalCost = new ArrayList<>();
        totalCost.add(0.0);
        totalCost.add(0.0);
        totalCost.add(0.0);

        stocks.add(new Stock("TCS", 3500, 10));
        stocks.add(new Stock("Infosys", 1800, 10));
        stocks.add(new Stock("Wipro", 500, 10));

        portfolio.add(0);
        portfolio.add(0);
        portfolio.add(0);

        double balance = 10000;
        double totalProfitLoss = 0;
        int choice;

        do {
            System.out.println("\n--- Stock Trading Platform ---");
            System.out.println("1. View Stocks");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Check Balance");
            System.out.println("6. View Profit/Loss");
            System.out.println("7. Transaction History");
            System.out.println("8. Exit");
          
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

            case 1:
                for (Stock stock : stocks) {
                    stock.displayStock();
                    System.out.println("----------------");
                }
                break;

                
            case 2:
                System.out.println("Available Stocks:");

                for (int i = 0; i < stocks.size(); i++) {
                    System.out.println((i + 1) + ". "
                            + stocks.get(i).name + " - Rs. "
                            + stocks.get(i).price);
                }

                System.out.print("Choose stock number: ");
                int buyChoice = sc.nextInt();

                if (buyChoice < 1 || buyChoice > stocks.size()) {
                    System.out.println("Invalid stock choice.");
                    break;
                }

                Stock buyStock = stocks.get(buyChoice - 1);

                System.out.print("Enter quantity: ");
                int buyQuantity = sc.nextInt();

                double buyTotal = buyStock.price * buyQuantity;

                if (buyQuantity > 0
                        && buyQuantity <= buyStock.quantity
                        && buyTotal <= balance) {

                    balance -= buyTotal;
                    buyStock.quantity -= buyQuantity;

                    int oldQuantity = portfolio.get(buyChoice - 1);
                    portfolio.set(buyChoice - 1,
                            oldQuantity + buyQuantity);

                    totalCost.set(buyChoice - 1,
                            totalCost.get(buyChoice - 1) + buyTotal);

                    history.add("Bought " + buyQuantity + " shares of "
                            + buyStock.name + " for Rs. " + buyTotal);

                    System.out.println("Stock purchased successfully!");
                    System.out.println("Total amount: Rs. " + buyTotal);

                } else {
                    System.out.println("Invalid quantity or insufficient balance.");
                }
                break;
            

            case 3:
                System.out.println("Your Portfolio:");

                for (int i = 0; i < stocks.size(); i++) {
                    System.out.println((i + 1) + ". "
                            + stocks.get(i).name
                            + " - Owned: " + portfolio.get(i));
                }

                System.out.print("Choose stock number to sell: ");
                int sellChoice = sc.nextInt();

                if (sellChoice < 1 || sellChoice > stocks.size()) {
                    System.out.println("Invalid stock choice.");
                    break;
                }

                System.out.print("Enter quantity to sell: ");
                int sellQuantity = sc.nextInt();

                int ownedQuantity = portfolio.get(sellChoice - 1);

                if (sellQuantity > 0 && sellQuantity <= ownedQuantity) {

                    Stock sellStock = stocks.get(sellChoice - 1);

                    double sellTotal = sellStock.price * sellQuantity;
                    double averageCost = totalCost.get(sellChoice - 1)
                            / portfolio.get(sellChoice - 1);

                    balance += sellTotal;
                    sellStock.quantity += sellQuantity;

                    totalProfitLoss += (sellStock.price - averageCost) * sellQuantity;

                    totalCost.set(sellChoice - 1,
                            totalCost.get(sellChoice - 1) - (averageCost * sellQuantity));

                    portfolio.set(sellChoice - 1,
                            ownedQuantity - sellQuantity);

                    System.out.println("Stock sold successfully!");
                    history.add("Sold " + sellQuantity + " shares of "+ sellStock.name + " for Rs. " + sellTotal);
                    System.out.println("Amount received: Rs. " + sellTotal);

                } else {
                    System.out.println("Invalid quantity or not enough stocks.");
                }
                break;

            case 4:
                System.out.println("\n--- Your Portfolio ---");

                boolean hasStocks = false;
                double portfolioValue = 0;

                for (int i = 0; i < stocks.size(); i++) {

                    int owned = portfolio.get(i);

                    if (owned > 0) {
                        double value = owned * stocks.get(i).price;

                        System.out.println("Stock: " + stocks.get(i).name);
                        System.out.println("Quantity: " + owned);
                        System.out.println("Current Value: Rs. " + value);

                        portfolioValue += value;
                        hasStocks = true;
                    }
                }

                if (!hasStocks) {
                    System.out.println("You do not own any stocks yet.");
                }

                System.out.println("Total Portfolio Value: Rs. "
                        + portfolioValue);
                break;

            case 5:
                System.out.println("Available Balance: Rs. " + balance);
                break;

            case 6: 
            	System.out.println("Total Profit/Loss: Rs. " + totalProfitLoss); 
            	break;
            	
            	
            case 7:
                System.out.println("\n--- Transaction History ---");

                if (history.isEmpty()) {
                    System.out.println("No transactions yet.");
                } else {
                    for (String transaction : history) {
                        System.out.println(transaction);
                    }
                }
                break;

            case 8:
                System.out.println("Thank you for using the platform!");
                break;
            
            }

        } while (choice != 8);        
        sc.close();
    }
}

