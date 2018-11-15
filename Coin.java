/**
 * Coin
 * @author Michael English
 * @author Dylan King
 */
public class Coin {

	private double value;
	private String name;

	/**
     * Constructs a coin.
     * @param value the monetary value of the coin.
     * @param name the name of the coin
	 */
	public Coin(double value, String name)
	{ 
		this.value = aValue; 
		this.name = aName;
	}
	
	/**
	 * @return the value
	 */
	public double getValue()
	{
		return value;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	@Override
	public String toString()
	{
		return name;
	}
}