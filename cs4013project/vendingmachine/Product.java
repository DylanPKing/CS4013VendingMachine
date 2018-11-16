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
	private int quantity;

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

	/**
	 * @return the quantity
	 */
	int getQuantity()
	{
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	@Override
	public String toString()
	{
		return String.format("Description:\t%s\nPrice:\t%.2f\nQuantity Available:\t%d",
							 description, price, quantity);
	}
}