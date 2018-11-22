package cs4013project.vendingmachine;

import java.util.*;

import javax.lang.model.util.ElementScanner6;

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

public class VendingMachineGUI extends VendingMachineSimulation implements VendingMachineMenu
{
	private boolean operator = false;

    /**
	 * @Szymon just testing if code crashes when it gets here...
	 */
	@Override
	public void run(VendingMachine machine)
	{
		boolean running = true;
		// Set the gui boolean to true.

		// Set up the stage.
		Stage mainMenu = new Stage();
		mainMenu.setTitle("Main Menu");

		//Set up a main pane
		VBox option = new VBox();
		option.setPadding(new Insets(50, 50, 50, 50));
		option.setSpacing(5);
		option.setAlignment(Pos.CENTER);

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
		option.getChildren().add(InstructionHolder);
		if(operator)
		{
			option.getChildren().add(InstructionHolder);
			option.getChildren().add(AddProducts);
			option.getChildren().add(RemoveCoins);
		}
		else
			option.getChildren().add(AccessOperatorMode);

			option.getChildren().add(ShowProducts);
			option.getChildren().add(BuyProduct);
			option.getChildren().add(InsertCoin);
			option.getChildren().add(Quit);

		btAccessOperatorMode.setOnAction(event -> 
		{
			mainMenu.close();
			menu.accessOperatorMode(machine.getOperatorPassword());
		});

		// Make the scene to put the pane into
		Scene screen = new Scene(option, 300, 200);
		// Populate the stage
		mainMenu.setScene(screen);
		mainMenu.show();
		
		System.out.println("Got here!");
	}

	@Override
	public String showOptions() {
		return null;
	}

	@Override
	public String showProducts(ArrayList<Product> products) {
		return VendingMachineMenu.super.showProducts(products);
	}

	@Override
	public void accessOperatorMode(String operatorPassword) {
		
	}

	@Override
	public void addNewProduct(VendingMachine machine) {
		
	}

	@Override
	public Object getChoice(Object[] choices) {
		return null;
	}
}