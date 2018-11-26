package cs4013project.vendingmachine;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;
import java.io.*;
import java.util.Scanner;

/**
 * VendingMachineSimulation
 * @author Dylan King
 * @author Szymon Sztyrmer
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
				menu.run(machine);
				running = false;
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
			menu.writeFiles(machine);
		in.close();
	}

	/**
	 * This is the start for the GUI interface.
	 */
	public void start(Stage primaryStage)
	{
		menu.run(machine);
	}
}
