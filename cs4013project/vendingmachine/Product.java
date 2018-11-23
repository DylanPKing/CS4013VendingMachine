package cs4013project.vendingmachine;

/**
 * Product
 * @author Michael English
 * @author Dylan King
 */
class Product
{

	private String description;
	private double price;

	/**
     * Constructs a Product object
     * @param description the description of the product
     * @param price the price of the product
	 */
	Product(String description, double price)
	{  
		this.description = description;
		this.price = price;
	}

	/**
	 * @return the description
	 */
	String getDescription()
	{
		return description;
	}

	/**
	 * @return the price
	 */
	double getPrice()
	{
		return price;
	}

	@Override
	public String toString()
	{
		return String.format("Description:\t%s\nPrice:\t\t%.2f\n",
							 description, price);
	}
}