package cs4013project.vendingmachine;

import java.util.*;
import java.io.*;

/**
 * VendingMachineMenu
 * @author Dylan King 17197813
 * @author Brian Malone 17198178
 */
class VendingMachineMenu
{

	boolean gui;
	boolean operator;
	private Scanner in = new Scanner(System.in);

	/**
	 * The commandline interface for the vending machine.
	 * @param machine the Vending Machine currently in use.
	 */
	void runCMD(VendingMachine machine)
	{
		boolean running = true;
		while (running)
		{
			//Scanner in = new Scanner(System.in);
			System.out.println(showOptions());
			String command = in.nextLine();
			//in.close();

			if (command.equalsIgnoreCase("S"))
				System.out.print(showProducts(machine.getProducts()));
			else if (command.equalsIgnoreCase("I"))
			{
				try
				{
					machine.addCoin((Coin)getChoice(machine.getCoins().toArray()));
				}
				catch (VendingException ex)
				{
					System.out.println(ex.getMessage());
				}
			}
			else if (command.equalsIgnoreCase("B"))
			{
				try
				{
					Product p = (Product)getChoice(machine.getProducts().toArray());
					machine.buyProduct(p);
					System.out.println("Purchased: " + p);
				}
				catch (VendingException ex)
				{
					System.out.println(ex.getMessage());
				}
			}
			else if (command.equalsIgnoreCase("A") && !operator)
				accessOperatorMode(machine.getOperatorPassword());
			else if (command.equalsIgnoreCase("A") && operator)
				addNewProduct(machine);
			else if (command.equalsIgnoreCase("R") && operator)
				System.out.println(removeCurrentCoins(machine));
			else if (command.equalsIgnoreCase("Q"))
				running = false;
		}
	}

	/**
	 * Reads from the Products, Money and Operator files.
	 * Adds products and coins to their arraylists as well as adding their quantities to their own arraylists.
	 * Separates operator details for use in another method.
	 * @param machine the Vending Machine currently in use.
	 * @throws IOException
	 */
	void readFiles(VendingMachine machine) throws IOException
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
	void writeFiles(VendingMachine machine) throws IOException
	{
		File coin = new File("./Coins.csv");
		File product = new File("./Products.csv");
		
		FileWriter fr = new FileWriter(product);
		PrintWriter pr = new PrintWriter(fr);
		
		FileWriter fr1 = new FileWriter(coin);
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
			pr1.print(coins.get(i).getName() + "," +
					  coins.get(i).getValue() + "," +
					  coinQuantity.get(i));
		}
		fr1.close();
		pr1.close();
	}

	/**
	 * Shows the options avaialable for the current user.
	 * @return the available options
	 */
	private String showOptions()
	{
		String output = "(S)how products\n" +
						"(I)nsert coin\n" +
						"(B)uy product\n";
		if (operator)
		{
			output += "(A)dd products\n" +
					  "(R)emove coins\n";
		}
		else
		{
			output += "(A)ccess operator mode\n";
		}
		return output + "(Q)uit";
	}

	/**
	 * Shows the currently available products for sale
	 * @param products the ArrayList of currently avaialable products
	 * @return the currently avialable products as a String.
	 */
	private String showProducts(ArrayList<Product> products)
	{
		String output = "";
		for (Product p : products)
			output += p + "\n";
		return output;
	}

	/**
	 * Offers a password input for the user to access operator mode.]
	 */
	private void accessOperatorMode(String operatorPassword)
	{
		if (!gui)
			System.out.print("Enter operator password:\t");
		else
		{

		}
		//Scanner in = new Scanner(System.in);
		if (in.nextLine().equals(operatorPassword))
			operator = true; 
		//in.close();
	}

	private void addNewProduct(VendingMachine machine)
	{
		if (!gui)
		{
			System.out.print("Enter description: ");
			//Scanner in = new Scanner(System.in);
			String description = in.nextLine();
			boolean valid = false;
			while (!valid)
			{
				System.out.print("Enter price: ");
				String input = in.nextLine();
				if (input.matches("\\d*.?\\d+"))
				{
					double price = Double.parseDouble(input);
					System.out.print("Enter quantity: ");
					input = in.nextLine();
					if (input.matches("\\d+"))
					{
						int quantity = Integer.parseInt(input);
						if (quantity > 0)
						{
							machine.addProduct(new Product(description, price), quantity);
							valid = true;
						}
					}
				}
				else
					System.out.println("Invalid price.");
			}
			//in.close();
		}
		else
		{

		}
	}

	/**
	 * Prints the amount of coins removed from the machine,
	 * and calls the machines removeMoney method
	 * @param machine the vending machine in use
	 * @return The sum of the coins removed.
	 */
	private String removeCurrentCoins(VendingMachine machine)
	{
		return "Removed: " + machine.removeMoney();
	}

	/**
	 * Prints the available choices of the passed in Array
	 * @param choices Object array of the coins/products
	 * @return the choice made by the user.
	 */
	private Object getChoice(Object[] choices)
	{
		for (int i = 0; i < choices.length; i++)
		{
			if (!gui)
				System.out.printf("%d) %s\n", i + 1, choices[i]);
			else
			{
				
			}
		}
		//Scanner in = new Scanner(System.in);
		String inputStr = in.nextLine();
		int input = (inputStr.matches("\\d+") ? Integer.parseInt(inputStr) : -1);
		//in.close();
		if (input < 0 || input > choices.length)
			throw new VendingException("Invalid choice.");
		return choices[input - 1];
	}
}