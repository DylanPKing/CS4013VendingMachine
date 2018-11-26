package cs4013project.vendingmachine;

import java.util.*;

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
import javafx.scene.control.TextField;
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
	private Object item;
    /**
	 * @Szymon just testing if code crashes when it gets here...
	 */
	@Override
	public void run(VendingMachine machine)
	{
		boolean firstRun = false;
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
			option.getChildren().add(AddProducts);
			option.getChildren().add(RemoveCoins);
		}
		else
			option.getChildren().add(AccessOperatorMode);

			option.getChildren().add(ShowProducts);
			option.getChildren().add(BuyProduct);
			option.getChildren().add(InsertCoin);
			option.getChildren().add(Quit);

		// Action listener for the operator mode button
		btAccessOperatorMode.setOnAction(event -> 
		{
			mainMenu.close();
			accessOperatorMode(machine.getOperatorPassword());
		});

		// Action listener for the Add Products button
		btAddProducts.setOnAction(event -> 
		{
			mainMenu.close();
			addNewProduct(machine);
		});

		// Action listener for the quit button.
		btQuit.setOnAction(event ->
		{
			//menu.writeFiles(machine);
			mainMenu.close();
		});

		// Action listener for the show products button
		btShowProducts.setOnAction(event ->
		{
			mainMenu.close();
			showStock();
		});

		// Action listenert for the insert coin button
		btInsertCoin.setOnAction(event ->
		{
			mainMenu.close();
			coinAdder();
		});

		// Action listener for the buy product button
		btBuyProduct.setOnAction(event ->
		{
			mainMenu.close();
			buy();
		});

		btRemoveCoins.setOnAction(event ->
		{
			mainMenu.close();
			remove(machine);
		});

		// Make the scene to put the pane into
		Scene screen = new Scene(option, 300, 400);
		// Populate the stage
		mainMenu.setScene(screen);
		mainMenu.show();
	}

	@Override
	public String showOptions() 
	{
		return null;
	}

	@Override
	public String showProducts(ArrayList<Product> products) 
	{
		return VendingMachineMenu.super.showProducts(products);
	}

	/**
	 * This method allows the user to enter operator mode.
	 * @author Szymon Sztyrmer
	 * @param operatorPassword
	 */
	@Override
	public void accessOperatorMode(String operatorPassword) 
	{
		// Make the initial stage for the text entering mechanism.
		Stage enterPassword = new Stage();

		// The panes imma be using.
		HBox fullPane = new HBox();
		StackPane information = new StackPane();
		StackPane enterText = new StackPane();
		StackPane ok = new StackPane();
		StackPane message = new StackPane();

		// Set up of HBox
		//fullPane.setAlignment(Pos.CENTER);
		fullPane.setSpacing(5);

		// The actual text
		Text txtInformation = new Text("Please enter the operator password");

		// The typing field
		TextField txtEnterText = new TextField();
		txtEnterText.setEditable(true);

		// a Label
		Label lbMessage = new Label("Password hasn't been entered");

		// The button
		Button btOk = new Button("Ok");

		// Action Listener
		
		txtEnterText.focusedProperty().addListener((arg0, oldValue, newValue) ->
		{
			if(!newValue)
			{
				if(!txtEnterText.getText().matches(operatorPassword))
					lbMessage.setText("You're obviously not an operator"); // change this to a label
				else
					{
						operator = true;
						enterPassword.close();
						run(machine);
					}
			}
		});
		
		// The assembly
		information.getChildren().add(txtInformation);
		enterText.getChildren().add(txtEnterText);
		ok.getChildren().add(btOk);
		message.getChildren().add(lbMessage);
		fullPane.getChildren().addAll(information, enterText, ok, message);

		// Time for scene
		Scene screen = new Scene(fullPane, 700, 100);

		// Put together
		enterPassword.setTitle("Operator Menu");
		enterPassword.setScene(screen);
		enterPassword.show();

	}

	/**
	 * This method displays the items currently available for purchase
	 * @author Szymon Sztyrmer
	 */
	public void showStock()
	{
		// Make a new stage
		Stage stock = new Stage();

		// Make some panes
		VBox list = new VBox();
		list.setSpacing(5);
		list.setAlignment(Pos.CENTER);
		StackPane products = new StackPane();
		StackPane ok = new StackPane();

		// Make some buttons.
		Button btOk = new Button("Ok");

		// Action listener for the ok button
		btOk.setOnAction(event ->
		{
			stock.close();
			run(machine);
		});

		// Labels maybe?
		Label lbProducts = new Label(showProducts(machine.getProducts()));

		//Asseble panes
		products.getChildren().add(lbProducts);
		ok.getChildren().add(btOk);
		list.getChildren().addAll(products, ok);

		// Scene making 101
		Scene screen = new Scene(list, 300, 300);

		// Add to stage and display
		stock.setTitle("Products");
		stock.setScene(screen);
		stock.show();
	}

	/**
	 * This method allows the user to add a coin to the machine... Hopefully
	 */
	public void coinAdder()
	{
		int number = 1;
		String listOfItems = "";
		String pattern = "[0-9]{1,}";
		Object [] choices = machine.getCoins().toArray();
		// Make a stage
		Stage choiceList = new Stage();

		// Make some panes
		VBox list = new VBox();
		list.setSpacing(5);
		list.setAlignment(Pos.CENTER);
		StackPane choice = new StackPane();
		StackPane ok = new StackPane();
		StackPane enterItem = new StackPane();
		StackPane error = new StackPane();

		// Make a normal label
		Label lbError = new Label();

		// Make my big label
		Label bigLabel = new Label("");

		for (int i = 0; i < choices.length; i++)
		{
			listOfItems += (number) + ") " + choices[i].toString() + "\n";
			number++;
		}

		// Fill the label
		bigLabel.setText(listOfItems);

		// Make a universal button
		Button btOk = new Button("Ok");

		// Make a text field
		TextField txtEnterItem = new TextField("Enter the number here");

		// Action listener for the text field button combo
		txtEnterItem.focusedProperty().addListener((arg0, oldValue, newValue) ->
		{
			if(!newValue)
			{
				if(!txtEnterItem.getText().matches(pattern))
					lbError.setText("Stop that, Dingus");
				else
				{
					if(1 > Integer.parseInt(txtEnterItem.getText()) || Integer.parseInt(txtEnterItem.getText()) > choices.length)
						lbError.setText("Nice try, Dingus");
					else
					{
						choiceList.close();
						machine.addCoin((Coin) choices[Integer.parseInt(txtEnterItem.getText()) - 1]);
						run(machine);
					}
				}
			}
		});

		// Fill panes
		choice.getChildren().add(bigLabel);
		enterItem.getChildren().add(txtEnterItem);
		ok.getChildren().add(btOk);
		error.getChildren().add(lbError);
		list.getChildren().addAll(choice, enterItem, error, ok);

		// Make a scene
		Scene screen = new Scene(list, 200, 300);

		// Set up the stage
		choiceList.setTitle("Choose Item");
		choiceList.setScene(screen);
		choiceList.show();
	}

	@Override
	public void addNewProduct(VendingMachine machine) 
	{
		// Make a stage
		Stage addProduct = new Stage();

		//Add panes
		VBox fullPane = new VBox();
		StackPane information = new StackPane();
		StackPane enterDescription = new StackPane();
		StackPane enterPrice = new StackPane();
		StackPane enterQuantity = new StackPane();
		StackPane add = new StackPane();
		StackPane description = new StackPane();
		StackPane price = new StackPane();
		StackPane quantity = new StackPane();

		fullPane.setSpacing(5);

		Text txtInfo = new Text("Enter the product you wish to add");

		Label lbDescription = new Label("\nDescription:");
		Label lbPrice = new Label("\nPrice:");
		Label lbQuantity = new Label("\nQuantity:");

		TextField txtEnterDesc = new TextField();
		txtEnterDesc.setEditable(true);

		TextField txtEnterPrice = new TextField();
		txtEnterPrice.setEditable(true);

		TextField txtEnterQuant = new TextField();
		txtEnterQuant.setEditable(true);

		Button btAdd = new Button("Add");

		btAdd.setOnAction(event ->
		{
			if(txtEnterPrice.getText().matches("\\d*.?\\d+") && txtEnterQuant.getText().matches("\\d+"))
			{
				ArrayList<Product> existingProducts = machine.getProducts();
				ArrayList<Integer> existingProdQuant = machine.getProdQuantity();
				String prodDesc = txtEnterDesc.getText();
				String prodPrice = txtEnterPrice.getText();
				Double priceVal = Double.parseDouble(prodPrice);
				Product added = new Product(prodDesc, priceVal);
				int addQuantity = Integer.parseInt(txtEnterQuant.getText());		

				if(existingProducts.contains(added))
				{
					for(int i = 0; i < existingProducts.size(); i++)
					{
						if(existingProducts.get(i).equals(added))
						{
							existingProdQuant.set(i, (existingProdQuant.get(i) + addQuantity));
							addProduct.close();
							run(machine);
						}
					}
				}
				else
				{			
					existingProducts.add(added);				
					existingProdQuant.add(addQuantity);
					addProduct.close();
					run(machine);
				}
			}
		});

		information.getChildren().add(txtInfo);
		description.getChildren().add(lbDescription);
		enterDescription.getChildren().add(txtEnterDesc);
		price.getChildren().add(lbPrice);
		enterPrice.getChildren().add(txtEnterPrice);
		quantity.getChildren().add(lbQuantity);
		enterQuantity.getChildren().add(txtEnterQuant);
		add.getChildren().add(btAdd);
		fullPane.getChildren().addAll(information, description, enterDescription, price, enterPrice, quantity, enterQuantity, add);

		Scene screen = new Scene(fullPane, 300, 350);

		addProduct.setTitle("Add Product");
		addProduct.setScene(screen);
		addProduct.show();
	}

	public void remove(VendingMachine machine)
	{
		Stage remove = new Stage();

		HBox fullPane = new HBox();
		StackPane removed = new StackPane();
		Label lbremove = new Label(removeCurrentCoins(machine));

		removed.getChildren().add(lbremove);
		fullPane.getChildren().addAll(removed);

		Scene screen = new Scene(fullPane, 200, 200);

		remove.setTitle("Remove coins");
		remove.setScene(screen);
		remove.show();
	}

	public void buy()
	{
		int number = 1;
		String listOfItems = "";
		String pattern = "[0-9]{1,}";
		Object [] choices = machine.getProducts().toArray();
		// Make a stage
		Stage choiceList = new Stage();

		// Make some panes
		VBox list = new VBox();
		list.setSpacing(5);
		list.setAlignment(Pos.CENTER);
		StackPane choice = new StackPane();
		StackPane ok = new StackPane();
		StackPane enterItem = new StackPane();
		StackPane error = new StackPane();

		// Make a normal label
		Label lbError = new Label();

		// Make my big label
		Label bigLabel = new Label("");

		for (int i = 0; i < choices.length; i++)
		{
			listOfItems += (number) + ") " + choices[i].toString() + "\n";
			number++;
		}

		// Fill the label
		bigLabel.setText(listOfItems);

		// Make a universal button
		Button btOk = new Button("Ok");

		// Make a text field
		TextField txtEnterItem = new TextField("Enter the number here");

		// Action listener for the text field button combo
		txtEnterItem.focusedProperty().addListener((arg0, oldValue, newValue) ->
		{
			if(!newValue)
			{
				if(!txtEnterItem.getText().matches(pattern))
					lbError.setText("Stop that, Dingus");
				else
				{
					if(1 > Integer.parseInt(txtEnterItem.getText()) || Integer.parseInt(txtEnterItem.getText()) > choices.length)
						lbError.setText("Nice try, Dingus");
					else
					{
						choiceList.close();
						try
						{
							machine.buyProduct((Product) choices[Integer.parseInt(txtEnterItem.getText()) - 1]);
							choiceList.close();
							run(machine);
						}
						catch(VendingException ex)
						{
							choiceList.close();
							showError(ex);
						}
					}
				}
			}
		});

		// Fill panes
		choice.getChildren().add(bigLabel);
		enterItem.getChildren().add(txtEnterItem);
		ok.getChildren().add(btOk);
		error.getChildren().add(lbError);
		list.getChildren().addAll(choice, enterItem, error, ok);

		// Make a scene
		Scene screen = new Scene(list, 200, 300);

		// Set up the stage
		choiceList.setTitle("Choose Item");
		choiceList.setScene(screen);
		choiceList.show();
	}

	public Object getItem(Object[] choices, int enteredNumber)
	{
		int position = 0;
		for(int i = 0; i < choices.length; i++)
		{
			if(i == enteredNumber - 1)
			position = i;
		}
		return choices[position];
	}

	public void showError(VendingException ex)
	{
		// Set up the stage
		Stage error = new Stage();
		//Stage.setTitle("Error");

		// Set up the panes
		VBox errorContents = new VBox();
		StackPane message = new StackPane();
		StackPane ok = new StackPane();

		// Set up the text
		Text txtMessage = new Text(ex.getMessage());

		// Set up the button
		Button btOk = new Button("Ok");

		// Set up the action listener
		btOk.setOnAction(event -> 
		{
			error.close();
			run(machine);
		});

		//Assemble
		message.getChildren().add(txtMessage);
		ok.getChildren().add(btOk);
		errorContents.getChildren().addAll(message, ok);

		// Make the scene
		Scene screen = new Scene(errorContents, 100, 45);

		// Fill stage
		error.setScene(screen);
		error.show();
	}
}