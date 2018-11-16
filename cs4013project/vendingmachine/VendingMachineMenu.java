package cs4013project.vendingmachine;

import java.util.*;

/**
 * VendingMachineMenu
 */
class VendingMachineMenu
{

	private boolean gui;
	private boolean operator;

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
			
			String command = in.nextLine();
			in.close();

			if (command.equalsIgnoreCase("S"))
			{
				for (Product p : machine.getProductTypes()){}
				
			}
			else if (command.equalsIgnoreCase("I"))
			{
				machine.addCoin((Coin)getChoice(coins));
			}
		}
	}

	private void readFiles()
	{

	}

	private void getChoice(ArrayList<Object> choices)
	{
		boolean choiceMade = false;
		while (!choiceMade)
		{
			for (int i = 0; i < choices.size(); i++)
			{
				if (!gui)
				{
					System.out.printf("%d) %s", i + 1, choices.get(i));
				}
				else
				{
					
				}
				Scanner in = new Scanner(System.in);
				String inputStr = in.nextLine();
				int input = (inputStr.matches("\\d+") ? Integer.parseInt(inputStr) : -1);
				in.close();
				
			}
		}
	}
}