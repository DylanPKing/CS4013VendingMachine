package 

import java.util.Scanner;

/**
 * VendingMachineMenu
 */
public class VendingMachineMenu
{

	private ArrayList<Coin> coins;
	private ArrayList<Integer> coinQuantity;
	private ArrayList<Coin> currentCoins;
	private ArrayList<Integer> currentCoinsQuantity;
	private boolean gui;
	private boolean operator;

	public VendingMachineMenu()
	{
		readFiles();
	}
	/**
	 * The commandline interface for the vending machine.
	 * @param machine the Vending
	 */
	public void runCMD(VendingMachine machine)
	{
		boolean running = true;
		while (running)
		{
			Scanner in = new Scanner(System.in);
			
			String command = in.nextLine();
			in.close();

			if (command.equalsIgnoreCase("S"))
			{
				for (Product p : machine.getProductTypes())
				
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
				if (input == -1)
				{
					throw new 
				}
			}
		}
	}
}