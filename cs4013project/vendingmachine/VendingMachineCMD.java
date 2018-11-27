package cs4013project.vendingmachine;

import java.util.*;
import java.io.*;

/**
 * This is the command line interface for the vending machine.
 * @author Dylan King 	17197813
 * @author Brian Malone 17198178
 */
public class VendingMachineCMD implements VendingMachineMenu
{

	boolean gui;
	boolean operator = false;
	private Scanner in = new Scanner(System.in);

	/**
	 * The commandline interface for the vending machine.
	 * @param machine the Vending Machine currently in use.
	 */
	@Override
	public void run(VendingMachine machine)
	{
		boolean running = true;
		while (running)
		{
			System.out.println(showOptions());
			String command = in.nextLine();

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
			{
				running = false;
			}
		}
	}

	/**
	 * Shows the options avaialable for the current user.
	 * @return the available options
	 */
	public String showOptions()
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

	@Override
	public void accessOperatorMode(String operatorPassword)
	{
		System.out.print("Enter operator password:\t");
		if (in.nextLine().equals(operatorPassword))
			operator = true; 
	}

	@Override
	public void addNewProduct(VendingMachine machine)
	{
		System.out.print("Enter description: ");
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
	}

	/**
	 * Takes in the array of either products or coins, as specified,
	 * an prompts the user for their choice.
	 * @param choices Array of products/coins
	 * @return product or coin chosen.
	 */
	public Object getChoice(Object[] choices)
	{
		for (int i = 0; i < choices.length; i++)
			System.out.printf("%d) %s\n", i + 1, choices[i]);
		String inputStr = in.nextLine();
		int input = (inputStr.matches("\\d+") ? Integer.parseInt(inputStr) : -1);
		if (input < 0 || input > choices.length)
			throw new VendingException("Invalid choice.");
		return choices[input - 1];
	}
}