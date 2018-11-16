package cs4013project.vendingmachine;

/**
 * VendingMachineSimulation
 */
public class VendingMachineSimulation
{
	public static void main(String[] args)
	{
		// Make the initial menu for the machine.
		VendingMachine machine = new VendingMachine();
		VendingMachineMenu menu = new VendingMachineMenu();

		//Run the 
		menu.readFiles(machine);
		menu.run(machine);
		menu.writeFiles(machine);
		
	}
}