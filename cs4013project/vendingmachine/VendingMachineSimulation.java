package cs4013project.vendingmachine;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.*;
import java.util.Scanner;

/**
 * This is the executable class for the vending machine.
 * @author Dylan King		17197813
 * @author Szymon Sztyrmer	17200296
 */
public class VendingMachineSimulation extends Application
{
	// Make the initial menu for the machine.
	public static VendingMachine machine = new VendingMachine();
	public static VendingMachineMenu menu;

	public static void main(String[] args) throws IOException
	{
		boolean running = true;
		String input = "";
		Scanner in = new Scanner(System.in);

		while(running)
		{
			System.out.println("C)ommand Line Interface \nG)raphical User Interface \nQ)uit");
			input = in.nextLine().toUpperCase();
			if(input.equals("C"))
			{
				menu = new VendingMachineCMD();
				menu.readFiles(machine);
				running = false;
				menu.run(machine);
			}
			else if(input.equals("G"))
			{
				menu = new VendingMachineGUI();
				menu.readFiles(machine);
				running = false;
				launch(args);
			}
			else if(input.equals("Q"))
				running = false;
		}
		//launch(args);
		if(menu instanceof VendingMachineMenu)
		{
			menu.writeFiles(machine);
		}
		in.close();
		System.exit(0);
	}

	/**
	 * This is the start for the GUI interface.
	 */
	public void start(Stage primaryStage)
	{
		menu.run(machine);
	}
}
