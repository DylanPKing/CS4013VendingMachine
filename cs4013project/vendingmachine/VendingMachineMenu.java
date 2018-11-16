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

	private boolean gui;
	private boolean operator;
	private String operatorPassword;

	/**
	 * The commandline interface for the vending machine.
	 * @param machine the Vending Machine currently in use.
	 */
	void runCMD(VendingMachine machine)
	{
		boolean running = true;
		while (running)
		{
			Scanner in = new Scanner(System.in);
			System.out.println(showOptions());
			String command = in.nextLine();
			in.close();

			if (command.equalsIgnoreCase("S"))
				System.out.print(showProducts(machine.products));
			else if (command.equalsIgnoreCase("I"))
			{
				try
				{
					machine.addCoin((Coin)getChoice(machine.coins.toArray()));
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
					Product p = (Product)getChoice(machine.products.toArray());
					machine.buyProduct(p);
					System.out.println("Purchased: " + p);
				}
				catch (VendingException ex)
				{
					System.out.println(ex.getMessage());
				}
			}
			else if (command.equalsIgnoreCase("A") && !operator)
				accessOperatorMode();
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
		machine.coins = new ArrayList<Coin>();
		machine.products = new ArrayList<Product>();
		
		machine.prodQuantity = new ArrayList<Integer>();
		machine.coinQuantity = new ArrayList<Integer>();
		
		File product = new File("./Products.csv");
		File coin = new File("./Money.csv");
		File operator = new File("./Operators.csv");
		
		String[] lineFromFile;
		String productName = "";
		double productPrice = 0.0;
		int quantity = 0;
		
		String coinName = "";
		double coinValue = 0.0;
		
		String operatorName ="";
		String operatorPassword ="";
		
		//Reads products and adds all products to the products ArrayList, it adds the quantity to it's own product quantity ArrayList
		Scanner in = new Scanner(product);
		while(in.hasNext())
		{
			for(int i = 0; i < machine.products.size(); i++)
			{
				lineFromFile = (in.nextLine().split(","));
				productName = lineFromFile[0];
				productPrice = Double.parseDouble(lineFromFile[1]);
				quantity = Integer.parseInt(lineFromFile[2]);
				
				Product someProduct = new Product(productName, productPrice);
				machine.products.add(someProduct);
				
				machine.prodQuantity.add(quantity);
			}
		}
		in.close();
		
		//Reads coins and adds all coins to the coins ArrayList, it adds the quantity to it's own coin quantity ArrayList
		in = new Scanner(coin);
		while(in.hasNext())
		{
			for(int i = 0; i < machine.coins.size(); i++)
			{
				lineFromFile = (in.nextLine().split(","));
				coinName = lineFromFile[0];
				coinValue = Double.parseDouble(lineFromFile[1]);
				quantity = Integer.parseInt(lineFromFile[2]);
				
				Coin aCoin = new Coin(coinValue,coinName);
				machine.coins.add(aCoin);
				
				machine.coinQuantity.add(quantity);
			}
		}
		in.close();
		
		//Reads operators and separates the name and password
		in = new Scanner(operator);
		while(in.hasNext())
		{
			lineFromFile =(in.nextLine().split(","));
			operatorName = lineFromFile[0];
			operatorPassword = lineFromFile[1];
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
		
		FileWriter fr = new FileWriter(product, true);
		PrintWriter pr = new PrintWriter(fr);
		
		FileWriter fr1 = new FileWriter(coin, true);
		PrintWriter pr1 = new PrintWriter(fr1);
		
		
		for(int i =0; i < machine.products.size();i++)
		{
			pr.println(machine.products.get(i).getDescription() + "," +
					   machine.products.get(i).getPrice() + "," + 
					   machine.productQuantity.get(i));
			fr.close();
			pr.close();
		}
		
		for(int i = 0; i < machine.coins.size();i++)
		{
			pr1.print(machine.coins.get(i).getName() + "," +
					  machine.coins.get(i).getValue() + "," +
					  machine.coinQuantity.get(i));
			fr1.close();
			pr1.close();
		}
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
	private void accessOperatorMode()
	{
		if (!gui)
		{
			System.out.print("Enter operator password:\t");
		}
		else
		{

		}
		Scanner in = new Scanner(System.in);
		if (in.nextLine().equals(operatorPassword))
		{
			operator = true; 
		}
		in.close();
	}

	private void addNewProduct(VendingMachine machine)
	{
		if (!gui)
		{
			System.out.print("Enter description: ");
			Scanner in = new Scanner(System.in);
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
			in.close();
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
			{
				System.out.printf("%d) %s", i + 1, choices[i]);
			}
			else
			{
				
			}
		}
		Scanner in = new Scanner(System.in);
		String inputStr = in.nextLine();
		int input = (inputStr.matches("\\d+") ? Integer.parseInt(inputStr) : -1);
		in.close();
		if (input < 1 || input > choices.length)
		{
			throw new VendingException("Invalid choice.");
		}
		return choices[input];
	}
}