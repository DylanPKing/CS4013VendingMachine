import java.util.*;
import java.io.*;
public class testWrite
{
	public static void main(String args[]) throws IOException
	{
		//write all the details of the arraylists back to the file
		//testRead();
		
		ArrayList<Coin> coins = new ArrayList<Coin>();
		ArrayList<Product> products = new ArrayList<Product>();
		
		ArrayList<Integer> productQuantity = new ArrayList<Integer>();
		ArrayList<Integer> coinQuantity = new ArrayList<Integer>();
		
		Product someProduct = new Product("AHand",1.0);
		Coin someCoin = new Coin(1000000,"oneMillion");
		
		products.add(someProduct);
		coins.add(someCoin);
		
		productQuantity.add(12);
		coinQuantity.add(15);
		
		File coin = new File("coins.csv");
		File product = new File("products.csv");
		
		FileWriter fr = new FileWriter(product,true);
		PrintWriter pr = new PrintWriter(fr);
		
		FileWriter fr1 = new FileWriter(coin,true);
		PrintWriter pr1 = new PrintWriter(fr1);
		
		for(int i =0; i<products.size();i++)
		{
			pr.println(products.get(i).getDescription() + "," + products.get(i).getPrice()+ "," + productQuantity.get(i));
			fr.close();
			pr.close();
		}
		
		for(int i = 0; i<coins.size();i++)
		{
			pr1.print(coins.get(i).getName() + "," + coins.get(i).getValue() + "," + coinQuantity.get(i));
			fr1.close();
			pr1.close();
		}
	}
}