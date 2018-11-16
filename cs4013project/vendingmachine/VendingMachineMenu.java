package cs4013project.vendingmachine;

import java.util.*;

/**
 * VendingMachineMenu
 */
class VendingMachineMenu
{

	private boolean gui;
	private boolean operator;
	private String operatorPassword;

	VendingMachineMenu()
	{
		readFiles();
	}
	/**
	 * The commandline interface for the vending machine.
	 * @param machine the Vending
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

	private void readFiles()
	{

	}

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

	private String showProducts(ArrayList<Product> products)
	{
		String output = "";
		for (Product p : products)
			output += p + "\n";
		return output;
	}

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

	private String removeCurrentCoins(VendingMachine machine)
	{
		return "Removed: " + machine.removeMoney();
	}

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