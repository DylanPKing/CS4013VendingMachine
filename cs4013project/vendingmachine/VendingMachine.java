package cs4013project.vendingmachine;

import java.util.*;

/**
 * @author Louise Madden 17198232
 */
class VendingMachine
{
    ArrayList<Product> products;
    ArrayList<Integer> prodQuantity;
    ArrayList<Coin> coins;
    ArrayList<Integer> coinQuantity;
    ArrayList<Coin> currentCoins;
    double currentBalance;
    /**
     * Constructor
     */
    VendingMachine() 
    {

    }

    /**
     * Called when the user inserts a coin to the machine
     * The coin that is added is added to currentCoins to be used later
     * Adds the value of the coin to currentBalance
     */
    void addCoin(Coin inserted)
    {
        currentCoins.add(inserted);
        currentBalance += inserted.getValue();
    }

    /**
     * Called when the user wants to buy a product
     * Checks if the user has enough money inserted into the machine
     * If they have enough then the value is removed from currentBalance and 
     * the coins are moved from currentCoins to coins and their qantity to coinQuantity 
     * If they dont have enough it returns the users coins and throws a VendingException
     */
    void buyProduct(Product toBuy)
    {
        //You can buy it
        if (currentBalance >= toBuy.getPrice())
        {
            //Sorting the money movement
            currentBalance -= toBuy.getPrice();
            for (int i = 0; i < currentCoins.size(); i++)
            {
                for (int j = 0; j < coins.size(); j++)
                {
                    if (coins.get(j).getName() == currentCoins.get(i).getName())
                    {
                        coinQuantity.set(j, (coinQuantity.get(j) + 1));
                    }
                }
            }
            //Remove the product from the machine
            for (int i = 0; i < products.size(); i++)
            {
                if (products.get(i).getDescription() == toBuy.getDescription())
                {
                    prodQuantity.set(i, prodQuantity.get(i) - 1);
                }
            }
        }
        else
        {
            double currentTotal = 0;
            for (int i = 0; i < currentCoins.size(); i++)
            {
                currentTotal += currentCoins.get(i).getValue();
            }
            currentBalance = 0;
            currentCoins.clear();
            throw new VendingException("Insufficient funds");
        }
    }

    /**
     * Operator only method
     * Finds the value of all coins in the machine
     * Removes these from the machine
     * Resets the quantity of each coin to 0
     * @return double total (total money in the machine)
     */
    double removeMoney()
    {
        double total = 0;
        for (int i = 0; i < coins.size(); i++)
        {
            for (int j = 0; j < coinQuantity.get(i); j++)
            {
                total += coins.get(i).getValue();
            }
        }
        for (int i = 0; i < coinQuantity.size(); i++)
        {
            coinQuantity.set(i, 0);
        }
        return total;
    }

    /**
     * This method adds a product to the vending machine
     * @param toAdd
     * @param quantity
     * toAdd is the product that is added
     * If there is already some of this product in the machine then the added quantity is added to the quantity that is already in the machine
     * If there are none of the product in the machine it adds a new product in the ArrayList and adds the products in the parallel prodQuantity ArrayList
     */
    void addProduct(Product toAdd, int quantity)
    {
        if(!products.contains(toAdd))
        {
            products.add(toAdd);
            prodQuantity.add(quantity);
        }
        else
        {
            for (int i = 0; i < products.size(); i++)
            {
                if (products.get(i).getDescription() == toAdd.getDescription())
                {
                    prodQuantity.set(i, prodQuantity.get(i) + 1);
                }
            }
        }
    }
}