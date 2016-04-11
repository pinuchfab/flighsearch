package pinuch.flightssearch;

/**
 * Currency enum.
 * 
 * Based on the dollar.
 * 
 * @author ukedfabien
 *
 */
public enum Currency 
{
	Euro("Euro (€)", 1.14),
	Dollar("Dollar ($)", 1.0),
	BritishPound("Pound (£)", 1.41);
	
	private final String name;
	private double unitValueAsDollar;	// Unit value as dollar.
	
	private Currency(String name, double unitValueAsDollar)
	{
		this.name = name;
		this.unitValueAsDollar = unitValueAsDollar;
	}
	
	// Set a new value is currency changes.
	void setUnitValueAsDollar(double unitValueAsDollar)
	{
		this.unitValueAsDollar = unitValueAsDollar;
	}
	
	public double getUnitValueAsDollar() {
		return unitValueAsDollar;
	}

	public String getName() {
		return name;
	}
}
