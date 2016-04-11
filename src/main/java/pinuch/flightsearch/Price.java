package pinuch.flightsearch;

/**
 * Price class.
 * 
 * Provides formating.
 * 
 * @author ukedfabien
 *
 */
public class Price 
{
	private final double value;
	private final String priceAsStr;
	private final Currency currency;
	
	public Price(double value, Currency currency)
	{
		this.value = value;
		this.priceAsStr = String.format("%.1f", value);
		this.currency = currency;
	}
	
	public String toString()
	{
		// e.g: "109.1 Euro (€)"
		return priceAsStr + " " + currency.getName();  
	}
	
	public String valueToString()
	{
		return priceAsStr;  
	}
	
	public double getValue()
	{
		return value;
	}
	
	public Currency getCurrency()
	{
		return currency;
	}
}
