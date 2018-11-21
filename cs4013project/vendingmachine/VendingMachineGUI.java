package cs4013project.vendingmachine;

import java.util.*;

import cs4013project.vendingmachine.VendingMachineSimulation;

import java.io.*;
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

public class VendingMachineGUI extends VendingMachineSimulation
{
    /**
	 * @Szymon just testing if code crashes when it gets here...
	 */
	void runGUI(VendingMachineMenu menu)
	{
		boolean running = true;
		// Set the gui boolean to true.
		menu.gui = true;

		// Set up the stage.
		Stage mainMenu = new Stage();
		mainMenu.setTitle("Main Menu");

		//Set up a main pane
		GridPane options = new GridPane();
		options.setPadding(new Insets(5, 5, 5, 5));
		options.setAlignment(Pos.CENTER);

		//Set up a bunch of small panes for formating.
		StackPane instructionHolder = new StackPane();

		// Set up text for the display.
		Text instruction = new Text("Please select what you would like to do?"); // main instruction
		//instruction.setFont(Font.font("Comic Sans MS")); Because I was told we're not doing comic sans :-(

		// Set up buttons.

		
		System.out.println("Got here!");
	}
}