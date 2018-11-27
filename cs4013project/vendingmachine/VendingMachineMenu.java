package cs4013project.vendingmachine;

import java.util.*;
import java.io.*;

/**
 * VendingMachineMenu
 * This interface is the base for both the VendingMachineCMD and VendimgMachineGUI
 * classes, and contains all common methods between the two classes, as well as
 * method headers that both will use.
 * @author Dylan King 17197813
 * @author Brian Malone 17198178
 */
interface VendingMachineMenu 
{

	/**
	 * The interface for the vending machine.
	 * @param machine the Vending Machine currently in use.
	 */
	abstract void run(VendingMachine machine);

	/**
	 * Reads from the Products, Money and Operator files.
	 * Adds products and coins to their arraylists as well as adding their quantities to their own arraylists.
	 * Separates operator details for use in another method.
	 * @param machine the Vending Machine currently in use.
	 * @throws IOException
	 */
	default void readFiles(VendingMachine machine) throws IOException
	{
		ArrayList<Coin> coins = machine.getCoins();
		ArrayList<Product> products = machine.getProducts();
		
		ArrayList<Integer> prodQuantity = machine.getProdQuantity();
		ArrayList<Integer> coinQuantity = machine.getCoinQuantity();
		
		File product = new File("./Products.csv");
		File coin = new File("./Money.csv");
		File operator = new File("./Operators.csv");
		
		String[] lineFromFile;
		String productName = "";
		double productPrice = 0.0;
		int quantity = 0;
		
		String coinName = "";
		double coinValue = 0.0;
		
		
		//Reads products and adds all products to the products ArrayList, it adds the quantity to it's own product quantity ArrayList
		Scanner in = new Scanner(product);
		while(in.hasNext())
		{
			lineFromFile = (in.nextLine().split(","));
			productName = lineFromFile[0];
			productPrice = Double.parseDouble(lineFromFile[1]);
			quantity = Integer.parseInt(lineFromFile[2]);
				
			Product someProduct = new Product(productName, productPrice);
			products.add(someProduct);
				
			prodQuantity.add(quantity);
		}
		in.close();
		
		//Reads coins and adds all coins to the coins ArrayList, it adds the quantity to it's own coin quantity ArrayList
		in = new Scanner(coin);
		while(in.hasNext())
		{
			lineFromFile = (in.nextLine().split(","));
			coinName = lineFromFile[0];
			coinValue = Double.parseDouble(lineFromFile[1]);
			quantity = Integer.parseInt(lineFromFile[2]);
				
			Coin aCoin = new Coin(coinValue,coinName);
			coins.add(aCoin);
				
			coinQuantity.add(quantity);
		}
		in.close();
		
		//Reads operators and separates the name and password
		in = new Scanner(operator);
		while(in.hasNext())
		{
			lineFromFile =(in.nextLine().split(","));
			machine.setOperatorPassword(lineFromFile[1]);
		}
		in.close();
	}

	/**
	 * Writes the product objects and their quantities to file, as well as writing the
	 * coin objects and their quantities to file.
	 * @param machine the Vending Machine currently in use.
	 * @throws IOException
	 */
	default void writeFiles(VendingMachine machine) throws IOException
	{
		File coin = new File("./Money.csv");
		File product = new File("./Products.csv");
		
		FileWriter fr = new FileWriter(product, false);
		PrintWriter pr = new PrintWriter(fr);
		
		FileWriter fr1 = new FileWriter(coin, false);
		PrintWriter pr1 = new PrintWriter(fr1);
		
		ArrayList<Product> products = machine.getProducts();
		ArrayList<Integer> prodQuantity = machine.getProdQuantity();
		
		for(int i =0; i < products.size();i++)
		{
			pr.println(products.get(i).getDescription() + "," +
					   products.get(i).getPrice() + "," + 
					   prodQuantity.get(i));
		}
		fr.close();
		pr.close();
		
		ArrayList<Coin> coins = machine.getCoins();
		ArrayList<Integer> coinQuantity = machine.getCoinQuantity();

		for(int i = 0; i < coins.size();i++)
		{
			pr1.println(coins.get(i).getName() + "," +
					  coins.get(i).getValue() + "," +
					  coinQuantity.get(i));
		}
		fr1.close();
		pr1.close();
	}

	/**
	 * Shows the currently available products for sale
	 * @param products the ArrayList of currently avaialable products
	 * @return the currently avialable products as a String.
	 */
	default String showProducts(ArrayList<Product> products)
	{
		String output = "";
		for (Product p : products)
			output += p + "\n";
		return output;
	}

	/**
	 * Offers a password input for the user to access operator mode.]
	 */
	abstract void accessOperatorMode(String operatorPassword);

	/**
	 * Adds a new product to the vending machine, only accessible by the administrator
	 * @param machine the vending machine the product is being added to.
	 */
	abstract void addNewProduct(VendingMachine machine);

	/**
	 * Prints the amount of coins removed from the machine,
	 * and calls the machines removeMoney method
	 * @param machine the vending machine in use
	 * @return The sum of the coins removed.
	 */
	default String removeCurrentCoins(VendingMachine machine)
	{
		return "Removed: " + machine.removeMoney();
	}
}