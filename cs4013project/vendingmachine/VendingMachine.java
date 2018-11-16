package cs4013project.vendingmachine;

import java.util.*;

class VendingMachine
{
    ArrayList<Product> products;
    ArrayList<Integer> prodQuantity;
    ArrayList<Coin> coins;
    ArrayList<Integer> coinQuantity;
    ArrayList<Coin> currentCoins;
    double currentBalance;
    
    VendingMachine() 
    {

    }

    void addCoin(Coin inserted)
    {
        for (int i = 0; i < coins.size(); i++)
        {
            if (coins.get(i).getName() == inserted.getName())
            {
                //coinQuantity.set(i, (coinQuantity.get(i) + 1));
                currentCoins.add(inserted);
                currentBalance += inserted.getValue();
            }
        }
    }

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

    //Operator method - removes all money from the machine
    void removeMoney()
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
    }

    void addProduct(Product toAdd)
    {
        if(!products.contains(toAdd))
        {
            products.add(toAdd);
            prodQuantity.add(1);
        }
        else
        {
            for (int i = 0; i < products.size(); i++)
            {
                if (product.get(i).getDescription() == toAdd.getDescription())
                {
                    prodQuantity.set(i, prodQuantity.get(i) + 1);
                }
            }
        }
    }
}