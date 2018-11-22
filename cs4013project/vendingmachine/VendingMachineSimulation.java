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

/**
 * VendingMachineSimulation
 */
public class VendingMachineSimulation extends Application
{
	// Make the initial menu for the machine.
	public static VendingMachine machine = new VendingMachine();
	public static VendingMachineMenu menu;

	public static void main(String[] args) throws IOException
	{
		//Run the machine
		launch(args);
		menu.writeFiles(machine);
	}

	/**
	 * This is the start for the GUI interface.
	 * @author Szymon Sztyrmer
	 */
	public void start(Stage primaryStage)
	{
		// Set up the grid pane
		GridPane choice = new GridPane();
		choice.setPadding(new Insets(20, 20, 20, 20));
		choice.setAlignment(Pos.CENTER);

		// Set up text
		Text question = new Text("Select your prefered UI");
		Text emptySpace = new Text(" ");
		//question.setFont(Font.font("Comic Sans MS"));

		// Set up buttons
		Button cmdButton = new Button("Command Line Interface");
		Button guiButton = new Button("Graphical User Interface");
	    //cmdButton.setFont(Font.font("Comic Sans MS"));
		//guiButton.setFont(Font.font("Comic Sans MS"));

		// Make the buttons wide
		cmdButton.setMaxWidth(Double.MAX_VALUE);
		guiButton.setMaxWidth(Double.MAX_VALUE);

		// Populate the grid pane
		choice.add(question, 1, 0);
		choice.add(emptySpace, 1, 1);
		choice.add(cmdButton, 1, 2);
		//choice.add(emptySpace, 1, 3);
		choice.add(guiButton, 1, 3);

		// Set up the scene by putting things together
		Scene welcomeScreen = new Scene(choice, 300, 200);

		// Set up the action listener for the cmd button
		cmdButton.setOnAction(event ->
		{
			primaryStage.close();
			menu = new VendingMachineCMD();
			try
			{
				menu.readFiles(machine);
				menu.run(machine);
			}
			catch (IOException e)
			{
				System.out.print("Shit");
			}
		});

		// Set up the action listener for the gui button
		guiButton.setOnAction(event ->
		{
			primaryStage.close();
			menu = new VendingMachineGUI();
			try
			{
				menu.readFiles(machine);
				menu.run(machine);
			}
			catch (IOException e)
			{
				System.out.print("Shit");
			}
		});
		
		// Set up the Stage
		primaryStage.setTitle("Vending Machine UI Selection");
		primaryStage.setScene(welcomeScreen);

		// Display the results of all this code
		primaryStage.show();
	}
}