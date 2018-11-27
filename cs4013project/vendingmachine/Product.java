package cs4013project.vendingmachine;

/**
 * This is the class for products, which contains the products name and price.
 * @author Michael English
 * @author Louise Madden		17198232
 */
public class Product
{

	private String description;
	private double price;

	/**
     * Constructs a Product object
     * @param description the description of the product
     * @param price the price of the product
	 */
	public Product(String description, double price)
	{  
		this.description = description;
		this.price = price;
	}

	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @return the price
	 */
	public double getPrice()
	{
		return price;
	}

	@Override
	public String toString()
	{
		return String.format("Description:\t%s\nPrice:\t\t%.2f\n",
							 description, price);
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Product)
		{
			return (description.equals(((Product)obj).getDescription()) && (price == ((Product)obj).getPrice()));
		}
		else
		{
			return false;
		}
	}
}