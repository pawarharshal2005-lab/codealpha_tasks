package com.codealpha.stocktrading;


	public class Stock {

	    String name;
	    double price;
	    int quantity;

	    public Stock(String name, double price, int quantity) {
	        this.name = name;
	        this.price = price;
	        this.quantity = quantity;
	    }

	    public void displayStock() {
	        System.out.println("Stock Name: " + name);
	        System.out.println("Stock Price: Rs. " + price);
	        System.out.println("Available Quantity: " + quantity);
	    }
	}
	



