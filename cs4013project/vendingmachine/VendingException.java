package cs4013project.vendingmachine;

/**
 * VendingException is our exception class, which is used in error handling.
 * @author Michael English
 */
public class VendingException extends RuntimeException
{

	public VendingException(String message)
	{
		super(message);
	}
}