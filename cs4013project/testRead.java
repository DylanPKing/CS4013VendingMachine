//read from product, money into their own arraylists and their quantity arraylists //fuk operators

import java.util.*;
import java.io.*;
public class testRead throws IOException
{
		ArrayList<Coin> coins = new ArrayList<Coin>();
		ArrayList<Product> products = new ArrayList<Product>();
		
		ArrayList<Integer> productQuantity = new ArrayList<Integer>();
		ArrayList<Integer> coinQuantity = new ArrayList<Integer>();
		
		File product = new File("products.csv");
		File coin = new File("coins.csv");
		File operator = new File("operators.csv");
		
		String [] lineFromFile;
		String productName = "";
		double productPrice = 0.0;
		int quantity = 0;
		
		String coinName = "";
		double coinValue = 0.0;
		
		String operatorName ="";
		String operatorPassword ="";
		
		//product: name,price,quantity
		//coins:name,double value,quantity
		
		//Reads products and adds all products to the products ArrayList, it adds the quantity to it's own product quantity ArrayList
		Scanner in = new Scanner(product);
		while(in.hasNext())
		{
			for(int i = 0; i<products.size(); i++)
			{
				lineFromFile = (in.nextLine().split(","));
				productName = lineFromFile[0];
				productPrice = Double.parseDouble(lineFromFile[1]);
				quantity = Integer.parseInt(lineFromFile[2]);
				
				Product someProduct = new Product(productName,productPrice);
				products.add(someProduct);
				
				productQuantity.add(quantity);
			}
		}
		in.close();
		
		//Reads coins and adds all coins to the coins ArrayList, it adds the quantity to it's own coin quantity ArrayList
		in = new Scanner(coin);
		while(in.hasNext())
		{
			for(int i = 0; i<coins.size(); i++)
			{
				lineFromFile = (in.nextLine().split(","));
				coinName = lineFromFile[0];
				coinValue = Double.parseDouble(lineFromFile[1]);
				quantity = Integer.parseInt(lineFromFile[2]);
				
				Coin aCoin = new Coin(coinValue,coinName);
				coins.add(aCoin);
				
				coinQuantity.add(quantity);
			}
		}
		
		//Reads operators and separates the name and password
		in = new Scanner(operator);
		while(in.hasNext())
		{
			lineFromFile =(in.nextLine().split(","));
			operatorName = lineFromFile[0];
			operatorPassword = lineFromFile[1]
		}
		
		/*
		for(int i = 0; i<5; i++)
		{
		System.out.print("Products: " + products.get(i) + " Quantity: " + productQuantity.get(i) + "\n" + "Coins: " + coins.get(i) + "Quantity: " + coinQuantity.get(i) + "\n");
		}
		*/
}