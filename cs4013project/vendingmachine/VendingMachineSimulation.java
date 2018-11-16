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
import javafx.scene.layout;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.FontPosture;

/**
 * VendingMachineSimulation
 */
public class VendingMachineSimulation extends Application
{
	public static void main(String[] args)
	{
		// Make the initial menu for the machine.
		VendingMachine machine = new VendingMachine();
		VendingMachineMenu menu = new VendingMachineMenu();

		//Run the machine
		menu.readFiles(machine);
		launch(args);
		menu.writeFiles(machine);
		
	}

	/**
	 * This is the start for the GUI interface.
	 */
	public void start(Stage primaryStage)
	{
		// Set up the grid pane
		GridPane choice = new GridPane();
		panel.setPadding(new Insets(20, 20, 20, 20));
		panel.setAlignment(Pos.CENTER);

		// Set up text
		Text question = new Text("Select your prefered UI");
		Text emptySpace = new Text(" ");
		question.setFont(Font.font("Comic Sans"));

		// Set up buttons
		Button cmdButton = new Button("Command Line Interface");
		Button guiButton = new Button("Graphical User Interface");

		// Populate the grid pane
		choice.add(question, 1, 0);
		choice.add(emptySpace, 1, 1);
		choice.add(cmdButton, 0, 2);
		choice.add(guiButton, 3, 2);

		// Set up the scene by putting things together
		Scene welcomeScreen = new Scene(choice, 300, 200);

		// Set up the action listener for the cmd button
		cmdButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				menu.runCMD();
			}
		});

		// Set up the action listener for the gui button
		guiButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				menu.runGUI();
			}
		});
		
		// Set up the Stage
		primaryStage.setTitle("Vending Machine UI Selection");
		primaryStage.setScene(welcomeScreen);

		// Display the results of all this code
		primaryStage.show();
	}
}