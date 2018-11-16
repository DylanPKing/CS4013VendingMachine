package cs4013project.vendingmachine;

/**
 * Coin
 * @author Michael English
 * @author Dylan King
 */
class Coin 
{

	private double value;
	private String name;

	/**
     * Constructs a coin.
     * @param value the monetary value of the coin.
     * @param name the name of the coin
	 */
	Coin(double value, String name)
	{ 
		this.value = value; 
		this.name = name;
	}
	
	/**
	 * @return the value
	 */
	double getValue()
	{
		return value;
	}

	/**
	 * @return the name
	 */
	String getName()
	{
		return name;
	}

	@Override
	public String toString()
	{
		return name;
	}
}