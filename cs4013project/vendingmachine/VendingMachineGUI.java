package cs4013project.vendingmachine;

import java.util.*;

import javax.lang.model.util.ElementScanner6;

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
		StackPane InstructionHolder = new StackPane();
		StackPane ShowProducts = new StackPane();
		StackPane InsertCoin = new StackPane();
		StackPane BuyProduct = new StackPane();
		StackPane AccessOperatorMode = new StackPane();
		StackPane AddProducts = new StackPane();
		StackPane RemoveCoins = new StackPane();
		StackPane Quit = new StackPane();

		// Set up text for the display.
		Text instruction = new Text("Please select what you would like to do?"); // main instruction
		//instruction.setFont(Font.font("Comic Sans MS")); Because I was told we're not doing comic sans :-(

		// Set up buttons.
		Button btShowProducts = new Button("Show products");
		Button btInsertCoin = new Button("Insert coin");
		Button btBuyProduct = new Button("Buy product");
		Button btAccessOperatorMode = new Button("Access operator mode");
		Button btAddProducts = new Button("AddProducts");
		Button btRemoveCoins = new Button("Remove coins");
		Button btQuit = new Button("Quit");

		// Put the buttons and the text in the stack panes.
		InstructionHolder.getChildren().add(instruction);
		ShowProducts.getChildren().add(btShowProducts);
		InsertCoin.getChildren().add(btInsertCoin);
		BuyProduct.getChildren().add(btBuyProduct);
		AccessOperatorMode.getChildren().add(btAccessOperatorMode);
		AddProducts.getChildren().add(btAddProducts);
		RemoveCoins.getChildren().add(btRemoveCoins);
		Quit.getChildren().add(btQuit);

		// Fill Up The grid.
		if(!menu.operator)
		{
			options.add(InstructionHolder, 1, 0);
			options.add(ShowProducts, 0, 1);
			options.add(Quit, 2, 1);
			options.add(InsertCoin, 0, 2);
			options.add(BuyProduct, 2, 2);
			options.add(AccessOperatorMode, 1, 2);
		}
		else
		{
			options.add(InstructionHolder, 1, 0);
			options.add(ShowProducts, 0, 1);
			options.add(Quit, 2, 1);
			options.add(InsertCoin, 0, 2);
			options.add(BuyProduct, 2, 2);
			options.add(AddProducts, 1, 2);
			options.add(RemoveCoins, 1, 3);
		}

		
		System.out.println("Got here!");
	}
}